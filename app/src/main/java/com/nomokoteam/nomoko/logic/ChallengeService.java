package com.nomokoteam.nomoko.logic;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChallengeService extends IntentService {
    private final String HEURE_DEFI = "08";
    /*Quantité d'exp nécessaire pour gagner un niveau*/
    private int palierExp;
    /*Points d'expériences actuels de l'utilisateur*/
    private int experience;
    /*Niveau actuel de l'utilisateur*/
    private int niveau;

    public ChallengeService () {
        super("ChallengeService");
        //permet de récupérer des infos stockées en local
        SharedPreferences infoPerso = PreferenceManager.getDefaultSharedPreferences(this);
        /*TODO : gérer le cas de defValue*/
        this.experience = infoPerso.getInt("experience", -1);
        /*TODO : idem*/
        this.niveau = infoPerso.getInt("niveau", -1);
        /*TODO ; idem*/
        this.palierExp = infoPerso.getInt("palierExp", -1);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Date dateCourante = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        if (this.HEURE_DEFI.equals(dateFormat.format(dateCourante))) {
            /*Choix des 3 défis proposés + envoi notification*/
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    private void gestionExperience () {
        if (this.experience > this.palierExp) {
            this.experience = this.experience - this.palierExp;
            this.niveau ++;
            SharedPreferences infoPerso = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editeur = infoPerso.edit();
            editeur.putInt("experience", this.experience);
            editeur.putInt("niveau", this.niveau);
            editeur.apply();
        }
    }

    private void valideDefi () {

    }
}
