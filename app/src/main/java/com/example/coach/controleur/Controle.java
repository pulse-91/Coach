package com.example.coach.controleur;

import android.content.Context;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

public class Controle {
    private static Controle instance = null;
    private static Profil profil;
    private static final String nomFic = "saveprofil";

    private Controle(Context context) {
        super();
        recupSerialize(context);
    }

    public static Controle getInstance(Context context) {
        if (Controle.instance == null) {
            Controle.instance = new Controle(context);
        }
        return Controle.instance;
    }

    public void creerProfil(int poids, int taille, int age, int sexe, Context context) {
        profil = new Profil(poids, taille, age, sexe);
        Serializer.serialize(nomFic, profil, context);
    }

    private static void recupSerialize(Context context) {
        profil = (Profil) Serializer.deSerialize(nomFic, context);
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

    public Integer getPoids() {
        return (profil == null) ? null : profil.getPoids();
    }

    public Integer getTaille() {
        return (profil == null) ? null : profil.getTaille();
    }

    public Integer getAge() {
        return (profil == null) ? null : profil.getAge();
    }

    public Integer getSexe() {
        return (profil == null) ? null : profil.getSexe();
    }
}
