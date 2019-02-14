package com.nomokoteam.nomoko.database.infos;

public class Utilisateur {
    private long idUtilisateur;
    private String prenom;
    private int age;
    private String sexe;
    private String personnalite;
    private String avatar;

    public Utilisateur(long idUtilisateur, String prenom, int age, String sexe, String personnalite, String avatar) {
        this.idUtilisateur = idUtilisateur;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.personnalite = personnalite;
        this.avatar = avatar;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
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
}
