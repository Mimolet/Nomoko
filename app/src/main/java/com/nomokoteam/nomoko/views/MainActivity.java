package com.nomokoteam.nomoko.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.nomokoteam.nomoko.database.infos.Question;
import com.nomokoteam.nomoko.database.dao.QuestionDAO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        QuestionDAO questionDAO = new QuestionDAO(this);
        questionDAO.open();
        questionDAO.ajouter(new Question(1, "Bonjour c'est la question", 1, 2));
        Question q = questionDAO.selectionner(1);


        TextView t = (TextView) findViewById(R.id.hey);
        t.setText(q.getTexte());

        questionDAO.close();

    }
}
