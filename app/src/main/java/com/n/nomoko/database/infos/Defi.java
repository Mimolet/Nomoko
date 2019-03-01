package com.n.nomoko.database.infos;

public class Defi {
    private long idDefi;
    private int nomDefiID;
    private int txtDefiID;
    private int dureeDefi;
    private String categorieDefi;
    private String traitDefi;
    private boolean reussiDefi;
    private boolean enCours;
    private int illustrationPathDefi;
    private int difficulte;

    public Defi(long idDefi, int nomDefi, int txtDefiID, int dureeDefi, String categorieDefi, String traitDefi, boolean reussiDefi, boolean enCours, int illustrationPathDefi, int difficulte) {
        this.idDefi = idDefi;
        this.nomDefiID = nomDefi;
        this.txtDefiID = txtDefiID;
        this.dureeDefi = dureeDefi;
        this.categorieDefi = categorieDefi;
        this.traitDefi = traitDefi;
        this.reussiDefi = reussiDefi;
        this.enCours = enCours;
        this.illustrationPathDefi = illustrationPathDefi;
        this.difficulte = difficulte;
    }


    public long getIdDefi() {
        return idDefi;
    }

    public void setIdDefi(long idDefi) {
        this.idDefi = idDefi;
    }

    public int getNomDefiID() {
        return nomDefiID;
    }

    public void setNomDefiID(int nomDefiID) {
        this.nomDefiID = nomDefiID;
    }

    public int getTxtDefiID() {
        return txtDefiID;
    }

    public void setTxtDefiID(int txtDefiID) {
        this.txtDefiID = txtDefiID;
    }

    public int getDureeDefi() {
        return dureeDefi;
    }

    public void setDureeDefi(int dureeDefi) {
        this.dureeDefi = dureeDefi;
    }

    public String getCategorieDefi() {
        return categorieDefi;
    }

    public void setCategorieDefi(String categorieDefi) {
        this.categorieDefi = categorieDefi;
    }

    public String getTraitDefi() {
        return traitDefi;
    }

    public void setTraitDefi(String traitDefi) {
        this.traitDefi = traitDefi;
    }

    public boolean isReussiDefi() {
        return reussiDefi;
    }

    public void setReussiDefi(boolean reussiDefi) {
        this.reussiDefi = reussiDefi;
    }

    public int getIllustrationPathDefi() {
        return illustrationPathDefi;
    }

    public void setIllustrationPathDefi(int illustrationPathDefi) {
        this.illustrationPathDefi = illustrationPathDefi;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public boolean isEnCours() {
        return enCours;
    }

    public void setEnCours(boolean enCours) {
        this.enCours = enCours;
    }
}
