import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



//----------------------------------------------------------
class Glb {
// Constantes globales
	public static int TAILLE_X = 400;
	public static int TAILLE_Y = 400;

}

//----------------------------------------------------------
class VuePrincipale extends JPanel {
	
	public VuePrincipale () {
		setLayout(new BorderLayout());
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
	public Controleur() {
		
		JFrame f = new JFrame ("Titre fenetre");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(new VuePrincipale(), BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);

	}
}
//-----------------------------------------------------
public class Amorce  {
	public static void main (String [] args) {
		new Controleur();
	}
}