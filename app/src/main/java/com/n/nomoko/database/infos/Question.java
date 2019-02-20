package com.n.nomoko.database.infos;

public class Question {
    private long idQuest;
    private String texte;
    private long idCat1;
    private long idCat2;

    public Question (long idQuest, String texte, long idCat1, long idCat2) {
        super();
        this.idQuest = idQuest;
        this.texte = texte;
        this.idCat1 = idCat1;
        this.idCat2 = idCat2;
    }

    public long getIdQuest() {
        return idQuest;
    }

    public void setIdQuest(long idQuest) {
        this.idQuest = idQuest;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public long getIdCat1() {
        return idCat1;
    }

    public void setIdCat1(long idCat1) {
        this.idCat1 = idCat1;
    }

    public long getIdCat2() {
        return idCat2;
    }

    public void setIdCat2(long idCat2) {
        this.idCat2 = idCat2;
    }
}
