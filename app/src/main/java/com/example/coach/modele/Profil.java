package com.example.coach.modele;

public class Profil {
    private int poids;
    private int taille;
    private int age;
    private int sexe;
    private float img = 0;
    private String message = "";

    // constantes
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus

    public Profil(int poids, int taille, int age, int sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
    }

    public int getPoids() {
        return poids;
    }

    public int getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public int getSexe() {
        return sexe;
    }

    public float getImg() {
        if (img == 0) {
            float tailleMetre = (float) taille / 100;
            float S = (sexe == 1) ? 1 : 0;
            img = (float) ((1.2 * poids / (tailleMetre * tailleMetre)) + (0.23 * age) - (10.83 * S) - 5.4);
        }

        return img;
    }

    public String getMessage() {
        if (message.isEmpty()) {
            img = getImg();

            if (sexe == 0) {
                if (img < 15) {
                    message = "Trop maigre";
                } else if (img >= 15 && img <= 30) {
                    message = "Normal";
                } else if (img > 30) {
                    message = "Trop de graisse";
                }
            } else {
                if (img < 10) {
                    message = "Trop maigre";
                } else if (img >= 10 && img <= 25) {
                    message = "Normal";
                } else if (img > 25) {
                    message = "Trop de graisse";
                }
            }
        }

        return message;
    }
}
