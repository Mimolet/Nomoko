package com.n.nomoko.database;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;

import com.n.nomoko.R;
import com.n.nomoko.database.dao.CategorieDAO;
import com.n.nomoko.database.dao.DefisDAO;
import com.n.nomoko.database.dao.QuestionDAO;
import com.n.nomoko.database.dao.TropheesDAO;
import com.n.nomoko.database.infos.Categorie;
import com.n.nomoko.database.infos.Defi;
import com.n.nomoko.database.infos.Question;
import com.n.nomoko.database.infos.Trophee;

import java.util.ArrayList;

public class FillDBService extends IntentService {

    private static final int NB_CATEGORIES = 4;
    private static final int NB_QUESTIONS = 32;
    private static final int NB_DEFIS = 80;
    private static final int NB_TROPHEES = 15;
    private long idEspritPlus;
    private long idEspritMoins;
    private long idEnergiePlus;
    private long idEnergieMoins;
    private long idNaturePlus;
    private long idNatureMoins;
    private long idTactiquePlus;
    private long idTactiqueMoins;

    public FillDBService() {
        super("FillDBService");
        this.idEspritPlus = 0;
        this.idEspritMoins = 0;
        this.idEnergiePlus = 0;
        this.idEnergieMoins = 0;
        this.idNaturePlus = 0;
        this.idNatureMoins = 0;
        this.idTactiquePlus = 0;
        this.idTactiqueMoins = 0;
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    public void fillCategorie () {
        CategorieDAO categorieDAO = new CategorieDAO(this);
        categorieDAO.open();

        this.idEspritPlus = categorieDAO.ajouter(new Categorie(0, getString(R.string.dbCatEsprit), "+"));
        this.idEspritMoins = categorieDAO.ajouter(new Categorie(0, getString(R.string.dbCatEsprit), "-"));
        this.idEnergiePlus = categorieDAO.ajouter(new Categorie(0, getString(R.string.dbCatEnergie), "+"));
        this.idEnergieMoins = categorieDAO.ajouter(new Categorie(0, getString(R.string.dbCatEnergie), "-"));
        this.idNaturePlus = categorieDAO.ajouter(new Categorie(0, getString(R.string.dbCatNature), "+"));
        this.idNatureMoins = categorieDAO.ajouter(new Categorie(0, getString(R.string.dbCatNature), "-"));
        this.idTactiquePlus = categorieDAO.ajouter(new Categorie(0, getString(R.string.dbCatTactique), "+"));
        this.idTactiqueMoins = categorieDAO.ajouter(new Categorie(0, getString(R.string.dbCatTactique), "-"));

        categorieDAO.close();
    }

    public void fillQuestions () {
        if (idEspritMoins >= 0) {
            QuestionDAO questionDAO = new QuestionDAO(this);
            questionDAO.open();

            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest1), idEspritPlus, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest2), idEnergiePlus, idNaturePlus));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest3), idEspritMoins, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest4), idEnergiePlus, idTactiqueMoins));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest5), idEnergieMoins, idNaturePlus));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest6), idTactiquePlus, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest7), idNatureMoins, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest8), idEspritPlus, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest9), idEnergieMoins, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest10), idEspritPlus, idNatureMoins));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest11), idTactiquePlus, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest12), idNaturePlus, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest13), idTactiqueMoins, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest14), idEspritMoins, idNaturePlus));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest15), idEspritMoins, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest16), idEnergieMoins, idTactiquePlus));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest17), idNaturePlus, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest18), idEspritPlus, idEnergieMoins));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest19), idTactiqueMoins, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest20), idEspritPlus, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest21), idEnergiePlus, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest22), idNaturePlus, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest23), idNatureMoins, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest24), idTactiqueMoins, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest25), idNaturePlus, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest26), idEnergiePlus, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest27), idEnergiePlus, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest28), idEnergieMoins, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest29), idEspritPlus, idNatureMoins));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest30), idTactiqueMoins, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest31), idNatureMoins, -1));
            questionDAO.ajouter(new Question(0, getString(R.string.dbQuest32), idEspritPlus, -1));

            questionDAO.close();
        }
    }

    public void fillDefis () {
        DefisDAO defisDAO = new DefisDAO(this);
        defisDAO.open();

        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, getString(R.string.dbDefiNom1), getString(R.string.dbDefi1), 0, "Esprit", "Introverti", false, "", 1));


        defisDAO.close();
    }

    public void fillTrophees () {
        TropheesDAO tropheesDAO = new TropheesDAO(this);
        tropheesDAO.open();

        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom1), getString(R.string.dbTro1), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom2), getString(R.string.dbTro2), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom3), getString(R.string.dbTro3), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom4), getString(R.string.dbTro4), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom5), getString(R.string.dbTro5), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom6), getString(R.string.dbTro6), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom7), getString(R.string.dbTro7), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom8), getString(R.string.dbTro8), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom9), getString(R.string.dbTro9), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom10), getString(R.string.dbTro10), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom11), getString(R.string.dbTro11), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom12), getString(R.string.dbTro12), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom13), getString(R.string.dbTro13), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom14), getString(R.string.dbTro14), false, ""));
        tropheesDAO.ajouter(new Trophee(0, getString(R.string.dbTroNom15), getString(R.string.dbTro15), false, ""));

        tropheesDAO.close();
    }



}
