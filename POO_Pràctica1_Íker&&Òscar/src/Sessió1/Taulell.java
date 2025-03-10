package Sessió1;

import java.util.Random;
import jconsole.JConsole;

public class Taulell {

	private enum Estat{ Sembrat, Nascut, Florit }
	private Estat taulell [][];
	private int numFiles;
	private int numCol;
	private int flors;

	public Taulell(int fil, int col) {
		numFiles = fil;
		numCol = col;
		this.taulell = new Estat[numFiles][numCol];
        sembrarJardi();
	}
	
	private void sembrarJardi() {
        int valor;
        flors = 0;
        Random aleatori = new Random();

        do {
            flors = 0;
            for (int i = 0; i < numFiles; i++) {
                for (int j = 0; j < numCol; j++) {
                    valor = aleatori.nextInt(1, 4);
                    if (valor == 1) {
                        taulell[i][j] = Estat.Sembrat;
                    } else if (valor == 2) {
                        taulell[i][j] = Estat.Nascut;
                    } else  {
                        taulell[i][j] = Estat.Florit;
                        flors++;
                    }
                }
            }
        } 
        while (flors == numFiles*numCol);
    }

	public boolean totFlorit() {
        int contador = 0;
        for (int i = 0; i < numFiles; i++) {
            for (int j = 0; j < numCol; j++) {
                if (taulell[i][j] == Estat.Florit) {
                    contador++;
                }
            }
        }
        return contador == numFiles * numCol;
    }

	public void regar(int fil, int col) {		

		for (int j = 0; j < taulell[0].length; j++) {
	        if (j == col || j != col) {
	            canviarEstat(fil, j);
	        }
	    }


	    for (int i = 0; i < taulell.length; i++) {
	        if (i != fil) {
	            canviarEstat(i, col);
	        }
	    }

	}

	private void canviarEstat(int fila, int col) {
        switch (taulell[fila][col]) {
        
            case Sembrat -> taulell[fila][col] = Estat.Nascut;
            
            case Nascut -> taulell[fila][col] = Estat.Florit;
            
            case Florit -> taulell[fila][col] = Estat.Sembrat;
            
        }
    }
	
	public void numFlors() {
	    int flors = 0;
	    for (int i = 0; i < numFiles; i++) {
	        for (int j = 0; j < numCol; j++) {
	            if (taulell[i][j] == Estat.Florit) {
	                flors++;
	            }
	        }
	    }
	    this.flors = flors;
	}
	
	public int getNumeroFiles() {return numFiles;}
	public int getNumeroCol() {return numCol;}
	public int getNumeroFlors() {return flors;}
	public Estat getEstat(int i, int j) {return this.taulell[i][j];}
	
}