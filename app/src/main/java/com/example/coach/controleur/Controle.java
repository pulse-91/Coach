package com.example.coach.controleur;

import android.content.Context;
import android.util.Log;

import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;
import com.example.coach.modele.AccesLocal;

import java.util.Date;

public class Controle {
    private static Controle instance = null;
    private static Profil profil;
    private static final String nomFic = "saveprofil";
    private static AccesLocal accesLocal;

    private Controle(Context context) {
        super();
        // recupSerialize(context);
        accesLocal = AccesLocal.getInstance(context); // Initialisation de l'accès local
        profil = accesLocal.recupDernier();
    }

    public static Controle getInstance(Context context) {
        if (Controle.instance == null) {
            Controle.instance = new Controle(context);
        }
        return Controle.instance;
    }

    public void creerProfil(int poids, int taille, int age, int sexe, Context context) {
        profil = new Profil(poids, taille, age, sexe, new Date());
        accesLocal.ajout(profil);
        // Serializer.serialize(nomFic, profil, context);
        // Log.d("DEBUG", "serialize " + profil);
    }

    private static void recupSerialize(Context context) {
        profil = (Profil) Serializer.deSerialize(nomFic, context);
        Log.d("DEBUG", "recupSerialize " + profil);
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
