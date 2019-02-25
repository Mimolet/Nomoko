package com.n.nomoko.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.n.nomoko.*;

public class InscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Button boutonSuivant = findViewById(R.id.button);
        boutonSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfos();
                Intent intent = new Intent(InscriptionActivity.this, TestPersoActivity.class);
                intent.putExtra("page", 1);
                startActivity(intent);
            }
        });
    }

    private void saveInfos () {
        /*Récupératon des données entrées par l'user (prénom et avatar)*/
        EditText editText = findViewById(R.id.editText2);
        /*Sauvegarde dans les shared pref*/
        SharedPreferences infoPerso = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editeur = infoPerso.edit();
        editeur.putString("prenom", String.valueOf(editText.getText()));
        editeur.putInt("niveau", 1);
        editeur.putInt("experience", 0);
        editeur.apply();
    }
}
