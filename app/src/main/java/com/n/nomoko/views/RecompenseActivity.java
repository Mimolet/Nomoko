package com.n.nomoko.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.n.nomoko.*;

public class RecompenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recompense);

        Button boutonAccueil = findViewById(R.id.button2);
        boutonAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecompenseActivity.this, AccueilActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    /*TODO : ajouter fonction pour ajouter la récompense dans la BDD*/
}
