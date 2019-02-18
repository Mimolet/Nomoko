package com.nomokoteam.nomoko.database.infos;

public class Utilisateur {
    private long idUtilisateur;
    private String prenom;
    private String personnalite;
    private String avatar;
    private String energie;
    private String esprit;
    private String nature;
    private String tactique;



    public Utilisateur(long idUtilisateur, String prenom, String personnalite, String avatar, String energie, String esprit, String nature, String tactique) {
        this.idUtilisateur = idUtilisateur;
        this.prenom = prenom;
        this.personnalite = personnalite;
        this.avatar = avatar;
        this.energie = energie;
        this.esprit = esprit;
        this.nature = nature;
        this.tactique = tactique;
    }

    public long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPersonnalite() {
        return personnalite;
    }

    public void setPersonnalite(String personnalite) {
        this.personnalite = personnalite;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEnergie() {
        return energie;
    }

    public void setEnergie(String energie) {
        this.energie = energie;
    }

    public String getEsprit() {
        return esprit;
    }

    public void setEsprit(String esprit) {
        this.esprit = esprit;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getTactique() {
        return tactique;
    }

    public void setTactique(String tactique) {
        this.tactique = tactique;
    }
}
