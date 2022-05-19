package com.example.a97cards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    private static DB instance;
    private SQLiteDatabase database;

    public static DB getInstance(Context contexte){

        if (instance == null)
        {
            instance = new DB(contexte.getApplicationContext());
        }
        return instance;
    }

    private DB(Context contexte){
        super(contexte,"db",null,1);
        openDB();
    }

    public void ajoutScore(SQLiteDatabase db, Score score){
        ContentValues cv = new ContentValues();
        cv.put("score", score.getScore());
        db.insert("scores",null,cv);
    }

    public int classementScores(){
        // Ici on recupère le plus grand score de la base de donnees
        // l'utilisation du MAX(nomColonne) permet de nous retourner le score le plus grand
        int plusgrosResultat = 0;
        Cursor c = database.rawQuery("SELECT MAX(score) FROM scores",null); // on cherche celui ayant le plus grand score
        c.moveToFirst();
        plusgrosResultat = c.getInt(0);
        return plusgrosResultat; // on retourne son resultat
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table scores (_id INTEGER PRIMARY KEY AUTOINCREMENT, score INTEGER UNIQUE NOT NULL);");
        ajoutScore(db,new Score(0)); // on ajoute un nouveau score au joueur
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void openDB(){
        database = this.getWritableDatabase();
    }

    public void closeDB(){
        database.close();
    }
}
