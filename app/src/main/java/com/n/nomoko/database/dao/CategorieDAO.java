package com.n.nomoko.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.n.nomoko.database.infos.Categorie;

public class CategorieDAO extends DAOBase {
    public static final String KEY = "idCat";
    public static final String NAME = "nomCat";
    public static final String SIGNE = "signeCat";
    public static final String TABLE_NAME = "Categorie";

    public CategorieDAO (Context pContext) {
        super(pContext);
    }

    public long ajouter (Categorie cat) {
        ContentValues valeur = new ContentValues();
        valeur.put(CategorieDAO.NAME, cat.getNomCatID());
        valeur.put(CategorieDAO.SIGNE, cat.getSigneCat());
        return this.mDb.insert(QuestionDAO.TABLE_NAME, null, valeur);
    }

    public Categorie selectionner (long idCat) {
        Cursor c = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY + " = ?", new String[]{Long.toString(idCat)});
        if (!c.moveToFirst()) {
            c.close();
            return null;
        }
        Categorie resultat = new Categorie(idCat, c.getInt(1), c.getString(2));
        c.close();
        return resultat;
    }

    public void toutSupprimer () {
        this.mDb.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
