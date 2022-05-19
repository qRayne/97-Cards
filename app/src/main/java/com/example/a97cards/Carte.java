package com.example.a97cards;

public class Carte {
    // Classe simple permettant de creer des cartes pour le jeu
    // Utile lorsque l'on veut creer les 98 cartes dans la classe PileCarte
    private int chiffre;

    public Carte(int chiffre) {
        this.chiffre = chiffre;
    }

    public int getChiffre() {
        return chiffre;
    }

    public void setChiffre(int chiffre) {
        this.chiffre = chiffre;
    }
}
