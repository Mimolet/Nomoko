package com.nomokoteam.nomoko.database.dao;

import android.content.Context;

import com.nomokoteam.nomoko.database.infos.Utilisateur;

public class UtilisateurDAO extends DAOBase {
    public static final String KEY = "idUtilisateur";
    public static final String NAME = "prenom";
    public static final String AGE = "age";
    public static final String SEXE = "sexe";
    public static final String PERSO = "personnalite";
    public static final String AVATAR = "avatar";
    public static final String TABLE_NAME = "Utilisateur";

    public UtilisateurDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter (Utilisateur u) {

    }

    public Utilisateur selectionner (long idUtilisateur) {
        
    }
}
