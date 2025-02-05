package com.example.coach.vue;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coach.R;
import com.example.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;

    private Controle controle;

    private void ecouteCalcul() {
        btnCalc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;
                Integer sexe = 0;

                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch (Exception e) {}

                sexe = rdHomme.isChecked() ? 1 : 0;

                if (poids == 0 || taille == 0 || age == 0) {
                    Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                affichResult(poids, taille, age, sexe);
            }
        });
    }

    private void affichResult(int poids, int taille, int age, int sexe) {
        controle.creerProfil(poids, taille, age, sexe);

        float img = controle.getImg();
        String message = controle.getMessage();

        String imgFormatted = String.format("%.01f", img);

        switch (message) {
            case "Normal":
                // Affichage de l'image correspondante et du message
                imgSmiley.setImageResource(R.drawable.normal); // Image "normal"
                lblIMG.setText(imgFormatted + " : IMG normal");
                lblIMG.setTextColor(Color.GREEN);  // Texte en vert pour "normal"
                break;

            case "Trop maigre":
                // Affichage de l'image correspondante et du message
                imgSmiley.setImageResource(R.drawable.maigre); // Image pour "trop faible"
                lblIMG.setText(imgFormatted + " : IMG trop faible");
                lblIMG.setTextColor(Color.RED);  // Texte en rouge pour "trop faible"
                break;

            case "Trop de graisse":
                // Affichage de l'image correspondante et du message
                imgSmiley.setImageResource(R.drawable.graisse); // Image pour "trop élevé"
                lblIMG.setText(imgFormatted + " : IMG trop élevé");
                lblIMG.setTextColor(Color.RED);  // Texte en rouge pour "trop élevé"
                break;

            default:
        }
    }

    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids) ;
        txtTaille = (EditText) findViewById(R.id.txtTaille) ;
        txtAge = (EditText) findViewById(R.id.txtAge) ;
        rdHomme = (RadioButton) findViewById(R.id.rdHomme) ;
        lblIMG = (TextView) findViewById(R.id.lblIMG) ;
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley) ;
        btnCalc = (Button) findViewById(R.id.btnCalc) ;

        controle = Controle.getInstance();

        ecouteCalcul();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
    }
}