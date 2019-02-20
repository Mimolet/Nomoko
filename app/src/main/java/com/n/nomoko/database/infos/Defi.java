package com.n.nomoko.database.infos;

public class Defi {
    private long idDefi;
    private String nomDefi;
    private String txtDefi;
    private int dureeDefi;
    private String categorieDefi;
    private String traitDefi;
    private boolean reussiDefi;
    private String illustrationPathDefi;
    private int difficulte;

    public Defi(long idDefi, String nomDefi, String txtDefi, int dureeDefi, String categorieDefi, String traitDefi, boolean reussiDefi, String illustrationPathDefi, int difficulte) {
        this.idDefi = idDefi;
        this.nomDefi = nomDefi;
        this.txtDefi = txtDefi;
        this.dureeDefi = dureeDefi;
        this.categorieDefi = categorieDefi;
        this.traitDefi = traitDefi;
        this.reussiDefi = reussiDefi;
        this.illustrationPathDefi = illustrationPathDefi;
        this.difficulte = difficulte;
    }


    public long getIdDefi() {
        return idDefi;
    }

    public void setIdDefi(long idDefi) {
        this.idDefi = idDefi;
    }

    public String getNomDefi() {
        return nomDefi;
    }

    public void setNomDefi(String nomDefi) {
        this.nomDefi = nomDefi;
    }

    public String getTxtDefi() {
        return txtDefi;
    }

    public void setTxtDefi(String txtDefi) {
        this.txtDefi = txtDefi;
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

    public String getIllustrationPathDefi() {
        return illustrationPathDefi;
    }

    public void setIllustrationPathDefi(String illustrationPathDefi) {
        this.illustrationPathDefi = illustrationPathDefi;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }
}
