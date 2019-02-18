package com.nomokoteam.nomoko.logic;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import com.nomokoteam.nomoko.database.dao.DefisDAO;
import com.nomokoteam.nomoko.database.dao.UtilisateurDAO;
import com.nomokoteam.nomoko.database.infos.Defi;
import com.nomokoteam.nomoko.database.infos.Utilisateur;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChallengeService extends IntentService {
    private final String HEURE_DEFI = "08";
    /*Quantité d'exp nécessaire pour gagner un niveau*/
    private int palierExp;
    /*Points d'expériences actuels de l'utilisateur*/
    private int experience;
    /*Niveau actuel de l'utilisateur*/
    private int niveau;
    /*Traits de personnalité de l'user*/
    private String energie;
    private String esprit;
    private String nature;
    private String tactique;
    /*Nom de la channel utilisée pour envoyer une notification à l'user (pour android 8,0 et sup)*/
    private static final String CHANNEL_ID = "Notif_Nomoko";

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

        this.energie = infoPerso.getString("energie", "");

        this.esprit = infoPerso.getString("esprit", "");

        this.nature = infoPerso.getString("nature", "");

        this.tactique = infoPerso.getString("tactique", "");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        this.createNotificationChannel();
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

    private void choixDefi () {
        /*Récupérer la liste des défis*/
        DefisDAO defisDAO = new DefisDAO(this);
        defisDAO.open();
        ArrayList<Defi> listeDefis = defisDAO.selectionnerTousLesDefis();
        defisDAO.close();
        /*Enlever les défis déjà réalisés*/
        /*Enlever les défis dont la difficulté est sup au niveau du joueur*/
        /*Enlever les défis qui ne correspondent pas aux traits du joueur*/
        ArrayList<Defi> listeModifiee = new ArrayList<Defi>();
        for (Defi d : listeDefis) {
            if (!d.isReussiDefi()) {
                if (d.getDifficulte() <= this.niveau) {
                    if (d.getTraitDefi().equals(this.energie) || d.getTraitDefi().equals(this.esprit) || d.getTraitDefi().equals(this.nature) || d.getTraitDefi().equals(this.tactique)) {
                        listeModifiee.add(d);
                    }
                }
            }
        }
        /*TODO : choix aléatoire dans la liste, puis envoi d'une notification et inscription dans les données à destinatation de l'activité d'accueil*/
    }

    private void notifieNvxDefis () {
        CharSequence titreNotif = getString(R.string.notifTitre);
        CharSequence texteNotif = getString(R.string.notifTexte);
        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        notifBuilder.setContentTitle(titreNotif);
        notifBuilder.setContentText(texteNotif);
        notifBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        /*TODO : modifier le nom de l'activité cible*/
/*
        Intent intent = new Intent (this, ActiviteAccueil.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        notifBuilder.setContentIntent(pendingIntent);
        notifBuilder.setAutoCancel(true);
*/
    }

    /*Code récup en ligne sur developpers.android.com*/
    private void createNotificationChannel () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }
}




























