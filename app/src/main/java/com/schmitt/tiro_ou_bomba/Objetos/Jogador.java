package com.schmitt.tiro_ou_bomba.Objetos;

import java.util.ArrayList;

/**
 * Created by Raphael on 18/04/2018.
 */

public class Jogador {

    private String nomeJogador, numJogador;

    private ArrayList<String> palpitesAnteriores;

    public Jogador(){

    }

    public Jogador(String nomeJogador, String numJogador, ArrayList<String> palpitesAnteriores){
        this.nomeJogador = nomeJogador;
        this.numJogador = numJogador;
        this.palpitesAnteriores = palpitesAnteriores;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public String getNumJogador() {
        return numJogador;
    }

    public void setNumJogador(String numJogador) {
        this.numJogador = numJogador;
    }

    public ArrayList<String> getPalpitesAnteriores() {
        return palpitesAnteriores;
    }

    public void setPalpitesAnteriores(ArrayList<String> palpitesAnteriores) {
        this.palpitesAnteriores = palpitesAnteriores;
    }
}
