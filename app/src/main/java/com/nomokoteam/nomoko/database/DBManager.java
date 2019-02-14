package com.nomokoteam.nomoko.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    /*TABLE CATEGORIE*/

    public static final String CATEGORIE_KEY = "idCat";
    public static final String CATEGORIE_NAME = "nomCat";
    public static final String CATEGORIE_SIGNE = "signeCat";
    public static final String CATEGORIE_TABLE_NAME = "Categorie";
    public static final String CATEGORIE_TABLE_CREATE = "CREATE TABLE " + CATEGORIE_TABLE_NAME +
            " (" + CATEGORIE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORIE_NAME + " TEXT, " +
            CATEGORIE_SIGNE + " TEXT);";
    public static final String CATEGORIE_TABLE_DROP = "DROP TABLE IF EXISTS " + CATEGORIE_TABLE_NAME + ";";

    /*TABLE QUESTION*/

    public static final String QUESTION_KEY = "idQuest";
    public static final String QUESTION_TEXT = "txtQuest";
    public static final String QUESTION_CAT1 = "idCat1";
    public static final String QUESTION_CAT2 = "idCat2";
    public static final String QUESTION_TABLE_NAME = "Question";
    public static final String QUESTION_TABLE_CREATE = "CREATE TABLE " + QUESTION_TABLE_NAME + " ("
            + QUESTION_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION_TEXT + " TEXT, " +
            QUESTION_CAT1 + " INTEGER, " + QUESTION_CAT2 + " INTEGER, " + "FOREIGN KEY (" +
            QUESTION_CAT1 + ") REFERENCES " + CATEGORIE_TABLE_NAME + " (" + CATEGORIE_KEY + "), "
            + "FOREIGN KEY (" + QUESTION_CAT2 + ") REFERENCES " + CATEGORIE_TABLE_NAME + " (" + CATEGORIE_KEY + ")); ";
    public static final String QUESTION_TABLE_DROP = "DROP TABLE IF EXISTS " + QUESTION_TABLE_NAME + ";";


    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CATEGORIE_TABLE_CREATE);
        db.execSQL(QUESTION_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUESTION_TABLE_DROP);
        db.execSQL(CATEGORIE_TABLE_DROP);
        onCreate(db);
    }
}
