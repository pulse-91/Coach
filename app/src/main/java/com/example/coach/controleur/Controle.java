package com.example.coach.controleur;

import com.example.coach.modele.Profil;

public final class Controle {
    private static Controle instance = null;
    private static Profil profil;

    private Controle() {
        super();
    }

    public static Controle getInstance() {
        if (Controle.instance == null) {
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    public void creerProfil(int poids, int taille, int age, int sexe) {
        this.profil = new Profil(poids, taille, age, sexe);
    }

    public float getImg() {
        if (profil == null) {
            return 0;
        }
        return profil.getImg();
    }

    public String getMessage() {
        if (profil == null) {
            return "";
        }
        return profil.getMessage();
    }
}
