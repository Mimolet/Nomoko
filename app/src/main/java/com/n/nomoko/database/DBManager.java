package com.n.nomoko.database;

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

    /*TABLE DEFIS*/

    public static final String DEFI_KEY = "idDefi";
    public static final String DEFI_NAME = "nomDefi";
    public static final String DEFI_TEXT = "txtDefi";
    public static final String DEFI_DUREE = "dureeDefi";
    public static final String DEFI_CAT = "categorieDefi";
    public static final String DEFI_TRAIT = "traitDefi";
    public static final String DEFI_REUSSI = "reussiDefi";
    public static final String DEFI_ILLU_PATH = "illustrationPathDefi";
    public static final String DEFI_DIFFICULTE = "difficulte";
    public static final String DEFI_TABLE_NAME = "Défis";
    public static final String DEFI_TABLE_CREATE = "CREATE TABLE " + DEFI_TABLE_NAME + " (" + DEFI_KEY +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + DEFI_TEXT + " TEXT, " + DEFI_DUREE + " INTEGER, " +
            DEFI_CAT + " TEXT, " + DEFI_TRAIT + " TEXT, " + DEFI_REUSSI + " BOOLEAN, " + DEFI_ILLU_PATH +
            " TEXT, " + DEFI_DIFFICULTE + " INTEGER);";
    public static final String DEFI_TABLE_DROP = "DROP TABLE IF EXISTS " + DEFI_TABLE_NAME + ";";

    /*TABLE TROPHEES*/

    public static final String TROPHEE_KEY = "idTrophee";
    public static final String TROPHEE_NAME = "nomTro";
    public static final String TROPHEE_TEXT = "txTro";
    public static final String TROPHEE_OBTENU = "obtenuTro";
    public static final String TROPHEE_ILLU_PATH = "illustrationPathTro";
    public static final String TROPHEE_TABLE_NAME = "Trophées";
    public static final String TROPHEE_TABLE_CREATE = "CREATE TABLE " + TROPHEE_TABLE_NAME + " (" +
            TROPHEE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TROPHEE_NAME + " TEXT, " + TROPHEE_TEXT +
            " TEXT, " + TROPHEE_OBTENU + " BOOLEAN, " + TROPHEE_ILLU_PATH + " TEXT);";
    public static final String TROPHEE_TABLE_DROP = "DROP TABLE IF EXISTS  " + TROPHEE_TABLE_NAME + ";";

    /*TABLE RECOMPENSES*/

    public static final String RECOM_KEY = "idRecompense";
    public static final String RECOM_TEXTE = "txtRec";
    public static final String RECOM_OBTENUE = "obtenuRec";
    public static final String RECOM_TABLE_NAME = "Récompenses";
    public static final String RECOM_TABLE_CREATE = "CREATE TABLE " + RECOM_TABLE_NAME + " (" +
            RECOM_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RECOM_TEXTE + " TEXT, " + RECOM_OBTENUE +
            " BOOLEAN);";
    public static final String RECOM_TABLE_DROP = "DROP TABLE IF EXISTS " + RECOM_TABLE_NAME + ";";

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CATEGORIE_TABLE_CREATE);
        db.execSQL(QUESTION_TABLE_CREATE);
        db.execSQL(DEFI_TABLE_CREATE);
        db.execSQL(TROPHEE_TABLE_CREATE);
        db.execSQL(RECOM_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUESTION_TABLE_DROP);
        db.execSQL(CATEGORIE_TABLE_DROP);
        db.execSQL(DEFI_TABLE_DROP);
        db.execSQL(TROPHEE_TABLE_DROP);
        db.execSQL(RECOM_TABLE_DROP);
        onCreate(db);
    }
}
