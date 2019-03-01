package com.n.nomoko.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.n.nomoko.database.infos.Defi;

import java.util.ArrayList;

public class DefisDAO extends DAOBase {
    public static final String KEY = "idDefi";
    public static final String NAME = "nomDefi";
    public static final String TEXT = "txtDefi";
    public static final String DUREE = "dureeDefi";
    public static final String CAT = "categorieDefi";
    public static final String TRAIT = "traitDefi";
    public static final String REUSSI = "reussiDefi";
    public static final String EN_COURS = "enCours";
    public static final String ILLU_PATH = "illustrationPathDefi";
    public static final String DIFFICULTE = "difficulte";
    public static final String TABLE_NAME = "Defis";


    public DefisDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter (Defi d) {
        ContentValues valeur = new ContentValues();
        valeur.put(DefisDAO.NAME, d.getNomDefiID());
        valeur.put(DefisDAO.TEXT, d.getTxtDefiID());
        valeur.put(DefisDAO.DUREE, d.getDureeDefi());
        valeur.put(DefisDAO.CAT, d.getCategorieDefi());
        valeur.put(DefisDAO.TRAIT, d.getTraitDefi());
        valeur.put(DefisDAO.REUSSI, d.isReussiDefi());
        valeur.put(DefisDAO.EN_COURS, d.isEnCours());
        valeur.put(DefisDAO.ILLU_PATH, d.getIllustrationPathDefi());
        valeur.put(DefisDAO.DIFFICULTE, d.getDifficulte());
        this.mDb.insert(DefisDAO.TABLE_NAME, null, valeur);
    }

    public Defi selectionner (long idDefi) {
        Cursor c = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY + " = ?", new String[]{Long.toString(idDefi)});
        if (!c.moveToFirst()) {
            c.close();
            return null;
        }
        Defi resultat = new Defi(idDefi, c.getInt(1), c.getInt(2),
                c.getInt(3), c.getString(4), c.getString(5),
                c.getInt(6) == 1, c.getInt(7) == 1, c.getInt(8), c.getInt(9));
        c.close();
        return resultat;
    }

    public ArrayList<Defi> selectionnerTousLesDefis () {
        ArrayList<Defi> resultats = new ArrayList<Defi>();
        Cursor c = mDb.rawQuery("SELECT * FROM " + TABLE_NAME, new String[]{});
        if (!c.moveToFirst()) {
            c.close();
            return null;
        }
        Defi resultat = new Defi(c.getLong(0), c.getInt(1), c.getInt(2),
                c.getInt(3), c.getString(4), c.getString(5),
                c.getInt(6) == 1, c.getInt(7) == 1, c.getInt(8), c.getInt(9));
        resultats.add(resultat);
        while(c.moveToNext()) {
            resultat = new Defi(c.getLong(0), c.getInt(1), c.getInt(2),
                    c.getInt(3), c.getString(4), c.getString(5),
                    c.getInt(6) == 1, c.getInt(7) == 1, c.getInt(8), c.getInt(9));
            resultats.add(resultat);
        }
        c.close();
        return resultats;
    }

    public void validerDefi (long id) {
        ContentValues value = new ContentValues();
        value.put(REUSSI, true);
        value.put(EN_COURS, false);
        mDb.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(id)});
    }

    public void accepterDefi (long id) {
        ContentValues value = new ContentValues();
        value.put(EN_COURS, true);
        mDb.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(id)});
    }

    public ArrayList<Defi> defisEnCours () {
        ArrayList<Defi> resultats = new ArrayList<Defi>();
        Cursor c = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + EN_COURS + " = 1", new String[]{});
        if (!c.moveToFirst()) {
            c.close();
            return null;
        }
        Defi resultat = new Defi(c.getLong(0), c.getInt(1), c.getInt(2),
                c.getInt(3), c.getString(4), c.getString(5),
                c.getInt(6) == 1, c.getInt(7) == 1, c.getInt(8), c.getInt(9));
        resultats.add(resultat);
        while(c.moveToNext()) {
            resultat = new Defi(c.getLong(0), c.getInt(1), c.getInt(2),
                    c.getInt(3), c.getString(4), c.getString(5),
                    c.getInt(6) == 1, c.getInt(7) == 1, c.getInt(8), c.getInt(9));
            resultats.add(resultat);
        }
        c.close();
        return resultats;
    }

    public ArrayList<Defi> defisTermines () {
        ArrayList<Defi> resultats = new ArrayList<Defi>();
        Cursor c = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + REUSSI + " = 1", new String[]{});
        if (!c.moveToFirst()) {
            c.close();
            return null;
        }
        Defi resultat = new Defi(c.getLong(0), c.getInt(1), c.getInt(2),
                c.getInt(3), c.getString(4), c.getString(5),
                c.getInt(6) == 1, c.getInt(7) == 1, c.getInt(8), c.getInt(9));
        resultats.add(resultat);
        while(c.moveToNext()) {
            resultat = new Defi(c.getLong(0), c.getInt(1), c.getInt(2),
                    c.getInt(3), c.getString(4), c.getString(5),
                    c.getInt(6) == 1, c.getInt(7) == 1, c.getInt(8), c.getInt(9));
            resultats.add(resultat);
        }
        c.close();
        return resultats;
    }

    public void echouerDefi (long id) {
        ContentValues value = new ContentValues();
        value.put(REUSSI, false);
        value.put(EN_COURS, false);
        mDb.update(TABLE_NAME, value, KEY + " = ?", new String[]{String.valueOf(id)});
    }

    public void toutSupprimer () {
        this.mDb.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
