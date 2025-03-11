package Pràctica_4;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Casella extends JButton {

	private enum Estat {Sembrat, Nascut, Florit}
	private final ImageIcon Florit = new ImageIcon (getClass().getResource("../Florit.jpg"));
	private final ImageIcon Nascut = new ImageIcon(getClass().getResource("../Nascut.jpg"));
	private final ImageIcon Sembrat = new ImageIcon(getClass().getResource("../Sembrat.jpg"));
	private Estat estat;
	private Random alea;

	public Casella() {
		super();
		alea = new Random();
		switch (alea.nextInt(0,3)) {
		case 0:
			estat = Estat.Sembrat;
			this.setIcon(Sembrat);
			break;
		case 1:
			estat = Estat.Nascut;
			this.setIcon(Nascut);
			break;
		case 2: 
			estat = Estat.Florit;
			this.setIcon(Florit);
			break;

		}
	}
	public int canviEstat() {
		switch (estat) {
		case Sembrat: 
			estat= Estat.Nascut;
			this.setIcon(Nascut);
			return 0;
		case Nascut: 
			estat= Estat.Florit;
			this.setIcon(Florit);
			return 1;
		default: 
			estat= Estat.Sembrat;
			this.setIcon(Sembrat);
			return -1;
		}

	}

	public boolean flor() {
		return estat == Estat.Florit;
	}

}
