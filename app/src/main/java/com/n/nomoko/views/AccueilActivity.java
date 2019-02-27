package com.n.nomoko.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.n.nomoko.database.dao.DefisDAO;
import com.n.nomoko.database.infos.Defi;
import com.n.nomoko.*;
import java.util.ArrayList;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        SharedPreferences infoPerso = PreferenceManager.getDefaultSharedPreferences(this);
        TextView textBonjour = findViewById(R.id.textView6);
        textBonjour.setText("Bonjour, " + infoPerso.getString("prenom", "Inconnu"));

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

            }
        }
    }

    /*TODO : fonction qui affiche les défis proposés au joueur*/


    public void setNav () {
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
}
