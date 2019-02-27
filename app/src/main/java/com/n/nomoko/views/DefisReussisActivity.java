package com.n.nomoko.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.n.nomoko.*;

public class DefisReussisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defis_reussis);
        setNav();
    }

    public void setNav () {
        BottomNavigationView navView = findViewById(R.id.activity_main_bottom_navigation);
        navView.setSelectedItemId(R.id.action_defis);
        navView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.action_param:
                                Intent intentParam = new Intent(DefisReussisActivity.this, ParametresActivity.class);

                                startActivity(intentParam);
                                break;

                            case R.id.action_accueil:
                                Intent intentDefis = new Intent(DefisReussisActivity.this, AccueilActivity.class);
                                finish();
                                startActivity(intentDefis);
                                break;

                            case R.id.action_profil:
                                Intent intentProfil = new Intent(DefisReussisActivity.this, AccueilActivity.class);
                                finish();
                                startActivity(intentProfil);
                                break;

                            case R.id.action_trophee:
                                Intent intentTrophee = new Intent(DefisReussisActivity.this, TropheeActivity.class);
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
