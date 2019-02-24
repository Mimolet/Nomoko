package com.n.nomoko.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.n.nomoko.database.infos.Categorie;
import com.n.nomoko.database.infos.Defi;
import com.n.nomoko.database.infos.Question;
import com.n.nomoko.database.infos.Trophee;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    /*TABLE CATEGORIE*/

    public static final String CATEGORIE_KEY = "idCat";
    public static final String CATEGORIE_NAME = "nomCat";
    public static final String CATEGORIE_SIGNE = "signeCat";
    public static final String CATEGORIE_TABLE_NAME = "Catégorie";
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
    public static final String DEFI_TABLE_NAME = "Defis";
    public static final String DEFI_TABLE_CREATE = "CREATE TABLE " + DEFI_TABLE_NAME + " (" + DEFI_KEY +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + DEFI_NAME + " TEXT, " + DEFI_TEXT + " TEXT, " + DEFI_DUREE + " INTEGER, " +
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
            TROPHEE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TROPHEE_NAME + " TEXT, " + TROPHEE_TEXT +
            " TEXT, " + TROPHEE_OBTENU + " BOOLEAN, " + TROPHEE_ILLU_PATH + " TEXT);";
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

        defis.add(new Defi(0, "getString(R.string.dbDefiNom1)", "getString(R.string.dbDefi1)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom2)", "getString(R.string.dbDefi2)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom3)", "getString(R.string.dbDefi3)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom4)", "getString(R.string.dbDefi4)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom5)", "getString(R.string.dbDefi5)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom6)", "getString(R.string.dbDefi6)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom7)", "getString(R.string.dbDefi7)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom8)", "getString(R.string.dbDefi8)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom9)", "getString(R.string.dbDefi9)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom10)", "getString(R.string.dbDefi10)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom11)", "getString(R.string.dbDefi11)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom12)", "getString(R.string.dbDefi12)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom13)", "getString(R.string.dbDefi13)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom14)", "getString(R.string.dbDefi14)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom15)", "getString(R.string.dbDefi15)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom16)", "getString(R.string.dbDefi16)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom17)", "getString(R.string.dbDefi17)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom18)", "getString(R.string.dbDefi18)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom19)", "getString(R.string.dbDefi19)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom20)", "getString(R.string.dbDefi20)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom21)", "getString(R.string.dbDefi21)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom22)", "getString(R.string.dbDefi22)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom23)", "getString(R.string.dbDefi23)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom24)", "getString(R.string.dbDefi24)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom25)", "getString(R.string.dbDefi25)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom26)", "getString(R.string.dbDefi26)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom27)", "getString(R.string.dbDefi27)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom28)", "getString(R.string.dbDefi28)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom29)", "getString(R.string.dbDefi29)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom30)", "getString(R.string.dbDefi30)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom31)", "getString(R.string.dbDefi31)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom32)", "getString(R.string.dbDefi32)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom33)", "getString(R.string.dbDefi33)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom34)", "getString(R.string.dbDefi34)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom35)", "getString(R.string.dbDefi35)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom36)", "getString(R.string.dbDefi36)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom37)", "getString(R.string.dbDefi37)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom38)", "getString(R.string.dbDefi38)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom39)", "getString(R.string.dbDefi39)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom40)", "getString(R.string.dbDefi40)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom41)", "getString(R.string.dbDefi41)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom42)", "getString(R.string.dbDefi42)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom43)", "getString(R.string.dbDefi43)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom44)", "getString(R.string.dbDefi44)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom45)", "getString(R.string.dbDefi45)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom46)", "getString(R.string.dbDefi46)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom47)", "getString(R.string.dbDefi47)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom48)", "getString(R.string.dbDefi48)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom49)", "getString(R.string.dbDefi49)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom50)", "getString(R.string.dbDefi50)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom51)", "getString(R.string.dbDefi51)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom52)", "getString(R.string.dbDefi52)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom53)", "getString(R.string.dbDefi53)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom54)", "getString(R.string.dbDefi54)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom55)", "getString(R.string.dbDefi55)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom56)", "getString(R.string.dbDefi56)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom57)", "getString(R.string.dbDefi57)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom58)", "getString(R.string.dbDefi58)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom59)", "getString(R.string.dbDefi59)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom60)", "getString(R.string.dbDefi60)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom61)", "getString(R.string.dbDefi61)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom62)", "getString(R.string.dbDefi62)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom63)", "getString(R.string.dbDefi63)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom64)", "getString(R.string.dbDefi64)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom65)", "getString(R.string.dbDefi65)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom66)", "getString(R.string.dbDefi66)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom67)", "getString(R.string.dbDefi67)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom68)", "getString(R.string.dbDefi68)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom69)", "getString(R.string.dbDefi69)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom70)", "getString(R.string.dbDefi70)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom71)", "getString(R.string.dbDefi71)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom72)", "getString(R.string.dbDefi72)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom73)", "getString(R.string.dbDefi73)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom74)", "getString(R.string.dbDefi74)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom75)", "getString(R.string.dbDefi75)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom76)", "getString(R.string.dbDefi76)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom77)", "getString(R.string.dbDefi77)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom78)", "getString(R.string.dbDefi78)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom79)", "getString(R.string.dbDefi79)", 0, "Esprit", "Introverti", false, "", 1));
        defis.add(new Defi(0, "getString(R.string.dbDefiNom80)", "getString(R.string.dbDefi80)", 0, "Esprit", "Introverti", false, "", 1));

        for (Defi d : defis) {
            ContentValues valeur = new ContentValues();
            valeur.put(DEFI_NAME, d.getNomDefi());
            valeur.put(DEFI_TEXT, d.getTxtDefi());
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

        categories.add(new Categorie(0, "getString(R.string.dbCatEsprit)", "+"));
        categories.add(new Categorie(0, "getString(R.string.dbCatEsprit)", "-"));
        categories.add(new Categorie(0, "getString(R.string.dbCatEnergie)", "+"));
        categories.add(new Categorie(0, "getString(R.string.dbCatEnergie)", "-"));
        categories.add(new Categorie(0, "getString(R.string.dbCatNature)", "+"));
        categories.add(new Categorie(0, "getString(R.string.dbCatNature)", "-"));
        categories.add(new Categorie(0, "getString(R.string.dbCatTactique)", "+"));
        categories.add(new Categorie(0, "getString(R.string.dbCatTactique)", "-"));

        for (int i = 0; i < idCat.length; i++) {
            ContentValues valeur = new ContentValues();
            valeur.put(CATEGORIE_NAME, categories.get(i).getNomCat());
            valeur.put(CATEGORIE_SIGNE, categories.get(i).getSigneCat());
            idCat[i] = db.insert(CATEGORIE_TABLE_NAME, null, valeur);
        }
    }

    private void ajouterQuestions (SQLiteDatabase db) {
        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question(0, "getString(R.string.dbQuest1)", idCat[0], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest2)", idCat[2], idCat[4]));
        questions.add(new Question(0, "getString(R.string.dbQuest3)", idCat[1], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest4)", idCat[2], idCat[7]));
        questions.add(new Question(0, "getString(R.string.dbQuest5)", idCat[3], idCat[4]));
        questions.add(new Question(0, "getString(R.string.dbQuest6)", idCat[6], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest7)", idCat[5], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest8)", idCat[0], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest9)", idCat[3], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest10)", idCat[0], idCat[5]));
        questions.add(new Question(0, "getString(R.string.dbQuest11)", idCat[6], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest12)", idCat[4], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest13)", idCat[7], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest14)", idCat[1], idCat[4]));
        questions.add(new Question(0, "getString(R.string.dbQuest15)", idCat[1], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest16)", idCat[3], idCat[4]));
        questions.add(new Question(0, "getString(R.string.dbQuest17)", idCat[4], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest18)", idCat[0], idCat[3]));
        questions.add(new Question(0, "getString(R.string.dbQuest19)", idCat[7], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest20)", idCat[0], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest21)", idCat[2], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest22)", idCat[4], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest23)", idCat[5], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest24)", idCat[7], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest25)", idCat[4], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest26)", idCat[2], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest27)", idCat[2], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest28)", idCat[3], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest29)", idCat[0], idCat[5]));
        questions.add(new Question(0, "getString(R.string.dbQuest30)", idCat[7], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest31)", idCat[5], -1));
        questions.add(new Question(0, "getString(R.string.dbQuest32)", idCat[0], -1));

        for (Question q : questions) {
            ContentValues valeur = new ContentValues();
            valeur.put(QUESTION_TEXT, q.getTexte());
            valeur.put(QUESTION_CAT1, q.getIdCat1());
            valeur.put(QUESTION_CAT2, q.getIdCat2());
            db.insert(QUESTION_TABLE_NAME, null, valeur);
        }
    }

    private void ajouterTrophees (SQLiteDatabase db) {
        ArrayList<Trophee> trophees = new ArrayList<>();

        trophees.add(new Trophee(0, "getString(R.string.dbTroNom1)", "getString(R.string.dbTro1)", false, ""));
        trophees.add(new Trophee(0, "getString(R.string.dbTroNom2)", "getString(R.string.dbTro2)", false, ""));
        trophees.add(new Trophee(0, "etString(R.string.dbTroNom3)", "getString(R.string.dbTro3)", false, ""));
        trophees.add(new Trophee(0, "getString(R.string.dbTroNom4)", "getString(R.string.dbTro4)", false, ""));
        trophees.add(new Trophee(0, "getString(R.string.dbTroNom5)", "getString(R.string.dbTro5)", false, ""));
        trophees.add(new Trophee(0, "getString(R.string.dbTroNom6)", "getString(R.string.dbTro6)", false, ""));
        trophees.add(new Trophee(0, "getString(R.string.dbTroNom7)", "getString(R.string.dbTro7)", false, ""));
        trophees.add(new Trophee(0, "getString(R.string.dbTroNom8)", "getString(R.string.dbTro8)", false, ""));
        trophees.add(new Trophee(0, "getString(R.string.dbTroNom9)", "getString(R.string.dbTro9)", false, ""));
        trophees.add(new Trophee(0, "getString(R.string.dbTroNom10)", "getString(R.string.dbTro10)", false, ""));
        trophees.add(new Trophee(0, "getString(R.string.dbTroNom11)", "getString(R.string.dbTro11)", false, ""));
        trophees.add(new Trophee(0, "getString(R.string.dbTroNom12)", "getString(R.string.dbTro12)", false, ""));
        trophees.add(new Trophee(0, "getString(R.string.dbTroNom13)", "getString(R.string.dbTro13)", false, ""));
        trophees.add(new Trophee(0, "etString(R.string.dbTroNom14)", "getString(R.string.dbTro14)", false, ""));
        trophees.add(new Trophee(0, "getString(R.string.dbTroNom15)", "getString(R.string.dbTro15)", false, ""));

        for (Trophee t : trophees) {
            ContentValues valeur = new ContentValues();
            valeur.put(TROPHEE_NAME, t.getNomTro());
            valeur.put(TROPHEE_TEXT, t.getTxtTro());
            valeur.put(TROPHEE_OBTENU, t.isObtenuTro());
            valeur.put(TROPHEE_ILLU_PATH, t.getIllustrationPathTro());
            db.insert(TROPHEE_TABLE_NAME, null, valeur);
        }
    }
}
