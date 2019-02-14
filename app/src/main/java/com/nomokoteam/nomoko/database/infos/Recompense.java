package com.nomokoteam.nomoko.database.infos;

public class Recompense {
    private long idRecompense;
    private String txtRec;
    private boolean obtenuRec;

    public Recompense(long idRecompense, String txtRec, boolean obtenuRec) {
        this.idRecompense = idRecompense;
        this.txtRec = txtRec;
        this.obtenuRec = obtenuRec;
    }

    public long getIdRecompense() {
        return idRecompense;
    }

    public void setIdRecompense(long idRecompense) {
        this.idRecompense = idRecompense;
    }

    public String getTxtRec() {
        return txtRec;
    }

    public void setTxtRec(String txtRec) {
        this.txtRec = txtRec;
    }

    public boolean isObtenuRec() {
        return obtenuRec;
    }

    public void setObtenuRec(boolean obtenuRec) {
        this.obtenuRec = obtenuRec;
    }
}
