package com.example.a97cards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JeuActivity extends AppCompatActivity {

    // Les variables front-end
    LinearLayout parent,conteneurChiffreEntête,
                 conteneurAscendant,conteneurAscendantGauche,
                 conteneurAscendantDroite,conteneurDescendant,
                 conteneurDescendantGauche,conteneurDescedantDroite;
    GridLayout deckCartes;
    Chronometer chronometer;
    Ecouteur ec;
    Button menu;

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


        // Test du chronomètre
        chronometer.start();

        // Test du score
        //DB.getInstance(this).ajoutScore(DB.getInstance(this).getReadableDatabase(), new Score(90));


        // Test du bouton
        menu.setOnClickListener(ec);

    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (v.equals(menu))
                startActivity(new Intent(JeuActivity.this,MainActivity.class));
        }
    }
}