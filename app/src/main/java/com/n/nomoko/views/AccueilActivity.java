package com.n.nomoko.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.n.nomoko.database.dao.DefisDAO;
import com.n.nomoko.database.infos.Defi;

import java.util.ArrayList;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
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
}
