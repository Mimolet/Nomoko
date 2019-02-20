package com.n.nomoko.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.n.nomoko.database.infos.Recompense;

public class RecompenseDAO extends DAOBase {
    public static final String KEY = "idRecompense";
    public static final String TEXTE = "txtRec";
    public static final String OBTENUE = "obtenuRec";
    public static final String TABLE_NAME = "Récompenses";

    public RecompenseDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter (Recompense r) {
        ContentValues valeur = new ContentValues();
        valeur.put(RecompenseDAO.TEXTE, r.getTxtRec());
        valeur.put(RecompenseDAO.OBTENUE, r.isObtenuRec());
        this.mDb.insert(DefisDAO.TABLE_NAME, null, valeur);
    }

    public Recompense selectionner (long idRecompense) {
        Cursor c = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY + " = ?", new String[]{Long.toString(idRecompense)});
        if (!c.moveToFirst()) {
            c.close();
            return null;
        }
        Recompense resultat = new Recompense(idRecompense, c.getString(1), c.getInt(2) == 1);
        c.close();
        return resultat;
    }
}
