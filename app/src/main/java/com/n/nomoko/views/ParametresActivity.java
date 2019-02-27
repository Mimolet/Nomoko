package com.n.nomoko.views;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.n.nomoko.*;

public class ParametresActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        setNav();
        final SharedPreferences infoPerso = PreferenceManager.getDefaultSharedPreferences(this);
        Switch aSwitch = findViewById(R.id.switch1);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //Les notifications sont activées.
                    SharedPreferences.Editor editeur = infoPerso.edit();
                    editeur.putBoolean("notifications", true);
                    editeur.apply();
                }else {
                    //Les notifications sont désactivées.
                    SharedPreferences.Editor editeur = infoPerso.edit();
                    editeur.putBoolean("notifications", false);
                    editeur.apply();
                }
            }
        });
    }

    public void setNav () {
        BottomNavigationView navView = findViewById(R.id.activity_main_bottom_navigation);
        navView.setSelectedItemId(R.id.action_param);
        navView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {

                            case R.id.action_accueil:
                                Intent intentParam = new Intent(ParametresActivity.this, AccueilActivity.class);

                                startActivity(intentParam);
                                break;

                            case R.id.action_defis:
                                Intent intentDefis = new Intent(ParametresActivity.this, DefisReussisActivity.class);
                                finish();
                                startActivity(intentDefis);
                                break;

                            case R.id.action_profil:
                                Intent intentProfil = new Intent(ParametresActivity.this, AccueilActivity.class);
                                finish();
                                startActivity(intentProfil);
                                break;

                            case R.id.action_trophee:
                                Intent intentTrophee = new Intent(ParametresActivity.this, TropheeActivity.class);
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
