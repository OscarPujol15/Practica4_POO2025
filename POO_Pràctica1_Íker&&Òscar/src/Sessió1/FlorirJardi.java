package Sessió1;

import java.awt.Color;
import java.util.Random;

public class FlorirJardi {

    public static void main(String[] args) {

        int fila, columna, contRegs = 0;
        int filaReg, columnaReg;
        int florides = 0;
        Random alea = new Random ();
        Taulell jardi = new Taulell(alea.nextInt(3,7), alea.nextInt(3,7));
            
        
        int vecFlors[] = new int[1];  
        int florMinimes = fila*columna;  
        int menysFlors = 0;
        

        while (!jardi.totFlorit()) {
            if (contRegs == vecFlors.length) {
            }    
            jardi.regar(filaReg, columnaReg);
            jardi.numFlors();
            florides = jardi.getNumeroFlors();
            vecFlors[contRegs] = florides;
            
            if (florides < florMinimes) {
                florMinimes = florides;
                menysFlors = contRegs;  
            }
            contRegs++;
        }
    }
}