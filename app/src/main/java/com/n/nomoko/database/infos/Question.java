package com.n.nomoko.database.infos;

public class Question {
    private long idQuest;
    private int texteID;
    private long idCat1;
    private long idCat2;

    public Question (long idQuest, int texteID, long idCat1, long idCat2) {
        super();
        this.idQuest = idQuest;
        this.texteID = texteID;
        this.idCat1 = idCat1;
        this.idCat2 = idCat2;
    }

    public long getIdQuest() {
        return idQuest;
    }

    public void setIdQuest(long idQuest) {
        this.idQuest = idQuest;
    }

    public int getTexteID() {
        return texteID;
    }

    public void setTexteID(int texteID) {
        this.texteID = texteID;
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
