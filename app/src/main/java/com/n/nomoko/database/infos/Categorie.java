package com.n.nomoko.database.infos;

public class Categorie {
    private long idCat;
    private int nomCatID;
    private String signeCat;


    public Categorie(long idCat, int nomCatID, String signeCat) {
        this.idCat = idCat;
        this.nomCatID = nomCatID;
        this.signeCat = signeCat;
    }


    public long getIdCat() {
        return idCat;
    }

    public void setIdCat(long idCat) {
        this.idCat = idCat;
    }

    public int getNomCatID() {
        return nomCatID;
    }

    public void setNomCatID(int nomCatID) {
        this.nomCatID = nomCatID;
    }

    public String getSigneCat() {
        return signeCat;
    }

    public void setSigneCat(String signeCat) {
        this.signeCat = signeCat;
    }
}
