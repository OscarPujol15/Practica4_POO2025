package Sessió1;

import jconsole.JConsole;
import java.awt.Color;
import java.util.Random;

public class FlorirJardi {
	public enum Estat { Sembrat, Nascut, Florit }

	public static void main(String[] args) {
		JConsole console = new JConsole (160,40);

		int fila, columna, contRegs=0;
		int filaReg, columnaReg;
		int florides=0;

		String textFila, textColumna, textRegarFila, textRegarColumna;

		textFila= "Indica quantes files té el taulell: ";
		textColumna= "Indica quantes columnes té el taulell: ";
		textRegarFila = "Indica la fila de la component a regar ";
		textRegarColumna = "Indica la columna de la component a regar ";

		fila = llegirValor(3, 10, textFila, console);
		columna = llegirValor(3, 10, textColumna, console);

		Estat jardiMinim[][] = new Estat[fila][columna];
		Estat jardi[][] = new Estat[fila][columna];

		florides = sembrarJardi(jardi);
		jardiMinim = copiarJardi(jardiMinim, jardi);

		visualitza(jardi, console);

		console.println();

		console.println("Conmença el joc. Àniras indicant les components que cols regar");
		console.println("--------------------------------------------------------------");

		int vecFlors [] = new int [1];
		int florMinimes = fila*columna;  
        int menysFlors = 0;
		while (!acabar(jardi)) {
			
		    if (contRegs == vecFlors.length) {
		        vecFlors = ampliarVector(vecFlors, florides);
		    }

		    filaReg = llegirValor(0, jardi.length-1, textRegarFila, console);
		    columnaReg = llegirValor(0,jardi.length-1, textRegarColumna, console);
		    
		    if (florides <= florMinimes) {
		    	jardiMinim = copiarJardi(jardiMinim, jardi);
                florMinimes = florides;
                menysFlors = contRegs;  
            }
		    
		    florides = regar (jardi, filaReg, columnaReg, florides, console);
		    
		    vecFlors[contRegs] = florides;
		    visualitza(jardi, console);
		    contRegs++;
		}


		console.println("Guanyat!!!!!");
		console.println("Dades finals");
		
		for (int i = 0; i<vecFlors.length; i++) {
			console.println("El reg número " + (i+1) + " té " + vecFlors[i] + " flors ");
		}

		console.println("La regada que ha produït menys flors ha estat la Nº " + menysFlors);

		console.println();
		
		console.println("I el jardi tenia aquest estat");
		visualitza(jardiMinim, console);
		console.print("Fi joc");

	}

	private static int llegirValor(int minim, int maxim, String text, JConsole console) {
		int valor;
		console.println(text);

		console.setForegroundColor(Color.GREEN);
		valor = console.readInt();
		while (valor<minim || valor>maxim) {
			console.setForegroundColor(Color.RED);
			console.print("El valor entrat està fora de l'interval (ha d'estar entre 3 i 10)");
			console.println();
			console.setForegroundColor(Color.GREEN);
			valor = console.readInt();
		}
		console.resetColor();
		return valor;

	}

	private static int sembrarJardi(Estat jardi[][]) {
		int valor;
		int florides = 0;

		Random aleatori = new Random();

		do {
			florides = 0;
			for (int i = 0; i < jardi.length; i++) {
				for (int j = 0; j < jardi[0].length; j++) {
					valor = aleatori.nextInt(1, 4);
					if (valor == 1) {
						jardi[i][j] = Estat.Sembrat;
					} else if (valor == 2) {
						jardi[i][j] = Estat.Nascut;
					} else  {
						jardi[i][j] = Estat.Florit;
						florides++;
					}
				}
			}
		} while (florides == jardi.length * jardi[0].length);

		return florides;
	}

	private static void canviarEstat(Estat[][]jardi, int fila, int col, JConsole console) {

		if(jardi[fila][col]== Estat.Sembrat) {
			jardi [fila][col] = Estat.Nascut;
		} 

		else if(jardi[fila][col] == Estat.Nascut) {
			jardi [fila][col] = Estat.Florit;
		} 

		else {
			jardi [fila][col] = Estat.Sembrat;
		}
	}


	private static boolean acabar(Estat[][]taulell) {
		for (int i = 0; i<taulell.length;i++) {
			for (int j = 0; j<taulell[0].length;j++) {
				if (taulell [i][j]!= Estat.Florit) {
					return false;
				}
			}
		} 
		return true;
	}

	private static int regar(Estat[][] taulell, int fil, int col, int florides, JConsole console) {
	    console.println("El reg de la component (" + fil + "," + col + ") provoca el seguents canvis al jardí");

	    int contF = 0;

	    if (fil < 0 || fil >= taulell.length || col < 0 || col >= taulell[0].length) {
	        return florides;
	    }

	 
	    for (int j = 0; j < taulell[0].length; j++) {
	        if (j == col || j != col) {
	            canviarEstat(taulell, fil, j, console);
	        }
	    }

	   
	    for (int i = 0; i < taulell.length; i++) {
	        if (i != fil) {
	            canviarEstat(taulell, i, col, console);
	        }
	    }

	    
	    for (int i = 0; i < taulell.length; i++) {
	        for (int j = 0; j < taulell[0].length; j++) {
	            if (taulell[i][j] == Estat.Florit) {
	                contF++;
	            }
	        }
	    }

	    return contF;
	}

	private static void visualitza(Estat[][] t, JConsole console) {
		for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[0].length; j++) {
                if (t[i][j] == Estat.Sembrat) {
                    console.print(t[i][j] + "\t");
                } else if (t[i][j] == Estat.Nascut) {
                    console.print(t[i][j] + "\t ");
                } else {
                    console.print(t[i][j] + "\t ");
                }
            }
            console.println();
        }
	}

	private static int[] ampliarVector(int[] vector, int nuevoElemento) {
		int[] nuevoVector = new int[vector.length + 1];

		for (int i = 0; i < vector.length; i++) {
			nuevoVector[i] = vector[i];
		}

		nuevoVector[nuevoVector.length - 1] = nuevoElemento;

		return nuevoVector;
	}

	private static Estat[][] copiarJardi(Estat[][] jardiMinim , Estat [][] jardi) {
		for (int i = 0; i < jardi.length; i++) {
			for (int j = 0; j < jardi[0].length; j++) {
				jardiMinim[i][j] = jardi[i][j]; 
			}
		}
		return jardiMinim;
	}


}