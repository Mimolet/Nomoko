package com.n.nomoko.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.n.nomoko.*;
import com.n.nomoko.database.dao.CategorieDAO;
import com.n.nomoko.database.dao.QuestionDAO;
import com.n.nomoko.database.infos.Categorie;
import com.n.nomoko.database.infos.Question;

import java.util.ArrayList;

public class TestPersoActivity extends AppCompatActivity {
    /*Contient les modificateurs pour les catégories de chaque question*/
    private ArrayList<String> signesQuestions;
    /*Contient les catégories dechaque question*/
    private ArrayList<String> catQuestions;

    private int numeroPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.signesQuestions = new ArrayList<>();
        this.catQuestions = new ArrayList<>();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editeur = sharedPreferences.edit();
        if (!sharedPreferences.contains("page")) {
            this.numeroPage = 1;
            editeur.putInt("page", 1);
            editeur.apply();
        } else {
            this.numeroPage = sharedPreferences.getInt("page", 1);
        }
        this.associateSignToQ();

        setContentView(R.layout.activity_test_perso);
        updateProgressBar();
        this.fillQuestionTextViews();
        Button buttonSuivant = findViewById(R.id.button3);
        buttonSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Vérifier que tous les radios group sont cochés*/
                RadioGroup radioGroup1 = findViewById(R.id.radioGroup);
                RadioGroup radioGroup2 = findViewById(R.id.radioGroup2);
                RadioGroup radioGroup3 = findViewById(R.id.radioGroup3);
                RadioGroup radioGroup4 = findViewById(R.id.radioGroup4);
                int resultatQ1 = radioGroup1.getCheckedRadioButtonId();
                int resultatQ2 = radioGroup2.getCheckedRadioButtonId();
                int resultatQ3 = radioGroup3.getCheckedRadioButtonId();
                int resultatQ4 = radioGroup4.getCheckedRadioButtonId();
                if (resultatQ1 != -1 && resultatQ2 != -1 && resultatQ3 != -1 && resultatQ4 != -1) {
                    /*Enregistrer les résultats dans les sharedpref ainsi que le numéro de la page courante*/
                    editeur.putInt("page", numeroPage+1);
                    editeur.apply();
                    comptabiliserReponses();
                    /*Recharger la page*/
                    if (numeroPage < 8) {
                        Intent intent = getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        startActivity(intent);
                    } else {
                        /*Page des résultats*/
                        editeur.putBoolean("testFini", true);
                        editeur.apply();
                        Intent intent = new Intent(TestPersoActivity.this, ResultatTestActivity.class);
                        finish();
                        startActivity(intent);
                    }



                }
            }
        });
    }

    /*Insère les questions dans les textviews*/
    private void fillQuestionTextViews () {
        /*Récupération dans la BDD des textes de question (attention, utiliser geString)*/
        QuestionDAO questionDAO = new QuestionDAO(this);
        questionDAO.open();
        ArrayList<String> questions = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            questions.add(getString(questionDAO.selectionner((long) i+(this.numeroPage - 1)*4).getTexteID()));
        }
        questionDAO.close();
        /*Insertion des textes dans les textViews*/
        TextView textView1 = findViewById(R.id.textView8);
        TextView textView2 = findViewById(R.id.textView11);
        TextView textView3 = findViewById(R.id.textView12);
        TextView textView4 = findViewById(R.id.textView17);
        textView1.setText(questions.get(0));
        textView2.setText(questions.get(1));
        textView3.setText(questions.get(2));
        textView4.setText(questions.get(3));
    }

    /*Récupère les catégories des questions et les signes associés*/
    private void associateSignToQ () {
        CategorieDAO categorieDAO = new CategorieDAO(this);
        QuestionDAO questionDAO = new QuestionDAO(this);
        for (int i = 1; i < 5; i++) {
            long [] idCats = new long[2];
            questionDAO.open();
            Question q = questionDAO.selectionner((long)i+(this.numeroPage - 1)*4);
            questionDAO.close();
            idCats[0] = q.getIdCat1();
            idCats[1] = q.getIdCat2();
            categorieDAO.open();
            Categorie c1 = categorieDAO.selectionner(idCats[0]);
            this.signesQuestions.add(i-1, c1.getSigneCat());
            this.catQuestions.add(i-1, getString(c1.getNomCatID()));
            if (idCats[1] > 0) {
                Categorie c2 = categorieDAO.selectionner(idCats[1]);
                this.signesQuestions.add(i, c2.getSigneCat());
                this.catQuestions.add(i, getString(c2.getNomCatID()));
            } else {
                this.signesQuestions.add(i, "0");
                this.catQuestions.add(i, "0");
            }
            categorieDAO.close();
        }
    }

    private void updateProgressBar () {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress((this.numeroPage)*13);
    }

    /*Fait le compte des réponses à chaque changement de page*/
    private void comptabiliserReponses () {
        /*Récupérer les radios buttons cochés*/
        RadioGroup radioGroup1 = findViewById(R.id.radioGroup);
        RadioGroup radioGroup2 = findViewById(R.id.radioGroup2);
        RadioGroup radioGroup3 = findViewById(R.id.radioGroup3);
        RadioGroup radioGroup4 = findViewById(R.id.radioGroup4);
        int [] reponses = new int [4];

        switch (radioGroup1.getCheckedRadioButtonId()) {
            case R.id.radioButton :
                reponses[0] = 2;
                break;

            case R.id.radioButton3 :
                reponses[0] = 1;
                break;

            case R.id.radioButton4 :
                reponses[0] = 0;
                break;

            case R.id.radioButton5 :
                reponses[0] = -1;
                break;

            case R.id.radioButton6 :
                reponses[0] = -2;
                break;
        }

        switch (radioGroup2.getCheckedRadioButtonId()) {
            case R.id.radioButton7 :
                reponses[1] = 2;
                break;

            case R.id.radioButton8 :
                reponses[1] = 1;
                break;

            case R.id.radioButton9 :
                reponses[1] = 0;
                break;

            case R.id.radioButton10 :
                reponses[1] = -1;
                break;

            case R.id.radioButton11 :
                reponses[1] = -2;
                break;
        }

        switch (radioGroup3.getCheckedRadioButtonId()) {
            case R.id.radioButton12 :
                reponses[2] = 2;
                break;

            case R.id.radioButton13 :
                reponses[2] = 1;
                break;

            case R.id.radioButton14 :
                reponses[2] = 0;
                break;

            case R.id.radioButton15 :
                reponses[2] = -1;
                break;

            case R.id.radioButton16 :
                reponses[2] = -2;
                break;
        }

        switch (radioGroup4.getCheckedRadioButtonId()) {
            case R.id.radioButton17 :
                reponses[3] = 2;
                break;

            case R.id.radioButton18 :
                reponses[3] = 1;
                break;

            case R.id.radioButton19 :
                reponses[3] = 0;
                break;

            case R.id.radioButton20 :
                reponses[3] = -1;
                break;

            case R.id.radioButton21 :
                reponses[3] = -2;
                break;
        }

        /*Modifier les réponses en fonction du signe et les stocker dans les cat correpsondantes*/
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editeur = sharedPreferences.edit();
        int resultEsprit = sharedPreferences.getInt("espritScore", 0);
        int resultEnergie = sharedPreferences.getInt("energieScore", 0);
        int resultNature = sharedPreferences.getInt("natureScore", 0);
        int resultTactique = sharedPreferences.getInt("tactiqueScore", 0);

        for (int i = 0; i < 4; i++) {
            switch(catQuestions.get(i)) {
                case "Esprit" :
                    if (signesQuestions.get(i).equals("-")) {
                        resultEsprit -= reponses[i];
                    } else {
                        resultEsprit += reponses[i];
                    }
                    break;

                case "Énergie" :
                    if (signesQuestions.get(i).equals("-")) {
                        resultEnergie -= reponses[i];
                    } else {
                        resultEnergie += reponses[i];
                    }
                    break;

                case "Nature" :
                    if (signesQuestions.get(i).equals("-")) {
                        resultNature -= reponses[i];
                    } else {
                        resultNature += reponses[i];
                    }
                    break;

                case "Tactique" :
                    if (signesQuestions.get(i).equals("-")) {
                        resultTactique -= reponses[i];
                    } else {
                        resultTactique += reponses[i];
                    }
                    break;
            }
        }

        for (int i = 0; i < 4; i++) {
            switch(catQuestions.get(i+4)) {
                case "Esprit" :
                    if (signesQuestions.get(i+4).equals("-")) {
                        resultEsprit -= reponses[i];
                    } else if (signesQuestions.get(i+4).equals("+")) {
                        resultEsprit += reponses[i];
                    }
                    break;

                case "Énergie" :
                    if (signesQuestions.get(i+4).equals("-")) {
                        resultEnergie -= reponses[i];
                    } else if (signesQuestions.get(i+4).equals("+")) {
                        resultEnergie += reponses[i];
                    }
                    break;

                case "Nature" :
                    if (signesQuestions.get(i+4).equals("-")) {
                        resultNature -= reponses[i];
                    } else if (signesQuestions.get(i+4).equals("+")) {
                        resultNature += reponses[i];
                    }
                    break;

                case "Tactique" :
                    if (signesQuestions.get(i+4).equals("-")) {
                        resultTactique -= reponses[i];
                    } else if (signesQuestions.get(i+4).equals("+")) {
                        resultTactique += reponses[i];
                    }
                    break;
            }
        }

        /*Enregistrer dans les shared ref les résultats*/
        editeur.putInt("espritScore", resultEsprit);
        editeur.putInt("energieScore", resultEnergie);
        editeur.putInt("natureScore", resultNature);
        editeur.putInt("tactiqueScore", resultTactique);
        editeur.apply();
    }
}
