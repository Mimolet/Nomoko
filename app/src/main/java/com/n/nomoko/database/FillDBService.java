package com.n.nomoko.database;

import android.app.IntentService;
import android.content.Intent;
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

public class FillDBService extends IntentService {

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
        QuestionDAO questionDAO = new QuestionDAO(this);
        /*Suppression des données existantes (obsolètes)*/
        questionDAO.toutSupprimer();
        (new CategorieDAO(this)).toutSupprimer();
        (new DefisDAO(this)).toutSupprimer();
        (new TropheesDAO(this)).toutSupprimer();
        /*Insertion des données*/
        fillCategorie();
        fillQuestions();
        fillDefis();
        fillTrophees();

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

        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom1)", "getString(R.string.dbDefi1)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom2)", "getString(R.string.dbDefi2)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom3)", "getString(R.string.dbDefi3)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom4)", "getString(R.string.dbDefi4)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom5)", "getString(R.string.dbDefi5)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom6)", "getString(R.string.dbDefi6)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom7)", "getString(R.string.dbDefi7)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom8)", "getString(R.string.dbDefi8)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom9)", "getString(R.string.dbDefi9)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom10)", "getString(R.string.dbDefi10)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom11)", "getString(R.string.dbDefi11)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom12)", "getString(R.string.dbDefi12)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom13)", "getString(R.string.dbDefi13)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom14)", "getString(R.string.dbDefi14)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom15)", "getString(R.string.dbDefi15)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom16)", "getString(R.string.dbDefi16)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom17)", "getString(R.string.dbDefi17)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom18)", "getString(R.string.dbDefi18)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom19)", "getString(R.string.dbDefi19)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom20)", "getString(R.string.dbDefi20)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom21)", "getString(R.string.dbDefi21)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom22)", "getString(R.string.dbDefi22)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom23)", "getString(R.string.dbDefi23)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom24)", "getString(R.string.dbDefi24)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom25)", "getString(R.string.dbDefi25)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom26)", "getString(R.string.dbDefi26)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom27)", "getString(R.string.dbDefi27)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom28)", "getString(R.string.dbDefi28)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom29)", "getString(R.string.dbDefi29)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom30)", "getString(R.string.dbDefi30)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom31)", "getString(R.string.dbDefi31)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom32)", "getString(R.string.dbDefi32)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom33)", "getString(R.string.dbDefi33)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom34)", "getString(R.string.dbDefi34)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom35)", "getString(R.string.dbDefi35)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom36)", "getString(R.string.dbDefi36)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom37)", "getString(R.string.dbDefi37)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom38)", "getString(R.string.dbDefi38)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom39)", "getString(R.string.dbDefi39)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom40)", "getString(R.string.dbDefi40)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom41)", "getString(R.string.dbDefi41)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom42)", "getString(R.string.dbDefi42)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom43)", "getString(R.string.dbDefi43)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom44)", "getString(R.string.dbDefi44)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom45)", "getString(R.string.dbDefi45)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom46)", "getString(R.string.dbDefi46)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom47)", "getString(R.string.dbDefi47)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom48)", "getString(R.string.dbDefi48)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom49)", "getString(R.string.dbDefi49)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom50)", "getString(R.string.dbDefi50)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom51)", "getString(R.string.dbDefi51)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom52)", "getString(R.string.dbDefi52)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom53)", "getString(R.string.dbDefi53)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom54)", "getString(R.string.dbDefi54)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom55)", "getString(R.string.dbDefi55)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom56)", "getString(R.string.dbDefi56)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom57)", "getString(R.string.dbDefi57)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom58)", "getString(R.string.dbDefi58)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom59)", "getString(R.string.dbDefi59)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom60)", "getString(R.string.dbDefi60)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom61)", "getString(R.string.dbDefi61)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom62)", "getString(R.string.dbDefi62)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom63)", "getString(R.string.dbDefi63)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom64)", "getString(R.string.dbDefi64)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom65)", "getString(R.string.dbDefi65)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom66)", "getString(R.string.dbDefi66)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom67)", "getString(R.string.dbDefi67)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom68)", "getString(R.string.dbDefi68)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom69)", "getString(R.string.dbDefi69)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom70)", "getString(R.string.dbDefi70)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom71)", "getString(R.string.dbDefi71)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom72)", "getString(R.string.dbDefi72)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom73)", "getString(R.string.dbDefi73)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom74)", "getString(R.string.dbDefi74)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom75)", "getString(R.string.dbDefi75)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom76)", "getString(R.string.dbDefi76)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom77)", "getString(R.string.dbDefi77)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom78)", "getString(R.string.dbDefi78)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom79)", "getString(R.string.dbDefi79)", 0, "Esprit", "Introverti", false, "", 1));
        defisDAO.ajouter(new Defi(0, "getString(R.string.dbDefiNom80)", "getString(R.string.dbDefi80)", 0, "Esprit", "Introverti", false, "", 1));




        defisDAO.close();
    }

    public void fillTrophees () {
        TropheesDAO tropheesDAO = new TropheesDAO(this);
        tropheesDAO.open();

        tropheesDAO.ajouter(new Trophee(0, "getString(R.string.dbTroNom1)", "getString(R.string.dbTro1)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "getString(R.string.dbTroNom2)", "getString(R.string.dbTro2)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "etString(R.string.dbTroNom3)", "getString(R.string.dbTro3)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "getString(R.string.dbTroNom4)", "getString(R.string.dbTro4)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "getString(R.string.dbTroNom5)", "getString(R.string.dbTro5)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "getString(R.string.dbTroNom6)", "getString(R.string.dbTro6)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "getString(R.string.dbTroNom7)", "getString(R.string.dbTro7)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "getString(R.string.dbTroNom8)", "getString(R.string.dbTro8)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "getString(R.string.dbTroNom9)", "getString(R.string.dbTro9)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "getString(R.string.dbTroNom10)", "getString(R.string.dbTro10)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "getString(R.string.dbTroNom11)", "getString(R.string.dbTro11)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "getString(R.string.dbTroNom12)", "getString(R.string.dbTro12)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "getString(R.string.dbTroNom13)", "getString(R.string.dbTro13)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "etString(R.string.dbTroNom14)", "getString(R.string.dbTro14)", false, ""));
        tropheesDAO.ajouter(new Trophee(0, "getString(R.string.dbTroNom15)", "getString(R.string.dbTro15)", false, ""));

        tropheesDAO.close();
    }



}
