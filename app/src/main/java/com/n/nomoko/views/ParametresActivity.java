package com.n.nomoko.views;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.n.nomoko.*;

public class ParametresActivity extends AppCompatActivity {

    Switch aSwitch = (Switch)findViewById(R.id.switch1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        final SharedPreferences infoPerso = PreferenceManager.getDefaultSharedPreferences(this);


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
}
