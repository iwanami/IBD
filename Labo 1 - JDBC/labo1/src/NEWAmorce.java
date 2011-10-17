
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableModel;



//----------------------------------------------------------
class Glb {
// Constantes globales
	public static int TAILLE_X = 800;
	public static int TAILLE_Y = 600;
        public static final String ok = "OK";
        public static final String cancel = "CANCEL";
        public static final String next = "NEXT";
        public static final String prev = "PREV";
        public static final String corr = "CORR";
        
        
        public static final int question_count = 20;
        public static final String[] reponses = {
            "SELECT * FROM etudiant",
            "SELECT * FROM etudiant ORDER BY nom DESC",
            "SELECT libelle, coef*100 FROM matiere",
            "SELECT nom, prenom FROM etudiant",
            "SELECT nom, prenom FROM etudiant WHERE ville='Lyon'",
            "SELECT note FROM notation WHERE note>=10",
            "SELECT * FROM epreuve WHERE datepreuve BETWEEN '2004-01-01' AND '2004- 06-30'",
            "SELECT nom, prenom, ville FROM etudiant WHERE ville LIKE '%ll%'",
            "SELECT prenom FROM etudiant WHERE nom IN ('Dupont', 'Durand', 'Martin')",
            "SELECT SUM(coef) FROM matiere",
            "SELECT COUNT(*) FROM epreuve",
            "SELECT COUNT(*) FROM notation WHERE note IS NULL",
            "SELECT numepreuve, datepreuve, lieu, libelle FROM epreuve, matiere WHERE epreuve.codemat=matiere.codemat",
            "SELECT nom, prenom, note FROM etudiant, notation WHERE etudiant.numetu=notation.numetu",
            "SELECT nom, prenom, note, libelle FROM etudiant, notation, epreuve, matiere "+
                "WHERE etudiant.numetu=notation.numetu AND notation.numepreuve=epreuve.numepreuve AND epreuve.codemat=matiere.codemat",
            "SELECT DISTINCT nom, prenom FROM etudiant, notation WHERE etudiant.numetu=notation.numetu AND note=20",
            "SELECT nom, prenom, AVG(note) FROM etudiant, notation "+
                "WHERE etudiant.numetu=notation.numetu GROUP BY nom, prenom",
            "SELECT nom, prenom, AVG(note) AS moyenne FROM etudiant, notation "+
                "WHERE etudiant.numetu=notation.numetu GROUP BY nom, prenom ORDER BY moyenne DESC",
            "SELECT libelle, AVG(note) FROM matiere AS m, epreuve AS e, notation AS n "+
                "WHERE m.codemat=e.codemat AND e.numepreuve=n.numepreuve "+
                "GROUP BY libelle HAVING COUNT(DISTINCT e.numepreuve)>1",
            "SELECT e.numepreuve, AVG(note) "+
                "FROM epreuve AS e, notation AS n "+
                "WHERE e.numepreuve=n.numepreuve AND note IS NOT NULL "+
                "GROUP BY e.numepreuve HAVING COUNT(*)<6"
        };
        
        public static final String[] questions = {
            "Liste de tous les étudiants.",
            "Liste de tous les étudiants, classée par ordre alphabétique inverse.",
            "Libellé et coefficient (exprimé en pourcentage) de chaque matière.",
            "Nom et prénom de chaque étudiant",
            "Nom et prénom des étudiants domiciliés à Lyon",
            "Liste des notes supérieures ou égales à 10",
            "Liste des épreuves dont la date se situe entre le 1er janvier et le 30 juin 2004.",
            "Nom, prénom et ville des étudiants dont la ville contient la chaîne 'll'.",
            "Prénoms des étudiants de nom Dupont, Durand ou Martin.",
            "Somme des coefficients de toutes les matières",
            "Nombre total d'épreuves",
            "Nombre de notes indéterminées (NULL).",
            "Liste des épreuves (numéro, date et lieu) incluant le libellé de la matière.",
            "Liste des notes en précisant pour chacune le nom et le prénom de l'étudi- ant qui l'a obtenue.",
            "Liste des notes en précisant pour chacune le nom et le prénom de l'étudi- ant qui l'a obtenue et le libellé de la matière concernée.",
            "Nom et prénom des étudiants qui ont obtenu au moins une note égale à 20.",
            "Moyennes des notes de chaque étudiant (indiquer le nom et le prénom).",
            "Moyennes des notes de chaque étudiant (indiquer le nom et le prénom), classées de la meilleure à la moins bonne",
            "Moyennes des notes pour les matières (indiquer le libellé) comportant plus d'une épreuve.",
            "Moyennes des notes obtenues aux épreuves (indiquer le numéro d'épreuve) où moins de 6 étudiants ont été notés."
        };
        

}

//----------------------------------------------------------
class VueSaisie extends JPanel {
        JTextField request = new JTextField("requete");
        JTextArea question = new JTextArea("JTan");
	public VueSaisie() {
			setLayout(new GridLayout(1, 2));
			add (request);
			add (question);
	}
}

class VueValid extends JPanel {
    JButton ok = new JButton("Ok");
    JButton cancel = new JButton("cancel");
    JButton next = new JButton("question suivante");
    JButton prev = new JButton("question precedente");
    JButton correct = new JButton("correction");
	public VueValid(ActionListener ml) {
			setLayout(new FlowLayout(FlowLayout.LEFT));
                        ok.setActionCommand(Glb.ok);
                        ok.addActionListener(ml);
			add (ok);
                        
                        cancel.setActionCommand(Glb.cancel);
                        cancel.addActionListener(ml);
			add (cancel);
                        
                        next.setActionCommand(Glb.next);
                        next.addActionListener(ml);
                        add (next);
                        
                        prev.setActionCommand(Glb.prev);
                        prev.addActionListener(ml);
                        add (prev);
                        
                        correct.setActionCommand(Glb.corr);
                        correct.addActionListener(ml);
                        add (correct);
                        
	}
}

class VueTables extends JPanel {
    JTable request = new JTable();
	public VueTables() {
			setLayout(new BorderLayout());
			//add (new JTable(), BorderLayout.WEST);
			add (request, BorderLayout.CENTER);
			//add (new JTable(), BorderLayout.EAST);
	}
}
//----------------------------------------------------------
class VuePrincipale extends JPanel {
        
        VueSaisie saisie;
        VueValid valid;
        VueTables tables;
        Connection con;
        int q_count = 0;
	
	public VuePrincipale (String db, String username, String password) {
                con = DBManager.connect(db, username, password);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.saisie = new VueSaisie();
                add(this.saisie);
                
                this.valid = new VueValid(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        String cmd = e.getActionCommand();
                        if(cmd.equals(Glb.ok)){
                            System.out.println("prout");
                            processRequest();
                        }
                        else if(cmd.equals(Glb.cancel)){
                            saisie.request.setText("requete");
                        }
                        else if(cmd.equals(Glb.next)){
                            saisie.question.setText(Glb.questions[++q_count%Glb.question_count]);
                            
                        }
                        else if(cmd.equals(Glb.prev)){
                            saisie.question.setText(Glb.questions[--q_count%Glb.question_count]);
                        }
                        else if(cmd.equals(Glb.corr)){
                            saisie.request.setText(Glb.reponses[q_count]);
                        }
                        
                        
                    }
                });
                add(this.valid);
                
                this.tables = new VueTables();
                add(this.tables);
                
	}
        
        private void processRequest(){
            
            String request = saisie.request.getText();
            if (request.equals(Glb.reponses[q_count])){
                saisie.question.setText(Glb.questions[++q_count%Glb.question_count]);
                
            }
            ResultSet rs = DBManager.request(con, request);
            int row_num = 0;
            String[] column_names;
            Object[][] data;
            int column_count;
            int row_count;
            try{
                //init tableaux+comptes des lignes colonnes
                ResultSetMetaData rsmd = rs.getMetaData();
                column_count = rsmd.getColumnCount();
                column_names = new String[column_count];
                rs.last();
                row_count = rs.getRow();
                rs.first();
                data = new Object[row_count][column_count];
                for(int i = 0; i < column_count; i++){
                    column_names[i] = rsmd.getColumnName(i+1);
                }
                
                while(rs.next()){
                    for(int i = 0; i < column_count; i++){
                        data[row_num][column_count] = rs.getObject(column_names[i]);
                    }
                    row_num++;
                }
                tables.request = new JTable(data, column_names);
            }
            catch(SQLException e){}
            
            
        
        }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString ("Hello!", 50, 50);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension (Glb.TAILLE_X, Glb.TAILLE_Y);
	}

}
//-----------------------------------------------------
class Controleur {
	public Controleur(String db, String username, String password) {
		
		JFrame f = new JFrame ("Titre fenetre");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(new VuePrincipale(db, username, password), BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);

	}
}
//-----------------------------------------------------
public class NEWAmorce  {
	public static void main (String [] args) {
            
                String db = "localhost:8888/labo1";
                String username = "";
                String password = "";
                
                //Connection con = DBManager.connect(db, username, password);
                
                
		new Controleur(db, username, password);
	}
}