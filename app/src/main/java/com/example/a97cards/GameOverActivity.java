package com.example.a97cards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    Button rejouer;
    TextView scoreFinal;
    Ecouteur ec;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        rejouer = findViewById(R.id.buttonRejouer);
        scoreFinal = findViewById(R.id.textViewScoreFinal);
        ec = new Ecouteur();
        db = DB.getInstance(this);

        // on recoit le data de l'activite source
        // on ajoute le resultat à la base de donnee, car c'est une fin de partie
        // transtipe le score dans le text view concerner
        String scoreString = getIntent().getExtras().getString("SCORE");
        db.ajoutScore(db.getWritableDatabase(), new Score(Integer.parseInt(scoreString)));
        scoreFinal.setText("Votre score : " + scoreString);


        rejouer.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (v.equals(rejouer))
                startActivity(new Intent(GameOverActivity.this,JeuActivity.class));
        }
    }
}