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
import com.n.nomoko.database.dao.RecompenseDAO;
import com.n.nomoko.database.infos.Recompense;
import com.n.nomoko.logic.ChallengeService;

public class RecompenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recompense);
        final SharedPreferences infoPerso = PreferenceManager.getDefaultSharedPreferences(this);

        Button boutonAccueil = findViewById(R.id.button2);
        boutonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.editTextRecompense);
                String textrecompense = String.valueOf(editText.getText());
                ajouterRecompense(textrecompense);
                /*
                Intent intentService = new Intent(RecompenseActivity.this, ChallengeService.class);
                intentService.putExtra("premiersDefis", true);
                startService(intentService);
                */
                SharedPreferences.Editor editeur = infoPerso.edit();
                editeur.putBoolean("premiersDefis", true);
                editeur.apply();
                Intent intent = new Intent(RecompenseActivity.this, AccueilActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void ajouterRecompense (String recompense) {
        RecompenseDAO recompenseDAO = new RecompenseDAO(this);
        recompenseDAO.open();
        recompenseDAO.ajouter(new Recompense(1, recompense, false));
        recompenseDAO.close();
    }
}
