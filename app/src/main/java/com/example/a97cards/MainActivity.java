package com.example.a97cards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Les variables
    Button commencerPartie;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        commencerPartie = findViewById(R.id.buttonPlay);
        ec = new Ecouteur();

        commencerPartie.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // si le joueur clicke sur une nouvelle partie, alors il sera rediriger vers la partie
            if (v.equals(commencerPartie))
                startActivity(new Intent(MainActivity.this, JeuActivity.class));
        }
    }
}