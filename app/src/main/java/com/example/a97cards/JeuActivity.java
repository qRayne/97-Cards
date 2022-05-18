package com.example.a97cards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

public class JeuActivity extends AppCompatActivity {

    // Les variables front-end
    LinearLayout parent, conteneurChiffreEntête,
            conteneurAscendant, conteneurAscendantGauche,
            conteneurAscendantDroite, conteneurDescendant,
            conteneurDescendantGauche, conteneurDescedantDroite;
    GridLayout deckCartes;
    Chronometer chronometer;
    EcouteurCarte ecouteurCarte;
    EcouteurDeck ecouteurDeck;
    Button menu;
    TextView nbCartesRestantes, textscore, carteSelectionner;

    //Les varibles back-end
    Vector<String> carteEnleverValeur;
    Vector<Integer> carteEnleverEmplacement;
    PileCarte pileCarte;
    Score score;
    int scoreAjouter;
    String chiffreSelectionner;
    Boolean findePartie;
    Integer dernierCarteSurlapile;
    String dernierCarteSurlapileValeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        // Les findViewById
        parent = findViewById(R.id.parent);
        conteneurChiffreEntête = findViewById(R.id.conteneurChiffresEnTete);
        conteneurAscendant = findViewById(R.id.conteneurAscendant);
        conteneurAscendantGauche = findViewById(R.id.conteneurAscendantGauche);
        conteneurAscendantDroite = findViewById(R.id.conteneurAscendantDroite);
        conteneurDescendant = findViewById(R.id.conteneurDescendant);
        conteneurDescendantGauche = findViewById(R.id.conteneurDescendantGauche);
        conteneurDescedantDroite = findViewById(R.id.conteneurDescendantDroite);
        deckCartes = findViewById(R.id.deckCards);
        chronometer = findViewById(R.id.simpleChronometer);
        menu = findViewById(R.id.buttonMenu);
        nbCartesRestantes = findViewById(R.id.nbCarteRestante);
        textscore = findViewById(R.id.score);


        // Les initialisation de variables
        ecouteurCarte = new EcouteurCarte();
        ecouteurDeck = new EcouteurDeck();
        chronometer.start();
        chiffreSelectionner = "0";
        score = new Score(0);
        scoreAjouter = 0;
        findePartie = false;
        pileCarte = new PileCarte();
        pileCarte.melangerCartes();
        dernierCarteSurlapile = null;
        dernierCarteSurlapileValeur = null;
        carteEnleverValeur = new Vector<>();
        carteEnleverEmplacement = new Vector<>();

        // Les sets texts
        nbCartesRestantes.setText(String.valueOf(pileCarte.tailleListeCartes()));
        textscore.setText(String.valueOf(score.getScore()));

        // Les setOnClickListener
        menu.setOnClickListener(ecouteurCarte);

        // Les cartes
        for (int i = 0; i < deckCartes.getChildCount(); i++) {
            TextView carte = (TextView) deckCartes.getChildAt(i);
            carte.setText(String.valueOf(pileCarte.tirerCarte()));
            carte.setOnTouchListener(ecouteurCarte);
            carte.setOnDragListener(ecouteurCarte);
        }

        // Le conteneur ascendant
        for (int i = 0; i < conteneurAscendant.getChildCount(); i++) {
            LinearLayout conteneur = (LinearLayout) conteneurAscendant.getChildAt(i);
            conteneur.setOnDragListener(ecouteurDeck);
        }

        // Le conteneur descendant
        for (int i = 0; i < conteneurDescendant.getChildCount(); i++) {
            LinearLayout conteneur = (LinearLayout) conteneurDescendant.getChildAt(i);
            conteneur.setOnDragListener(ecouteurDeck);
        }
    }

    private class EcouteurCarte implements View.OnClickListener, View.OnDragListener, View.OnTouchListener {

        @Override
        public void onClick(View v) {
            if (v.equals(menu))
                startActivity(new Intent(JeuActivity.this, MainActivity.class));
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {

            if (event.getAction() == DragEvent.ACTION_DRAG_STARTED) {
                if (((TextView) v).getText() == chiffreSelectionner)
                    ((TextView) v).setText("");
            } else if (event.getAction() == DragEvent.ACTION_DRAG_ENDED || event.getAction() == DragEvent.ACTION_DROP) {
                if (chiffreSelectionner != null) {
                    carteSelectionner.setText(chiffreSelectionner);
                    chiffreSelectionner = null;
                }
            }

            System.out.println(((TextView) v).getText());
            return true;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

            if (Build.VERSION.SDK_INT <= 24)
                v.startDrag(null, shadowBuilder, v, 0);
            else
                v.startDragAndDrop(null, shadowBuilder, v, 0);

            carteSelectionner = (TextView) v;
            chiffreSelectionner = (String) carteSelectionner.getText();
            return true;
        }
    }


    private class EcouteurDeck implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            if (event.getAction() == DragEvent.ACTION_DROP) {
                LinearLayout conteneur = (LinearLayout) v;
                boolean placementEffectuer = false;  // permet de voir si l'action du joueur a marcher ou non
                int valeurCarte = Integer.parseInt(chiffreSelectionner);
                int carteCouranteValeur;
                TextView carteConteneur = null;
                TextView endroitDeposeCarte = null;

                // trouver dans quel côte le joueur a deposer sa carte
                if (v.getId() == conteneurAscendantGauche.getId() || v.getId() == conteneurDescendantGauche.getId())
                    carteConteneur = (TextView) conteneur.getChildAt(1);
                else if (v.getId() == conteneurAscendantDroite.getId() || v.getId() == conteneurDescedantDroite.getId())
                    carteConteneur = (TextView) conteneur.getChildAt(1);
                carteCouranteValeur = Integer.parseInt((String) carteConteneur.getText());

                // si c'est du côte ascendant ou descendant
                if (((LinearLayout) conteneur.getParent()).getId() == conteneurAscendant.getId()) {
                    if (valeurCarte > carteCouranteValeur || carteCouranteValeur - valeurCarte == 10)
                        placementEffectuer = true;
                } else if (valeurCarte < carteCouranteValeur || valeurCarte - carteCouranteValeur == 10)
                    placementEffectuer = true;


                // si le placement a ete effectuer
                if (placementEffectuer) {
                    dernierCarteSurlapile = carteConteneur.getId();
                    dernierCarteSurlapileValeur = (String) carteConteneur.getText();
                    endroitDeposeCarte = carteConteneur;
                    carteEnleverValeur.add(chiffreSelectionner);
                    carteEnleverEmplacement.add(carteSelectionner.getId());
                    nbCartesRestantes.setText(String.valueOf(pileCarte.tailleListeCartes() - carteEnleverValeur.size()));
                }


                // calculer maintenant le score
                int scoreCourant = score.getScore();
                scoreAjouter = Math.abs(valeurCarte - carteCouranteValeur) * 2;
                if (scoreAjouter < 0)
                    scoreAjouter = 0;
                score.setScore(scoreCourant += scoreAjouter);
                textscore.setText(String.valueOf(score.getScore()));


                // repiger deux nouvelles cartes après leur placement
                if (carteEnleverValeur.size() == 2) {
                    if (pileCarte.tailleListeCartes() > 0) {
                        for (int i = 0; i < 2; i++){
                            TextView nouvelleCarte = findViewById(carteEnleverEmplacement.remove(carteEnleverEmplacement.size()-1));
                            carteEnleverValeur.remove(carteEnleverValeur.size() - 1);

                            int nouvelleValeurCarte = pileCarte.tirerCarte();
                            nouvelleCarte.setText(String.valueOf(nouvelleValeurCarte));
                        }
                    }
                }
                else {
                    endroitDeposeCarte = carteSelectionner;
                }
                endroitDeposeCarte.setText(chiffreSelectionner);
                carteSelectionner = null;
                chiffreSelectionner = null;
            }
            return true;
        }
    }
}