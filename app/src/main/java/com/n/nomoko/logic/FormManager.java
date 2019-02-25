package com.n.nomoko.logic;


public class FormManager {
    private final int SCORE_MAX_ESPRIT = 20;
    private final int SCORE_MAX_ENERGIE = 20;
    private final int SCORE_MAX_NATURE = 24;
    private final int SCORE_MAX_TACTIQUE = 16;

    /*Ces variables stockent le trait dominant de chaque catégorie*/
    /*E : Extraversion / I : Introversion*/
    private String Esprit;
    /*S : Sensation / N : Intuition*/
    private String Energie;
    /*T : Pensée / F : Sentiment*/
    private String Nature;
    /*J : Jugement / P : Perception*/
    private String Tactique;

    /*Ces variables stockent le pourcentage correspondant à chaque catégorie.*/
    private int EspritPourcent;
    private int EnergiePourcent;
    private int NaturePourcent;
    private int TactiquePourcent;

    /*Type de personnalité*/
    private String personnalite;

    public FormManager() {
        /*Il s'agit là des valeurs par défaut, qui seront modifiées dès que le test de personnalité
        * sera complété par l'utilisateur.*/
        this.Esprit = "E";
        this.Energie = "N";
        this.Nature = "T";
        this.Tactique = "J";

        this.EspritPourcent = 50;
        this.EnergiePourcent = 50;
        this.NaturePourcent = 50;
        this.TactiquePourcent = 50;

        personnalite = "";
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
                    case 0: this.Esprit = "I";
                        this.EspritPourcent = 100*res/SCORE_MAX_ESPRIT;
                        break;

                    case 1: this.Energie = "S";
                        this.EnergiePourcent = 100*res/SCORE_MAX_ENERGIE;
                        break;

                    case 2: this.Nature = "F";
                        this.NaturePourcent = 100*res/SCORE_MAX_NATURE;
                        break;

                    case 3: this.Tactique = "P";
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
        String type = this.Esprit + this.Energie + this.Nature +this.Tactique;
        switch (type) {
            case "INTJ":
                this.personnalite = "Analyste";
                break;
            case "INTP":
                this.personnalite = "Analyste";
                break;
            case "ENTJ":
                this.personnalite = "Analyste";
                break;
            case "ENTP":
                this.personnalite = "Analyste";
                break;
            case "INFJ":
                this.personnalite = "Diplomate";
                break;
            case "INFP":
                this.personnalite = "Diplomate";
                break;
            case "ENFJ":
                this.personnalite = "Diplomate";
                break;
            case "ENFP":
                this.personnalite = "Diplomate";
                break;
            case "ISTJ":
                this.personnalite = "Sentinelle";
                break;
            case "ISFJ":
                this.personnalite = "Sentinelle";
                break;
            case "ESTJ":
                this.personnalite = "Sentinelle";
                break;
            case "ESFJ":
                this.personnalite = "Sentinelle";
                break;
            case "ISTP":
                this.personnalite = "Explorateur";
                break;
            case "ISFP":
                this.personnalite = "Explorateur";
                break;
            case "ESTP":
                this.personnalite = "Explorateur";
                break;
            case "ESFP":
                this.personnalite = "Explorateur";
                break;
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
            if (valeur.equals("I") || valeur.equals("E")) {
                this.Esprit = valeur;
            }
        } else if (cat.equals("Energie")) {
            if (valeur.equals("N") || valeur.equals("S")) {
                this.Energie = valeur;
            }
        } else if (cat.equals("Nature")) {
            if (valeur.equals("T") || valeur.equals("F")) {
                this.Nature = valeur;
            }
        } else if (cat.equals("Tactique")) {
            if (valeur.equals("J") || valeur.equals("P")) {
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

    public String getPersonnalite() {
        return personnalite;
    }
}
