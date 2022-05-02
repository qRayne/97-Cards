package com.example.a97cards;

import android.content.Context;
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
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
