package com.example.a97cards;

import java.util.Collections;
import java.util.Vector;

public class PileCarte {
    private Vector<Carte> listeCartes;

    public PileCarte() {
        // on creer les 98 cartes dans le jeu
        listeCartes = new Vector<Carte>();
        for (int i = 1; i < 98; i++)
            listeCartes.add(new Carte(i));
    }

    public void melangerCartes() {
        // Methode permettant de melanger les cartes dans le jeu
        Vector listeCartesMelanger = listeCartes;
        Collections.shuffle(listeCartesMelanger);
        this.listeCartes = listeCartesMelanger;
    }

    public int tirerCarte(){

        // Methode permettant de tirer une carte (la dernière sur la pile de carte) après que cette pile soit melanger
        // J'ai utiliser listeCartes.size()-1 comme alternative à pop, car j'utilise un vecteur et non un Stack
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
