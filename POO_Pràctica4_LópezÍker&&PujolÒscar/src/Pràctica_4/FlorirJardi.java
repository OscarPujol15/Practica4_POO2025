package Pràctica_4;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlorirJardi extends JFrame implements ActionListener {

	private static Casella [][]terreny;
	private static int numFlors;
	private JButton reiniciar;
	private JButton finalitzar;
	private JPanel p1,p2;
	private Random alea = new Random();
	private static int fila;
	private static int columna;

	public FlorirJardi() {
		super("Taulell de Joc");
		initComponents();
	}

	private void initComponents() {
		fila = alea.nextInt(3,7);
		columna = alea.nextInt(3,7);
		this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		p1 = new JPanel();
		p1.setLayout(new GridLayout(fila, columna));
		terreny = new Casella[fila][columna];

		for (int i = 0; i < fila; i++) {
			for (int j = 0; j < columna; j++) {
				terreny[i][j] = new Casella();
				if (terreny[i][j].flor()) {
					numFlors++;
				}

				terreny [i][j].addActionListener(this);
				p1.add(terreny[i][j]);
			}

		}

		p2 = new JPanel();
		p2.setLayout(new GridLayout(1,2)); 
		reiniciar = new JButton("Reiniciar");
		finalitzar = new JButton("Finalitzar");

		reiniciar.addActionListener(this);
		finalitzar.addActionListener(this);

		p2.add(reiniciar);
		p2.add(finalitzar);

		this.add(p1, BorderLayout.CENTER);
		this.add(p2, BorderLayout.SOUTH);


		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}



	public void actionPerformed(ActionEvent e) {
		for (int i =0; i<fila; i++) {
			for (int j =0; j<columna; j++) {
				if (e.getSource()==terreny[i][j]) {
					regar(i,j);
					return;
				}
			}
			if (e.getSource()== reiniciar) {
				reinitcialitzacio();
			}

		}	
		if (e.getSource() == finalitzar) {
			System.exit(0);
		}

	}

	private void regar(int x, int y) {
		for (int i = 0; i < fila; i++) {
			numFlors+=terreny[i][y].canviEstat();

		}
		for (int j = 0; j < columna; j++) {
			if (j != y) {
				numFlors+=terreny[x][j].canviEstat();
			}
		}
	}

	private void reinitcialitzacio() {
		for (int i=0; i<fila;i++) {
			for(int j=0;j<columna;j++) {
				int cops=alea.nextInt(0,3);
				for(int r=0;r<cops;r++)
					numFlors+=terreny[i][j].canviEstat();
			}
		}
	}

	private static void guanyem() {
		if (numFlors==columna*fila) {
			for(int i=0;i<fila;i++) {
				for(int j=0;j<columna;j++)
					terreny[i][j].setEnabled(false);
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(
				new Runnable() {
					public void run () {
						JFrame taulell = new FlorirJardi();
						taulell.setSize(190*columna,120*fila);
						taulell.setVisible(true);
						guanyem();
					}
				}
				);
	}
}
