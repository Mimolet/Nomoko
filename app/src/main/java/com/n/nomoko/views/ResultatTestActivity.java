package com.n.nomoko.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.n.nomoko.*;
import com.n.nomoko.logic.FormManager;

public class ResultatTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_test);
        this.processResults();
        Button boutonAccueil = findViewById(R.id.boutonVersAccueil);
        boutonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultatTestActivity.this, AccueilActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void processResults () {
        FormManager formManager = new FormManager();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editeur = sharedPreferences.edit();
        int [] scores = new int [4];
        scores[0] = sharedPreferences.getInt("espritScore", 0);
        scores[1] = sharedPreferences.getInt("energieScore", 0);
        scores[2] = sharedPreferences.getInt("natureScore", 0);
        scores[3] = sharedPreferences.getInt("tactiqueScore", 0);

        formManager.processFormResults(scores);

        int espritPourcent = formManager.getPourcent("Esprit");
        int energiePourcent = formManager.getPourcent("Energie");
        int naturePourcent = formManager.getPourcent("Nature");
        int tactiquePourcent = formManager.getPourcent("Tactique");
        String personnalite = formManager.getPersonnalite();

        editeur.putInt("espritPourcent", espritPourcent);
        editeur.putInt("energiePourcent", energiePourcent);
        editeur.putInt("naturePourcent", naturePourcent);
        editeur.putInt("tactiquePourcent", tactiquePourcent);

        editeur.putString("personnalite", personnalite);

        editeur.apply();

        TextView textView = findViewById(R.id.personnaliteUser);
        textView.setText(personnalite);

        ProgressBar BarEsprit = findViewById(R.id.espritBarre);
        ProgressBar BarEnergie = findViewById(R.id.energieBarre);
        ProgressBar BarNature = findViewById(R.id.natureBarre);
        ProgressBar BarTactique = findViewById(R.id.tactiqueBarre);

        BarEsprit.setProgress(espritPourcent);
        BarEnergie.setProgress(energiePourcent);
        BarNature.setProgress(naturePourcent);
        BarTactique.setProgress(tactiquePourcent);

        ImageView imageView = findViewById(R.id.imagePersonnalite);
        /*TODO: importer les images des personnalité en svg*/

        switch (personnalite) {
            case "Analyste" :
                imageView.setImageDrawable(getDrawable(R.drawable.analyste));
                break;

            case "Sentinelle" :
                imageView.setImageDrawable(getDrawable(R.drawable.sentinelle));
                break;

            case "Diplomate" :
                imageView.setImageDrawable(getDrawable(R.drawable.diplomate));
                break;

            case "Explorateur" :
                imageView.setImageDrawable(getDrawable(R.drawable.explorateur));
                break;
        }
    }
}
