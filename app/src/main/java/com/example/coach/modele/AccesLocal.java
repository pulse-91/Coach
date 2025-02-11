package com.example.coach.modele;

import static com.example.coach.outils.MesOutils.convertStringToDate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {

    // 2D1. Déclaration des propriétés
    private static final String NOM_BDD = "bdCoach.sqlite";
    private static final int VERSION_BDD = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    // 2D2. Singleton
    private static AccesLocal instance = null;

    /**
     * Constructeur privé (singleton)
     * @param context Contexte de l'application
     */
    private AccesLocal(Context context) {
        accesBD = new MySQLiteOpenHelper(context, NOM_BDD, VERSION_BDD);
    }

    /**
     * Méthode statique pour obtenir l'instance unique de la classe (singleton)
     * @param context Contexte de l'application
     * @return Instance unique de AccesLocal
     */
    public static synchronized AccesLocal getInstance(Context context) {
        if (instance == null) {
            instance = new AccesLocal(context);
        }
        return instance;
    }

    // 2D3. Ajout d’un profil dans la base
    public void ajout(Profil profil) {
        bd = accesBD.getWritableDatabase(); // Accès en écriture

        ContentValues values = new ContentValues();
        values.put("datemesure", String.valueOf(profil.getDateMesure().getTime())); // Stockage en timestamp
        values.put("poids", profil.getPoids());
        values.put("taille", profil.getTaille());
        values.put("age", profil.getAge());
        values.put("sexe", profil.getSexe());

        bd.insert("profil", null, values);
        bd.close(); // Fermeture de la connexion
    }

    // 2D4. Récupération du dernier profil
    public Profil recupDernier() {
        Profil profil = null;
        bd = accesBD.getReadableDatabase(); // Accès en lecture

        String req = "SELECT * FROM profil ORDER BY datemesure DESC LIMIT 1";
        Cursor cursor = bd.rawQuery(req, null);

        if (cursor.moveToFirst()) { // Vérifier s'il y a une ligne disponible
            int poids = cursor.getInt(1);
            int taille = cursor.getInt(2);
            int age = cursor.getInt(3);
            int sexe = cursor.getInt(4);
            Date dateMesure = convertStringToDate(cursor.getString(0));
            Log.d("DEBUG", "date " + cursor.getString(0) + " " +  dateMesure);

            profil = new Profil(poids, taille, age, sexe, dateMesure);
        }

        cursor.close();
        bd.close();
        return profil;
    }
}