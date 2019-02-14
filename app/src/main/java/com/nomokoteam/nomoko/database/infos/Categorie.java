package com.nomokoteam.nomoko.database.infos;

public class Categorie {
    private long idCat;
    private String nomCat;
    private String signeCat;


    public Categorie(long idCat, String nomCat, String signeCat) {
        this.idCat = idCat;
        this.nomCat = nomCat;
        this.signeCat = signeCat;
    }


    public long getIdCat() {
        return idCat;
    }

    public void setIdCat(long idCat) {
        this.idCat = idCat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public String getSigneCat() {
        return signeCat;
    }

    public void setSigneCat(String signeCat) {
        this.signeCat = signeCat;
    }
}
