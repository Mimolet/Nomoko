package com.nomokoteam.nomoko.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.nomokoteam.nomoko.database.infos.Utilisateur;

public class UtilisateurDAO extends DAOBase {
    public static final String KEY = "idUtilisateur";
    public static final String NAME = "prenom";
    public static final String PERSO = "personnalite";
    public static final String AVATAR = "avatar";
    public static final String ENERGIE = "energie";
    public static final String ESPRIT = "esprit";
    public static final String NATURE = "nature";
    public static final String TACTIQUE = "tactique";
    public static final String TABLE_NAME = "Utilisateur";

    public UtilisateurDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter (Utilisateur u) {
        ContentValues valeur = new ContentValues();
        valeur.put(UtilisateurDAO.NAME, u.getPrenom());
        valeur.put(UtilisateurDAO.PERSO, u.getPersonnalite());
        valeur.put(UtilisateurDAO.AVATAR, u.getAvatar());
        valeur.put(UtilisateurDAO.ENERGIE, u.getEnergie());
        valeur.put(UtilisateurDAO.ESPRIT, u.getEsprit());
        valeur.put(UtilisateurDAO.NATURE, u.getNature());
        valeur.put(UtilisateurDAO.TACTIQUE, u.getTactique());
        this.mDb.insert(DefisDAO.TABLE_NAME, null, valeur);
    }

    public Utilisateur selectionner (long idUtilisateur) {
        Cursor c = mDb.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY + " = ?", new String[]{Long.toString(idUtilisateur)});
        if (!c.moveToFirst()) {
            c.close();
            return null;
        }
        Utilisateur resultat = new Utilisateur(idUtilisateur, c.getString(1),
                c.getString(2), c.getString(3), c.getString(4),
                c.getString(5), c.getString(6), c.getString(7));
        c.close();
        return resultat;
    }

    /*TODO / enlever user de la base de données et stocker ses données dans des SharedPreferences*/
}
