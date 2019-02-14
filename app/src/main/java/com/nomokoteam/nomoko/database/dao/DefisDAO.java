package com.nomokoteam.nomoko.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nomokoteam.nomoko.database.infos.Defi;

public class DefisDAO extends DAOBase {
    public static final String KEY = "idDefi";
    public static final String NAME = "nomDefi";
    public static final String TEXT = "txtDefi";
    public static final String DUREE = "dureeDefi";
    public static final String CAT = "categorieDefi";
    public static final String TRAIT = "traitDefi";
    public static final String REUSSI = "reussiDefi";
    public static final String ILLU_PATH = "illustrationPathDefi";
    public static final String DIFFICULTE = "difficulte";
    public static final String TABLE_NAME = "Défis";


    public DefisDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter (Defi d) {
        ContentValues valeur = new ContentValues();
        valeur.put(DefisDAO.NAME, d.getNomDefi());
        valeur.put(DefisDAO.TEXT, d.getTxtDefi());
        valeur.put(DefisDAO.DUREE, d.getDureeDefi());
        valeur.put(DefisDAO.CAT, d.getCategorieDefi());
        valeur.put(DefisDAO.TRAIT, d.getTraitDefi());
        valeur.put(DefisDAO.REUSSI, d.isReussiDefi());
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
        Defi resultat = new Defi(idDefi, c.getString(1), c.getString(2),
                c.getInt(3), c.getString(4), c.getString(5),
                c.getInt(6) == 1, c.getString(7), c.getInt(8));
        c.close();
        return resultat;
    }
}
