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

import org.w3c.dom.Text;

public class JeuActivity extends AppCompatActivity {

    // Les variables front-end
    LinearLayout parent,conteneurChiffreEntête,
                 conteneurAscendant,conteneurAscendantGauche,
                 conteneurAscendantDroite,conteneurDescendant,
                 conteneurDescendantGauche,conteneurDescedantDroite;
    GridLayout deckCartes;
    Chronometer chronometer;
    EcouteurCarte ecouteurCarte;
    EcouteurDeck ecouteurDeck;
    Button menu;
    TextView nbCartesRestantes, textscore,carteSelectionner;

    //Les varibles back-end
    Partie partie;
    PileCarte pileCarte;
    Score score;
    String chiffreSelectionner;
    Boolean nouvellePartie;

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


        // Les intialisation de variables
        ecouteurCarte = new EcouteurCarte();
        ecouteurDeck = new EcouteurDeck();
        partie = new Partie();
        pileCarte = new PileCarte();
        score = new Score(0);
        chiffreSelectionner = "0";
        nouvellePartie = false;
        pileCarte.melangerCartes();


        // Les sets texts
        nbCartesRestantes.setText(String.valueOf(pileCarte.tailleListeCartes()));
        textscore.setText(String.valueOf(score.getScore()));

        // Les setOnClickListener
        menu.setOnClickListener(ecouteurCarte);

        // Les cartes
        for (int i = 0; i < deckCartes.getChildCount();i++){
            TextView carte = (TextView)deckCartes.getChildAt(i);
            carte.setText(String.valueOf(pileCarte.tirerCarte()));
            carte.setOnTouchListener(ecouteurCarte);
            carte.setOnDragListener(ecouteurCarte);
        }

        // Le conteneur ascendant
        for (int i = 0; i < conteneurAscendant.getChildCount(); i++){
            LinearLayout conteneur = (LinearLayout)conteneurAscendant.getChildAt(i);
            conteneur.setOnDragListener(ecouteurCarte);
        }

        // Le conteneur descendant
        for (int i = 0; i < conteneurDescendant.getChildCount(); i++){
            LinearLayout conteneur = (LinearLayout)conteneurDescendant.getChildAt(i);
            conteneur.setOnDragListener(ecouteurCarte);
        }
    }

    private class EcouteurCarte implements View.OnClickListener,View.OnDragListener,View.OnTouchListener{

        @Override
        public void onClick(View v) {
            if (v.equals(menu))
                startActivity(new Intent(JeuActivity.this,MainActivity.class));
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {

            for (Carte carte:pileCarte.getListeCartes()){
                carte.getChiffre();
            }
            if (event.getAction() == DragEvent.ACTION_DRAG_STARTED){

            }
            else if (event.getAction() == DragEvent.ACTION_DRAG_ENDED){

            }
            else if (event.getAction() == DragEvent.ACTION_DROP) {


            }
            return true;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

            if (Build.VERSION.SDK_INT <= 24)
                v.startDrag(null,shadowBuilder,v,0);
            else
                v.startDragAndDrop(null,shadowBuilder,v,0);

            carteSelectionner = (TextView)v;
            chiffreSelectionner = (String)carteSelectionner.getText();
            return true;
        }
    }



    private class EcouteurDeck implements View.OnDragListener{

        @Override
        public boolean onDrag(View v, DragEvent event) {
            return false;
        }
    }
}