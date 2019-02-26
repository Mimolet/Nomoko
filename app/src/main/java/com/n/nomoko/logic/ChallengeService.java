package com.n.nomoko.logic;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import com.n.nomoko.database.dao.DefisDAO;
import com.n.nomoko.database.infos.Defi;
import com.n.nomoko.views.AccueilActivity;
import com.n.nomoko.*;

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
    /*Notifications activees ou non*/
    private boolean notif;
    /*Nom de la channel utilisée pour envoyer une notification à l'user (pour android 8,0 et sup)*/
    private static final String CHANNEL_ID = "Notif_Nomoko";

    public ChallengeService () {
        super("ChallengeService");
        //permet de récupérer des infos stockées en local
        SharedPreferences infoPerso = PreferenceManager.getDefaultSharedPreferences(this);

        this.experience = infoPerso.getInt("experience", 0);

        this.niveau = infoPerso.getInt("niveau", 1);

        this.palierExp = infoPerso.getInt("palierExp", 100);

        this.energie = infoPerso.getString("energie", "");

        this.esprit = infoPerso.getString("esprit", "");

        this.nature = infoPerso.getString("nature", "");

        this.tactique = infoPerso.getString("tactique", "");

        this.notif = infoPerso.getBoolean("notifications", true);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        this.createNotificationChannel();
        Date dateCourante = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
        if (this.HEURE_DEFI.equals(dateFormat.format(dateCourante))) {
            choixDefi();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //validation defi
        long idDefi = intent.getLongExtra("idDefi", 0);
        validerDefi(idDefi);
        this.experience += 10;
        gestionExperience();
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

        if (listeModifiee.size() <= 3) {
            if (listeModifiee.size() == 0) {
                notifieNvxDefis(new ArrayList<Defi>());
            } else {
                notifieNvxDefis(listeModifiee);
            }

        } else {
            int deuxiemeDefiIndex = -1;
            int troisiemeDefiIndex = -1;
            ArrayList<Defi> defisChoisis = new ArrayList<Defi>();
            int premierDefiIndex = (int)(Math.random() * listeModifiee.size());
            while (deuxiemeDefiIndex == premierDefiIndex || deuxiemeDefiIndex == -1) {
                deuxiemeDefiIndex = (int)(Math.random() * listeModifiee.size());
            }
            while (troisiemeDefiIndex == deuxiemeDefiIndex || troisiemeDefiIndex == premierDefiIndex || troisiemeDefiIndex == -1) {
                troisiemeDefiIndex = (int)(Math.random() * listeModifiee.size());
            }
            defisChoisis.add(listeModifiee.get(premierDefiIndex));
            defisChoisis.add(listeModifiee.get(deuxiemeDefiIndex));
            defisChoisis.add(listeModifiee.get(troisiemeDefiIndex));

            if (notif) {
                notifieNvxDefis(defisChoisis);
            } else {
                // TODO : inscription dans les shared pref les id des nouveaux défs. Verif dans accueil
            }

        }
    }

    private void notifieNvxDefis (ArrayList<Defi> defisAEnvoyer) {
        /*Création de la notification*/
        CharSequence titreNotif = getString(R.string.notifTitre);
        CharSequence texteNotif = getString(R.string.notifTexte);
        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        notifBuilder.setContentTitle(titreNotif);
        notifBuilder.setContentText(texteNotif);
        notifBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        /*Récupération des id des défis à envoyer*/
        long [] idDefis = new long [3];
        switch (defisAEnvoyer.size()) {
            case 0 :
                idDefis[0] = -1;
                idDefis[1] = -1;
                idDefis[2] = -1;
            case 1 :
                idDefis[0] = defisAEnvoyer.get(0).getIdDefi();
                idDefis[1] = -1;
                idDefis[2] = -1;
                break;
            case 2 :
                idDefis[0] = defisAEnvoyer.get(0).getIdDefi();
                idDefis[1] = defisAEnvoyer.get(1).getIdDefi();
                idDefis[2] = -1;
                break;
            case 3 :
                idDefis[0] = defisAEnvoyer.get(0).getIdDefi();
                idDefis[1] = defisAEnvoyer.get(1).getIdDefi();
                idDefis[2] = defisAEnvoyer.get(2).getIdDefi();
                break;
        }

        /*Envoi de la notification*/
        Intent intent = new Intent (this, AccueilActivity.class);
        intent.putExtra("tableauIDDefis", idDefis);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        notifBuilder.setContentIntent(pendingIntent);
        notifBuilder.setAutoCancel(true);

    }

    /*Code récup en ligne sur developpers.android.com
    * Création d'une channel pour la gestion des notifications
    * */
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

    private void validerDefi (long id) {
        DefisDAO defisDAO = new DefisDAO(this);
        defisDAO.open();
        defisDAO.validerDefi(id);
        defisDAO.close();
    }

    private void validerTrophee () {
        /*Vérifie si un trophee a été valide*/
    }
}






























