package com.schmitt.tiro_ou_bomba.Objetos;

/**
 * Created by Raphael on 18/04/2018.
 */

public class Configuracao {

    private int numDigitos, maiorNumero;
    private boolean repetirNum;

    public Configuracao(){

    }

    public Configuracao(int numDigitos, int maiorNumero, boolean repetirNum){
        this.numDigitos = numDigitos;
        this.maiorNumero = maiorNumero;
        this.repetirNum = repetirNum;
    }

    public int getNumDigitos() {
        return numDigitos;
    }

    public void setNumDigitos(int numDigitos) {
        this.numDigitos = numDigitos;
    }

    public int getMaiorNumero() {
        return maiorNumero;
    }

    public void setMaiorNumero(int maiorNumero) {
        this.maiorNumero = maiorNumero;
    }

    public boolean isRepetirNum() {
        return repetirNum;
    }

    public void setRepetirNum(boolean repetirNum) {
        this.repetirNum = repetirNum;
    }
}
