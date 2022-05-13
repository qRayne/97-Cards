package com.example.a97cards;

import java.util.Collections;
import java.util.Vector;

public class PileCarte {
    private Vector<Carte> listeCartes;

    public PileCarte() {
        listeCartes = new Vector<Carte>();
        for (int i = 1; i < 98; i++)
            listeCartes.add(new Carte(i));
    }

    public void melangerCartes() {
        Vector listeCartesMelanger = listeCartes;
        Collections.shuffle(listeCartesMelanger);
        this.listeCartes = listeCartesMelanger;
    }

    public int tirerCarte(){
        return listeCartes.remove(listeCartes.size()-1).getChiffre();
    }

    public int tailleListeCartes()
    {
        return listeCartes.size();
    }

    public Vector<Carte> getListeCartes() {
        return listeCartes;
    }

    public void setListeCartes(Vector<Carte> listeCartes) {
        this.listeCartes = listeCartes;
    }
}
