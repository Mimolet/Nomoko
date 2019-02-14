package com.nomokoteam.nomoko.database.infos;

public class Trophee {
    private long idTrophee;
    private String nomTro;
    private String txtTro;
    private boolean obtenuTro;
    private String illustrationPathTro;

    public Trophee(long idTrophee, String nomTro, String txtTro, boolean obtenuTro, String illustrationPathTro) {
        this.idTrophee = idTrophee;
        this.nomTro = nomTro;
        this.txtTro = txtTro;
        this.obtenuTro = obtenuTro;
        this.illustrationPathTro = illustrationPathTro;
    }


    public long getIdTrophee() {
        return idTrophee;
    }

    public void setIdTrophee(long idTrophee) {
        this.idTrophee = idTrophee;
    }

    public String getNomTro() {
        return nomTro;
    }

    public void setNomTro(String nomTro) {
        this.nomTro = nomTro;
    }

    public String getTxtTro() {
        return txtTro;
    }

    public void setTxtTro(String txtTro) {
        this.txtTro = txtTro;
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
