package com.n.nomoko.database.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.n.nomoko.database.DBManager;


/*Cette classe sert de base pour créer des classes d'accès à la base de données.*/
public abstract class DAOBase {
    protected final static int VERSION = 9;
    protected final static String NOM = "database.db";
    protected SQLiteDatabase mDb = null;
    protected DBManager manager;

    public DAOBase(Context pContext) {
        this.manager = new DBManager(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        this.mDb = manager.getWritableDatabase();
        return mDb;
    }

    public void close() {
        this.mDb.close();
    }

    public SQLiteDatabase getDb() {
        return this.mDb;
    }

    abstract public void toutSupprimer ();

}
