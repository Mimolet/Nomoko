package com.n.nomoko;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.n.nomoko.database.dao.QuestionDAO;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionDAO questionDAO = new QuestionDAO(this);
        questionDAO.open();
        questionDAO.close();
    }

    @Override
    protected void onStart() {
        super.onStart();
        QuestionDAO questionDAO = new QuestionDAO(this);
        questionDAO.open();
        String text = questionDAO.selectionner(5).getTexte();
        questionDAO.close();
        TextView textView = findViewById(R.id.hey);
        textView.setText(text);
    }
}
