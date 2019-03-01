package com.n.nomoko.views;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.n.nomoko.database.dao.DefisDAO;
import com.n.nomoko.database.infos.Defi;
import com.n.nomoko.*;
import com.n.nomoko.logic.ChallengeService;

import java.util.ArrayList;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        SharedPreferences infoPerso = PreferenceManager.getDefaultSharedPreferences(this);
        TextView textBonjour = findViewById(R.id.textView6);
        textBonjour.setText("Bonjour, " + infoPerso.getString("prenom", "Inconnu"));


        /*DEMONSTRATION*/
        if (infoPerso.getBoolean("premiersDefis", false)) {
            ArrayList<Defi> defisNouveaux = new ArrayList<>();
            DefisDAO defisDAO1 = new DefisDAO(this);
            defisDAO1.open();
            defisNouveaux.add(defisDAO1.selectionner(30));
            defisNouveaux.add(defisDAO1.selectionner(20));
            defisNouveaux.add(defisDAO1.selectionner(10));
            defisDAO1.close();
            this.nouveauxDefis(defisNouveaux);
            SharedPreferences.Editor editeur = infoPerso.edit();
            editeur.putBoolean("premiersDefis", false);
            editeur.apply();
        }

        afficherDefis();
        setNav();


        /*Soit l'utilisateur arrive sur la page depuis une autre fenêtre de l'application, soit il
        * arrive sur cette page depuis une notification*/
        Intent i = getIntent();
        if (i != null) {
            long[] idDefis = i.getLongArrayExtra("tableauIDDefis");
            if (idDefis != null) {
                ArrayList<Defi> nouveauxDefis = new ArrayList<Defi>();
                /*On récupère les id des défis*/
                DefisDAO defisDAO = new DefisDAO(this);
                defisDAO.open();
                for (int j = 0; j < idDefis.length; j++) {
                    nouveauxDefis.add(defisDAO.selectionner(idDefis[j]));
                }
                defisDAO.close();
                /*On lance la fonction qui propose les défis à l'user*/
                this.nouveauxDefis(nouveauxDefis);
            }
        }

        /*S'il n'arrive pas depuis une notification mais que de nouveaux défis sont disponibles*/
        if (infoPerso.getBoolean("nouveauxDefis", false)) {
            /*On récupère les défis dans SharedPref*/
            DefisDAO defisDAO = new DefisDAO(this);
            ArrayList<Defi> nouveauDefis = new ArrayList<>();
            defisDAO.open();
            nouveauDefis.add(defisDAO.selectionner(infoPerso.getLong("premierDefiID", -1)));
            nouveauDefis.add(defisDAO.selectionner(infoPerso.getLong("deuxiemeDefiID", -1)));
            nouveauDefis.add(defisDAO.selectionner(infoPerso.getLong("troisiemeDefiID", -1)));
            defisDAO.close();

            /*On lance la fonction d'alert défis*/
            this.nouveauxDefis(nouveauDefis);
        }

    }


    private void setNav () {
        BottomNavigationView navView = findViewById(R.id.activity_main_bottom_navigation);
        navView.setSelectedItemId(R.id.action_accueil);
        navView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.action_param:
                                Intent intentParam = new Intent(AccueilActivity.this, ParametresActivity.class);

                                startActivity(intentParam);
                                break;

                            case R.id.action_defis:
                                Intent intentDefis = new Intent(AccueilActivity.this, DefisReussisActivity.class);
                                finish();
                                startActivity(intentDefis);
                                break;

                            case R.id.action_profil:
                                Intent intentProfil = new Intent(AccueilActivity.this, AccueilActivity.class);
                                finish();
                                startActivity(intentProfil);
                                break;

                            case R.id.action_trophee:
                                Intent intentTrophee = new Intent(AccueilActivity.this, TropheeActivity.class);
                                finish();
                                startActivity(intentTrophee);
                                break;
                        }
                        return false;
                    }
                }
        );
    }

    /*Affiche les défis en cours*/
    private void afficherDefis () {
        /*Chercher dans la DB les défis concernés*/
        DefisDAO defisDAO = new DefisDAO(this);
        defisDAO.open();
        ArrayList<Defi> defisEnCours = defisDAO.defisEnCours();
        defisDAO.close();

        /*inflate les vues pour chaque défi*/
        if (defisEnCours != null) {
            for (Defi d : defisEnCours) {
                inflateVueDefi(d);
            }
        }
    }

    /*Fonction auxilaire : se charge d'afficher un défi en cours passé en paramètres*/
    private void inflateVueDefi (Defi d) {
        /*Récupérer le layout*/
        LinearLayout layoutScrollable = findViewById(R.id.lin_layout_accueil_scroll);
        /*Récupérer un inflater*/
        LayoutInflater layoutInflater = this.getLayoutInflater();
        /*Créer le layout du nouveau défi*/
        View nouveauDefiVue = layoutInflater.inflate(R.layout.defi_en_cours_layout, null);
        /*Remplir le nouveau layout*/
        TextView titreDefi = nouveauDefiVue.findViewById(R.id.titreDefi);
        TextView texteDefi = nouveauDefiVue.findViewById(R.id.texteDefi);
        ImageView imageDefi = nouveauDefiVue.findViewById(R.id.imageDefiEnCours);
        titreDefi.setText(getString(d.getNomDefiID()));
        texteDefi.setText(getString(d.getTxtDefiID()));
//        imageDefi.setImageDrawable(getDrawable(d.getIllustrationPathDefi()));

        ImageButton checkBouton = nouveauDefiVue.findViewById(R.id.checkBoutonDefi);
        ImageButton closeBouton = nouveauDefiVue.findViewById(R.id.closeBoutonDefi);

        final long idDefi = d.getIdDefi();

        checkBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefisDAO defisDAO = new DefisDAO(v.getContext());
                defisDAO.open();
                defisDAO.validerDefi(idDefi);
                defisDAO.close();
                Intent intent = new Intent(AccueilActivity.this, AccueilActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);
            }
        });

        closeBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DefisDAO defisDAO = new DefisDAO(v.getContext());
                defisDAO.open();
                defisDAO.echouerDefi(idDefi);
                defisDAO.close();
                Intent intent = new Intent(AccueilActivity.this, AccueilActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);
            }
        });

        /*Insérer le layout du nouveau défi dans la scrollview*/
        layoutScrollable.addView(nouveauDefiVue);

    }

    private void nouveauxDefis (ArrayList<Defi> defis) {
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View alertDialogView = layoutInflater.inflate(R.layout.activity_alert_defis, null);
        /*Ajout des défis dans l'alertView*/
        for (Defi d : defis) {
            insereNouveauDefiInAlertView(d, alertDialogView);
        }
        /*Lancement de l'alert view*/
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setView(alertDialogView);
        adb.create().show();
    }

    private void insereNouveauDefiInAlertView (Defi d, View alertDialogView) {
        /*Récupérer le linear layout où on insere le défi*/
        LinearLayout scrollView = alertDialogView.findViewById(R.id.linearLayoutAlertDefi);
        /*Récupérer un inflateur*/
        LayoutInflater inflater = this.getLayoutInflater();
        /*Créer le layout du nouveau défi*/
        View nouveauDefiVue = inflater.inflate(R.layout.defi_in_alert_accueil, null);
        /*Remplir le nouveau layout*/
        TextView titreNouveauDefi = nouveauDefiVue.findViewById(R.id.titreNouveauDefi);
        TextView texteNouveauDefi = nouveauDefiVue.findViewById(R.id.texteNouveauDefi);
        ImageView imageDefi = nouveauDefiVue.findViewById(R.id.imageViewNouveauDefi);
        Button boutonAccepte = nouveauDefiVue.findViewById(R.id.buttonAccepterDefi);
        titreNouveauDefi.setText(getString(d.getNomDefiID()));
        texteNouveauDefi.setText(getString(d.getTxtDefiID()));
 //       imageDefi.setImageDrawable(getDrawable(d.getIllustrationPathDefi()));

        final long idDefi = d.getIdDefi();

        boutonAccepte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Accepter le défi*/
                DefisDAO defisDAO = new DefisDAO(v.getContext());
                defisDAO.open();
                defisDAO.accepterDefi(idDefi);
                defisDAO.close();
                /*Sharedpref*/
                SharedPreferences infoPerso = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                SharedPreferences.Editor editeur = infoPerso.edit();
                editeur.putBoolean("nouveauxDefis", false);
                editeur.apply();
                /*Fermer l'alert view*/
                Intent intent = new Intent(AccueilActivity.this, AccueilActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);
            }
        });
        /*L'insérer dans la scrollview*/
        scrollView.addView(nouveauDefiVue);
    }
}
