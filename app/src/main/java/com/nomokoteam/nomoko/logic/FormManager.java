package com.nomokoteam.nomoko.logic;

import com.nomokoteam.nomoko.views.R;
import android.content.res.Resources;

public class FormManager {
    private final int SCORE_MAX_ESPRIT = 20;
    private final int SCORE_MAX_ENERGIE = 20;
    private final int SCORE_MAX_NATURE = 24;
    private final int SCORE_MAX_TACTIQUE = 16;

    /*Ces variables stockent le trait dominant de chaque catégorie*/
    private String Esprit;
    private String Energie;
    private String Nature;
    private String Tactique;

    /*Ces variables stockent le pourcentage correspondant à chaque catégorie.*/
    private int EspritPourcent;
    private int EnergiePourcent;
    private int NaturePourcent;
    private int TactiquePourcent;


    public FormManager() {
        /*Il s'agit là des valeurs par défaut, qui seront modifiées dès que le test de personnalité
        * sera complété par l'utilisateur.*/
        this.Esprit = "Extraverti";
        this.Energie = "Intuitif";
        this.Nature = "Pensée";
        this.Tactique = "Jugement";

        this.EspritPourcent = 50;
        this.EnergiePourcent = 50;
        this.NaturePourcent = 50;
        this.TactiquePourcent = 50;
    }

    /*
    * Traite les résultats du questionnaire de personnalité : conversion des scores en pourcentage,
    * association à des traits de personnalités, détermination d'une personnalité parmi les 16.
    * @param : results - Il s'agit du tableau des scores, qui comprends 4 cases, correspondant aux
    * 4 catégories mesurées, dans l'ordre : Esprit, Energie, Nature, Tactique.
    * */
    public void processFormResults (int [] results) {
        for (int i = 0; i < 4; i++) {
            int res = results[i];
            if (res < 0) {
                res = -res;
                switch (i) {
                    case 0: this.Esprit = "Introverti";
                        this.EspritPourcent = 100*res/SCORE_MAX_ESPRIT;
                        break;

                    case 1: this.Energie = "Observateur";
                        this.EnergiePourcent = 100*res/SCORE_MAX_ENERGIE;
                        break;

                    case 2: this.Nature = "Sentiment";
                        this.NaturePourcent = 100*res/SCORE_MAX_NATURE;
                        break;

                    case 3: this.Tactique = "Prospection";
                        this.TactiquePourcent = 100*res/SCORE_MAX_TACTIQUE;
                        break;
                }
            }
            switch (i) {
                case 0: this.EspritPourcent = 100*res/SCORE_MAX_ESPRIT;
                    break;

                case 1: this.EnergiePourcent = 100*res/SCORE_MAX_ENERGIE;
                    break;

                case 2: this.NaturePourcent = 100*res/SCORE_MAX_NATURE;
                    break;

                case 3: this.TactiquePourcent = 100*res/SCORE_MAX_TACTIQUE;
                    break;
            }

        }
    }

    /*GETTERS & SETTERS*/

    public void setPourcent (String cat, int valeur) {
        if (valeur > 100 || valeur < 0) {
            System.out.println("Erreur : la valeur numérique donnée doit être comprise entre 0 et 100");
        } else if (cat.equals("Esprit")) {
            this.EspritPourcent = valeur;
        } else if (cat.equals("Energie")) {
            this.EnergiePourcent = valeur;
        } else if (cat.equals("Nature")) {
            this.NaturePourcent = valeur;
        } else if (cat.equals("Tactique")) {
            this.TactiquePourcent = valeur;
        } else {
            System.out.println("Erreur : catégorie inconnue.");
        }
    }

    public int getPourcent (String cat) {
        if (cat.equals("Esprit")) {
            return this.EspritPourcent;
        } else if (cat.equals("Energie")) {
            return this.EnergiePourcent;
        } else if (cat.equals("Nature")) {
            return this.NaturePourcent;
        } else if (cat.equals("Tactique")) {
            return this.TactiquePourcent;
        } else {
            System.out.println("Erreur : catégorie inconnue.");
            return -1;
        }
    }

    public void setTrait (String cat, String valeur) {
        if (cat.equals("Esprit")) {
            if (valeur.equals("Introverti") || valeur.equals("Extraverti")) {
                this.Esprit = valeur;
            }
        } else if (cat.equals("Energie")) {
            if (valeur.equals("Intuitif") || valeur.equals("Observateur")) {
                this.Energie = valeur;
            }
        } else if (cat.equals("Nature")) {
            if (valeur.equals("Pensee") || valeur.equals("Sentiment")) {
                this.Nature = valeur;
            }
        } else if (cat.equals("Tactique")) {
            if (valeur.equals("Jugement") || valeur.equals("Prospection")) {
                this.Tactique = valeur;
            }
        } else {
            System.out.println("Erreur : catégorie inconnue.");
        }
    }

    public String getTrait (String cat) {
        if (cat.equals("Esprit")) {
            return this.Esprit;
        } else if (cat.equals("Energie")) {
            return this.Energie;
        } else if (cat.equals("Nature")) {
            return this.Nature;
        } else if (cat.equals("Tactique")) {
            return this.Tactique;
        } else {
            System.out.println("Erreur : catégorie inconnue.");
            return null;
        }
    }
}
