package com.n.nomoko.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.n.nomoko.database.infos.Categorie;
import com.n.nomoko.database.infos.Defi;
import com.n.nomoko.database.infos.Question;
import com.n.nomoko.database.infos.Trophee;

import com.n.nomoko.*;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    /*TABLE CATEGORIE*/

    public static final String CATEGORIE_KEY = "idCat";
    public static final String CATEGORIE_NAME = "nomCat";
    public static final String CATEGORIE_SIGNE = "signeCat";
    public static final String CATEGORIE_TABLE_NAME = "Catégorie";
    public static final String CATEGORIE_TABLE_CREATE = "CREATE TABLE " + CATEGORIE_TABLE_NAME +
            " (" + CATEGORIE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CATEGORIE_NAME + " INTEGER, " +
            CATEGORIE_SIGNE + " TEXT);";
    public static final String CATEGORIE_TABLE_DROP = "DROP TABLE IF EXISTS " + CATEGORIE_TABLE_NAME + ";";

    /*TABLE QUESTION*/

    public static final String QUESTION_KEY = "idQuest";
    public static final String QUESTION_TEXT = "txtQuest";
    public static final String QUESTION_CAT1 = "idCat1";
    public static final String QUESTION_CAT2 = "idCat2";
    public static final String QUESTION_TABLE_NAME = "Question";
    public static final String QUESTION_TABLE_CREATE = "CREATE TABLE " + QUESTION_TABLE_NAME + " ("
            + QUESTION_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION_TEXT + " INTEGER, " +
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
    public static final String DEFI_TABLE_NAME = "Defis";
    public static final String DEFI_TABLE_CREATE = "CREATE TABLE " + DEFI_TABLE_NAME + " (" + DEFI_KEY +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + DEFI_NAME + " INTEGER, " + DEFI_TEXT + " INTEGER, " + DEFI_DUREE + " INTEGER, " +
            DEFI_CAT + " TEXT, " + DEFI_TRAIT + " TEXT, " + DEFI_REUSSI + " BOOLEAN, " + DEFI_ILLU_PATH +
            " TEXT, " + DEFI_DIFFICULTE + " INTEGER);";
    public static final String DEFI_TABLE_DROP = "DROP TABLE IF EXISTS " + DEFI_TABLE_NAME + ";";

    /*TABLE TROPHEES*/

    public static final String TROPHEE_KEY = "idTrophee";
    public static final String TROPHEE_NAME = "nomTro";
    public static final String TROPHEE_TEXT = "txTro";
    public static final String TROPHEE_OBTENU = "obtenuTro";
    public static final String TROPHEE_ILLU_PATH = "illustrationPathTro";
    public static final String TROPHEE_TABLE_NAME = "Trophees";
    public static final String TROPHEE_TABLE_CREATE = "CREATE TABLE " + TROPHEE_TABLE_NAME + " (" +
            TROPHEE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TROPHEE_NAME + " INTEGER, " + TROPHEE_TEXT +
            " INTEGER, " + TROPHEE_OBTENU + " BOOLEAN, " + TROPHEE_ILLU_PATH + " TEXT);";
    public static final String TROPHEE_TABLE_DROP = "DROP TABLE IF EXISTS  " + TROPHEE_TABLE_NAME + ";";

    /*TABLE RECOMPENSES*/

    public static final String RECOM_KEY = "idRecompense";
    public static final String RECOM_TEXTE = "txtRec";
    public static final String RECOM_OBTENUE = "obtenuRec";
    public static final String RECOM_TABLE_NAME = "Recompenses";
    public static final String RECOM_TABLE_CREATE = "CREATE TABLE " + RECOM_TABLE_NAME + " (" +
            RECOM_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RECOM_TEXTE + " TEXT, " + RECOM_OBTENUE +
            " BOOLEAN);";
    public static final String RECOM_TABLE_DROP = "DROP TABLE IF EXISTS " + RECOM_TABLE_NAME + ";";

    /*Variables auxiliaires*/

        /*IDs des catégories*/
    private long [] idCat = new long[8];

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
        this.ajouterCategories(db);
        this.ajouterQuestions(db);
        this.ajouterDefis(db);
        this.ajouterTrophees(db);
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

    private void ajouterDefis (SQLiteDatabase db) {
        ArrayList<Defi> defis = new ArrayList<Defi>();

        defis.add(new Defi(0, R.string.dbDefiNom1, R.string.dbDefi1, 0, "Esprit", "I", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom2, R.string.dbDefi2, 0, "Esprit", "I", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom3, R.string.dbDefi3, 0, "Esprit", "I", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom4, R.string.dbDefi4, 0, "Esprit", "I", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom5, R.string.dbDefi5, 0, "Esprit", "I", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom6, R.string.dbDefi6, 0, "Esprit", "I", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom7, R.string.dbDefi7, 0, "Esprit", "I", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom8, R.string.dbDefi8, 0, "Esprit", "I", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom9, R.string.dbDefi9, 0, "Esprit", "I", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom10, R.string.dbDefi10, 0, "Esprit", "I", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom11, R.string.dbDefi11, 0, "Esprit", "E", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom12, R.string.dbDefi12, 0, "Esprit", "E", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom13, R.string.dbDefi13, 0, "Esprit", "E", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom14, R.string.dbDefi14, 0, "Esprit", "E", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom15, R.string.dbDefi15, 0, "Esprit", "E", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom16, R.string.dbDefi16, 0, "Esprit", "E", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom17, R.string.dbDefi17, 0, "Esprit", "E", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom18, R.string.dbDefi18, 0, "Esprit", "E", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom19, R.string.dbDefi19, 0, "Esprit", "E", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom20, R.string.dbDefi20, 0, "Esprit", "E", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom21, R.string.dbDefi21, 0, "Energie", "S", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom22, R.string.dbDefi22, 0, "Energie", "S", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom23, R.string.dbDefi23, 0, "Energie", "S", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom24, R.string.dbDefi24, 0, "Energie", "S", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom25, R.string.dbDefi25, 0, "Energie", "S", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom26, R.string.dbDefi26, 0, "Energie", "S", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom27, R.string.dbDefi27, 0, "Energie", "S", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom28, R.string.dbDefi28, 0, "Energie", "S", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom29, R.string.dbDefi29, 0, "Energie", "S", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom30, R.string.dbDefi30, 0, "Energie", "S", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom31, R.string.dbDefi31, 0, "Energie", "N", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom32, R.string.dbDefi32, 0, "Energie", "N", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom33, R.string.dbDefi33, 0, "Energie", "N", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom34, R.string.dbDefi34, 0, "Energie", "N", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom35, R.string.dbDefi35, 0, "Energie", "N", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom36, R.string.dbDefi36, 0, "Energie", "N", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom37, R.string.dbDefi37, 0, "Energie", "N", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom38, R.string.dbDefi38, 0, "Energie", "N", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom39, R.string.dbDefi39, 0, "Energie", "N", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom40, R.string.dbDefi40, 0, "Energie", "N", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom41, R.string.dbDefi41, 0, "Nature", "T", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom42, R.string.dbDefi42, 0, "Nature", "T", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom43, R.string.dbDefi43, 0, "Nature", "T", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom44, R.string.dbDefi44, 0, "Nature", "T", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom45, R.string.dbDefi45, 0, "Nature", "T", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom46, R.string.dbDefi46, 0, "Nature", "T", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom47, R.string.dbDefi47, 0, "Nature", "T", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom48, R.string.dbDefi48, 0, "Nature", "T", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom49, R.string.dbDefi49, 0, "Nature", "T", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom50, R.string.dbDefi50, 0, "Nature", "T", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom51, R.string.dbDefi51, 0, "Nature", "F", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom52, R.string.dbDefi52, 0, "Nature", "F", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom53, R.string.dbDefi53, 0, "Nature", "F", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom54, R.string.dbDefi54, 0, "Nature", "F", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom55, R.string.dbDefi55, 0, "Nature", "F", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom56, R.string.dbDefi56, 0, "Nature", "F", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom57, R.string.dbDefi57, 0, "Nature", "F", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom58, R.string.dbDefi58, 0, "Nature", "F", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom59, R.string.dbDefi59, 0, "Nature", "F", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom60, R.string.dbDefi60, 0, "Nature", "F", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom61, R.string.dbDefi61, 0, "Tactique", "J", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom62, R.string.dbDefi62, 0, "Tactique", "J", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom63, R.string.dbDefi63, 0, "Tactique", "J", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom64, R.string.dbDefi64, 0, "Tactique", "J", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom65, R.string.dbDefi65, 0, "Tactique", "J", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom66, R.string.dbDefi66, 0, "Tactique", "J", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom67, R.string.dbDefi67, 0, "Tactique", "J", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom68, R.string.dbDefi68, 0, "Tactique", "J", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom69, R.string.dbDefi69, 0, "Tactique", "J", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom70, R.string.dbDefi70, 0, "Tactique", "J", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom71, R.string.dbDefi71, 0, "Tactique", "P", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom72, R.string.dbDefi72, 0, "Tactique", "P", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom73, R.string.dbDefi73, 0, "Tactique", "P", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom74, R.string.dbDefi74, 0, "Tactique", "P", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom75, R.string.dbDefi75, 0, "Tactique", "P", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom76, R.string.dbDefi76, 0, "Tactique", "P", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom77, R.string.dbDefi77, 0, "Tactique", "P", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom78, R.string.dbDefi78, 0, "Tactique", "P", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom79, R.string.dbDefi79, 0, "Tactique", "P", false, "", 1));
        defis.add(new Defi(0, R.string.dbDefiNom80, R.string.dbDefi80, 0, "Tactique", "P", false, "", 1));

        for (Defi d : defis) {
            ContentValues valeur = new ContentValues();
            valeur.put(DEFI_NAME, d.getNomDefiID());
            valeur.put(DEFI_TEXT, d.getTxtDefiID());
            valeur.put(DEFI_DUREE, d.getDureeDefi());
            valeur.put(DEFI_CAT, d.getCategorieDefi());
            valeur.put(DEFI_TRAIT, d.getTraitDefi());
            valeur.put(DEFI_REUSSI, d.isReussiDefi());
            valeur.put(DEFI_ILLU_PATH, d.getIllustrationPathDefi());
            valeur.put(DEFI_DIFFICULTE, d.getDifficulte());
            db.insert(DEFI_TABLE_NAME, null, valeur);
        }
    }

    private void ajouterCategories (SQLiteDatabase db) {
        ArrayList<Categorie> categories = new ArrayList<>();

        categories.add(new Categorie(0, R.string.dbCatEsprit, "+"));
        categories.add(new Categorie(0, R.string.dbCatEsprit, "-"));
        categories.add(new Categorie(0, R.string.dbCatEnergie, "+"));
        categories.add(new Categorie(0, R.string.dbCatEnergie, "-"));
        categories.add(new Categorie(0, R.string.dbCatNature, "+"));
        categories.add(new Categorie(0, R.string.dbCatNature, "-"));
        categories.add(new Categorie(0, R.string.dbCatTactique, "+"));
        categories.add(new Categorie(0, R.string.dbCatTactique, "-"));

        for (int i = 0; i < idCat.length; i++) {
            ContentValues valeur = new ContentValues();
            valeur.put(CATEGORIE_NAME, categories.get(i).getNomCatID());
            valeur.put(CATEGORIE_SIGNE, categories.get(i).getSigneCat());
            idCat[i] = db.insert(CATEGORIE_TABLE_NAME, null, valeur);
        }
    }

    private void ajouterQuestions (SQLiteDatabase db) {
        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question(0, R.string.dbQuest1, idCat[0], -1));
        questions.add(new Question(0, R.string.dbQuest2, idCat[2], idCat[4]));
        questions.add(new Question(0, R.string.dbQuest3, idCat[1], -1));
        questions.add(new Question(0, R.string.dbQuest4, idCat[2], idCat[7]));
        questions.add(new Question(0, R.string.dbQuest5, idCat[3], idCat[4]));
        questions.add(new Question(0, R.string.dbQuest6, idCat[6], -1));
        questions.add(new Question(0, R.string.dbQuest7, idCat[5], -1));
        questions.add(new Question(0, R.string.dbQuest8, idCat[0], -1));
        questions.add(new Question(0, R.string.dbQuest9, idCat[3], -1));
        questions.add(new Question(0, R.string.dbQuest10, idCat[0], idCat[5]));
        questions.add(new Question(0, R.string.dbQuest11, idCat[6], -1));
        questions.add(new Question(0, R.string.dbQuest12, idCat[4], -1));
        questions.add(new Question(0, R.string.dbQuest13, idCat[7], -1));
        questions.add(new Question(0, R.string.dbQuest14, idCat[1], idCat[4]));
        questions.add(new Question(0, R.string.dbQuest15, idCat[1], -1));
        questions.add(new Question(0, R.string.dbQuest16, idCat[3], idCat[4]));
        questions.add(new Question(0, R.string.dbQuest17, idCat[4], -1));
        questions.add(new Question(0, R.string.dbQuest18, idCat[0], idCat[3]));
        questions.add(new Question(0, R.string.dbQuest19, idCat[7], -1));
        questions.add(new Question(0, R.string.dbQuest20, idCat[0], -1));
        questions.add(new Question(0, R.string.dbQuest21, idCat[2], -1));
        questions.add(new Question(0, R.string.dbQuest22, idCat[4], -1));
        questions.add(new Question(0, R.string.dbQuest23, idCat[5], -1));
        questions.add(new Question(0, R.string.dbQuest24, idCat[7], -1));
        questions.add(new Question(0, R.string.dbQuest25, idCat[4], -1));
        questions.add(new Question(0, R.string.dbQuest26, idCat[2], -1));
        questions.add(new Question(0, R.string.dbQuest27, idCat[2], -1));
        questions.add(new Question(0, R.string.dbQuest28, idCat[3], -1));
        questions.add(new Question(0, R.string.dbQuest29, idCat[0], idCat[5]));
        questions.add(new Question(0, R.string.dbQuest30, idCat[7], -1));
        questions.add(new Question(0, R.string.dbQuest31, idCat[5], -1));
        questions.add(new Question(0, R.string.dbQuest32, idCat[0], -1));

        for (Question q : questions) {
            ContentValues valeur = new ContentValues();
            valeur.put(QUESTION_TEXT, q.getTexteID());
            valeur.put(QUESTION_CAT1, q.getIdCat1());
            valeur.put(QUESTION_CAT2, q.getIdCat2());
            db.insert(QUESTION_TABLE_NAME, null, valeur);
        }
    }

    private void ajouterTrophees (SQLiteDatabase db) {
        ArrayList<Trophee> trophees = new ArrayList<>();

        trophees.add(new Trophee(0, R.string.dbTroNom1, R.string.dbTro1, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom2, R.string.dbTro2, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom3, R.string.dbTro3, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom4, R.string.dbTro4, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom5, R.string.dbTro5, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom6, R.string.dbTro6, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom7, R.string.dbTro7, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom8, R.string.dbTro8, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom9, R.string.dbTro9, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom10, R.string.dbTro10, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom11, R.string.dbTro11, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom12, R.string.dbTro12, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom13, R.string.dbTro13, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom14, R.string.dbTro14, false, ""));
        trophees.add(new Trophee(0, R.string.dbTroNom15, R.string.dbTro15, false, ""));

        for (Trophee t : trophees) {
            ContentValues valeur = new ContentValues();
            valeur.put(TROPHEE_NAME, t.getNomTroID());
            valeur.put(TROPHEE_TEXT, t.getTxtTroID());
            valeur.put(TROPHEE_OBTENU, t.isObtenuTro());
            valeur.put(TROPHEE_ILLU_PATH, t.getIllustrationPathTro());
            db.insert(TROPHEE_TABLE_NAME, null, valeur);
        }
    }
}
