package com.nomokoteam.nomoko.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class QuestionDAO extends DAOBase {
    public static final String TABLE_NAME = "Question";
    public static final String KEY = "idQuest";
    public static final String TEXT = "txtQuest";
    public static final String CAT1 = "idCat1";
    public static final String CAT2 = "idCat2";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public QuestionDAO(Context pContext) {
        super(pContext);
    }

    /*Ajout d'une question*/
    public void ajouter (Question q) {
        ContentValues valeur = new ContentValues();
        valeur.put(QuestionDAO.TEXT, q.getTexte());
        valeur.put(QuestionDAO.CAT1, q.getIdCat1());
        valeur.put(QuestionDAO.CAT2, q.getIdCat2());
        this.mDb.insert(QuestionDAO.TABLE_NAME, null, valeur);
    }

    /*Suppression de la question*/
    public void supprimer (long idQuest) {
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[]{String.valueOf(idQuest)});
    }

    /*Modification de la question*/
    public void modifierTexte (Question q) {
        ContentValues valeur = new ContentValues();
        valeur.put(TEXT, q.getTexte());
        mDb.update(TABLE_NAME, valeur, KEY + " = ?", new String[] {String.valueOf(q.getIdQuest())});
    }

    /*Sélection de la question*/
    public Question selectionner (long idQuest) {
        Cursor c = this.mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID == ?", new String[]{"idQuest"});
        if (!c.moveToFirst()) {
            return null;
        }
        return new Question(idQuest, c.getString(1), c.getLong(2), c.getLong(3));
    }
    
}
