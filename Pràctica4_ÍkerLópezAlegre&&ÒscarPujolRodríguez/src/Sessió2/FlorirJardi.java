package Sessió2;

import java.awt.Color;
import jconsole.JConsole;

public class FlorirJardi {

    public static void main(String[] args) {
        
        JConsole console = new JConsole(160,40);

        int fila, columna, contRegs = 0;
        int filaReg, columnaReg;
        int florides = 0;

        String textFila, textColumna, textRegarFila, textRegarColumna;

        textFila = "Indica quantes files té el taulell: ";
        textColumna = "Indica quantes columnes té el taulell: ";
        textRegarFila = "Indica la fila de la component a regar ";
        textRegarColumna = "Indica la columna de la component a regar ";

        fila = llegirValor(3, 10, textFila, console);
        columna = llegirValor(3, 10, textColumna, console);

        Taulell jardi = new Taulell(fila, columna);
        
        jardi.visualitzar(console);

        console.println();

        console.println("Conmença el joc. Àniras indicant les components que cols regar");
        console.println("--------------------------------------------------------------");
        
        
        int vecFlors[] = new int[1];
        Taulell jardiMinim = jardi.copia();  
        int florMinimes = fila*columna;  
        int menysFlors = 0;
        

        while (!jardi.totFlorit()) {
            if (contRegs == vecFlors.length) {
                vecFlors = ampliarVector(vecFlors, florides);
            }

            filaReg = llegirValor(0, fila-1 , textRegarFila, console);
            columnaReg = llegirValor(0, columna-1, textRegarColumna, console);
            
            jardi.regar(filaReg, columnaReg, console);
            jardi.numFlors();
            florides = jardi.getNumeroFlors();
            vecFlors[contRegs] = florides;
            
            if (florides < florMinimes) {
                florMinimes = florides;
                jardiMinim = jardi.copia();
                menysFlors = contRegs;  
            }
            
            console.println("El reg de la component (" + filaReg + "," + columnaReg + ") provoca el següents canvis al jardí");
            jardi.visualitzar(console);
            contRegs++;
        }
        
        console.println("Guanyat!!!!!");
        console.println("Dades finals");

        for (int i = 0; i < vecFlors.length; i++) {
            console.println("El reg número " + (i+1) + " té " + vecFlors[i] + " flors ");
        }
        
        console.println("La regada que ha produït menys flors ha estat la Nº " + (menysFlors+1));
        console.println();
        console.println("I el jardi tenia aquest estat");
        jardiMinim.visualitzar(console);
        console.print("Fi joc");
    }

    private static int llegirValor(int minim, int maxim, String text, JConsole console) {
        int valor;
        console.println(text);

        console.setForegroundColor(Color.GREEN);
        valor = console.readInt();
        while (valor < minim || valor > maxim) {
            console.setForegroundColor(Color.RED);
            console.print("El valor entrat està fora de l'interval (ha d'estar entre 3 i 10)");
            console.println();
            console.setForegroundColor(Color.GREEN);
            valor = console.readInt();
        }
        console.resetColor();
        return valor;
    }

    private static int[] ampliarVector(int[] vector, int nouElemnt) {
        int[] vecNou = new int[vector.length + 1];

        for (int i = 0; i < vector.length; i++) {
            vecNou[i] = vector[i];
        }

        vecNou[vecNou.length - 1] = nouElemnt;
        return vecNou;
    }
}