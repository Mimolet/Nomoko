package com.n.nomoko.database.infos;

public class Trophee {
    private long idTrophee;
    private int nomTroID;
    private int txtTroID;
    private boolean obtenuTro;
    private String illustrationPathTro;

    public Trophee(long idTrophee, int nomTroID, int txtTroID, boolean obtenuTro, String illustrationPathTro) {
        this.idTrophee = idTrophee;
        this.nomTroID = nomTroID;
        this.txtTroID = txtTroID;
        this.obtenuTro = obtenuTro;
        this.illustrationPathTro = illustrationPathTro;
    }


    public long getIdTrophee() {
        return idTrophee;
    }

    public void setIdTrophee(long idTrophee) {
        this.idTrophee = idTrophee;
    }

    public int getNomTroID() {
        return nomTroID;
    }

    public void setNomTroID(int nomTroID) {
        this.nomTroID = nomTroID;
    }

    public int getTxtTroID() {
        return txtTroID;
    }

    public void setTxtTroID(int txtTroID) {
        this.txtTroID = txtTroID;
    }

    public boolean isObtenuTro() {
        return obtenuTro;
    }

    public void setObtenuTro(boolean obtenuTro) {
        this.obtenuTro = obtenuTro;
    }

    public String getIllustrationPathTro() {
        return illustrationPathTro;
    }

    public void setIllustrationPathTro(String illustrationPathTro) {
        this.illustrationPathTro = illustrationPathTro;
    }
}
