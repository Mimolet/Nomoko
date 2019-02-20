package com.n.nomoko.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.n.nomoko.database.infos.Question;

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
        Cursor c = this.mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY + " = ?", new String[]{Long.toString(idQuest)});
        if (!c.moveToFirst()) {
            c.close();
            return null;
        }
        Question resultat = new Question(idQuest, c.getString(1), c.getLong(2), c.getLong(3));
        c.close();
        return resultat;
    }
    
}
