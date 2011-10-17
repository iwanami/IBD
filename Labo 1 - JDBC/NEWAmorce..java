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
class Vue1 extends JPanel {
	public Vue1() {
			setLayout(new GridLayout(1, 2));
			add (new JButton("B1"));
			add (new JButton("B2"));
	}
}

class Vue2 extends JPanel {
	public Vue2() {
			setLayout(new FlowLayout(FlowLayout.LEFT));
			add (new JButton("B1"));
			add (new JButton("B2"));
	}
}

class Vue3 extends JPanel {
	public Vue3() {
			setLayout(new BorderLayout());
			add (new JButton("B1"), BorderLayout.WEST);
			add (new JButton("B2"), BorderLayout.CENTER);
			add (new JButton("B3"), BorderLayout.EAST);
	}
}
//----------------------------------------------------------
class VuePrincipale extends JPanel {
	
	public VuePrincipale () {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add (new Vue1());
		add (new Vue2());
		add (new Vue3());
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