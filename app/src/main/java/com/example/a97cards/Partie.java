package com.example.a97cards;

import android.widget.TextView;

import androidx.gridlayout.widget.GridLayout;

import java.util.Vector;

public class Partie {
    Vector<String> carteEnleverValeur;
    Vector<Integer> carteEnleverEmplacement;
    int dernierCarteSurlapile;
    String dernierCarteSurlapileValeur;
    Boolean findePartie;

    public Partie() {
        this.carteEnleverValeur = new Vector<>();
        this.carteEnleverEmplacement = new Vector<>();
        this.dernierCarteSurlapile = 0;
        this.dernierCarteSurlapileValeur = "";
        this.findePartie = false;
    }

    public boolean verifierFindePartie(){
        return findePartie = true;
    }

    public boolean verifierPlacement(String conteneurParent,int valeurCarte, int carteCouranteValeur){
        if (conteneurParent.equals("ascendant"))
            return (valeurCarte > carteCouranteValeur || carteCouranteValeur - valeurCarte == 10);
        else if (conteneurParent.equals("descendant"))
            return (valeurCarte < carteCouranteValeur || valeurCarte - carteCouranteValeur == 10);
        else
            return false;
    }

    public int calculerScore(Score score,int valeurCarte, int carteCouranteValeur){
        int scoreAjouter = (int) (Math.abs(valeurCarte - carteCouranteValeur) * 2.5);
        int scoreCourant = score.getScore();
        if (scoreAjouter < 0)
            scoreAjouter = 0;
        score.setScore(scoreAjouter += scoreCourant);
        return scoreAjouter + scoreCourant;
    }

    public boolean deckVide(GridLayout deckCartes){
        boolean deckVide = true;

        for (int i = 0; i<8; i++)
            if (!((String)((TextView)deckCartes.getChildAt(i)).getText()).contentEquals("")){
                deckVide = false;
                break;
            }
        return deckVide;
    }

    public Vector<String> getCarteEnleverValeur() {
        return carteEnleverValeur;
    }

    public void setCarteEnleverValeur(Vector<String> carteEnleverValeur) {
        this.carteEnleverValeur = carteEnleverValeur;
    }

    public Vector<Integer> getCarteEnleverEmplacement() {
        return carteEnleverEmplacement;
    }

    public void setCarteEnleverEmplacement(Vector<Integer> carteEnleverEmplacement) {
        this.carteEnleverEmplacement = carteEnleverEmplacement;
    }

    public int getDernierCarteSurlapile() {
        return dernierCarteSurlapile;
    }

    public void setDernierCarteSurlapile(int dernierCarteSurlapile) {
        this.dernierCarteSurlapile = dernierCarteSurlapile;
    }

    public String getDernierCarteSurlapileValeur() {
        return dernierCarteSurlapileValeur;
    }

    public void setDernierCarteSurlapileValeur(String dernierCarteSurlapileValeur) {
        this.dernierCarteSurlapileValeur = dernierCarteSurlapileValeur;
    }

    public Boolean getFindePartie() {
        return findePartie;
    }

    public void setFindePartie(Boolean findePartie) {
        this.findePartie = findePartie;
    }
}
