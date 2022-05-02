package com.example.a97cards;

import java.util.Collections;
import java.util.Vector;

public class PileCarte {
    private Vector<Carte> listeCartes;

    public Vector creerCartes() {
        listeCartes = new Vector<Carte>(listeCartes);
        for (int i = 1; i < 98; i++)
            listeCartes.add(new Carte(i));
        return listeCartes;
    }

    public void melangerCartes() {
        Vector listeCartesMelanger = creerCartes();
        Collections.shuffle(listeCartesMelanger);
        this.listeCartes = listeCartesMelanger;
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
