package com.nomokoteam.nomoko.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nomokoteam.nomoko.database.infos.Trophee;

public class TropheesDAO extends DAOBase {
    public static final String KEY = "idTrophee";
    public static final String NAME = "nomTro";
    public static final String TEXT = "txTro";
    public static final String OBTENU = "obtenuTro";
    public static final String ILLU_PATH = "illustrationPathTro";
    public static final String TABLE_NAME = "Trophées";

    public TropheesDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter (Trophee t) {
        ContentValues valeur = new ContentValues();
        valeur.put(TropheesDAO.NAME, t.getNomTro());
        valeur.put(TropheesDAO.TEXT, t.getTxtTro());
        valeur.put(TropheesDAO.OBTENU, t.isObtenuTro());
        valeur.put(TropheesDAO.ILLU_PATH, t.getIllustrationPathTro());
        this.mDb.insert(TropheesDAO.TABLE_NAME, null, valeur);
    }

    public Trophee selectionner (long idTrophee) {
        Cursor c = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY + " = ?", new String[]{Long.toString(idTrophee)});
        if (!c.moveToFirst()) {
            c.close();
            return null;
        }
        Trophee resultat = new Trophee(idTrophee, c.getString(1), c.getString(2), c.getInt(3) == 1, c.getString(4));
        c.close();
        return resultat;
    }
}
