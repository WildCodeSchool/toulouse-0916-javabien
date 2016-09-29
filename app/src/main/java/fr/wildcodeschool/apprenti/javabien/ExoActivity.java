package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class ExoActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo);

        //récupération de l'objet
        Intent intent = getIntent();
        final Contenant exo = (Contenant)intent.getSerializableExtra("amont");

        //mise en place du cours

        TextView info = (TextView)findViewById(R.id.info);
        info.setText(exo.getCours());

        //mise en place de la question

        TextView question = (TextView)findViewById(R.id.question);
        question.setText(exo.getQuestion());


        final Button boutonVrai=(Button)findViewById(R.id.boutonVrai); // Récupération de l'instance bouton 1
        boutonVrai.setText(exo.getPropositon());// mise en place du texte du boutton 1
        boutonVrai.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testAndToast(exo.getPropositon(),exo.getReponse());
                    }
                }
        );

        Button boutonFaux=(Button)findViewById(R.id.boutonFaux); // Récupération de l'instance bouton 1
        boutonFaux.setText(exo.getProposition2()); //mise en place du texte du boutton 2
        boutonFaux.setOnClickListener( new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               testAndToast(exo.getProposition2(),exo.getReponse());
                                           }
                                       }
        );

        Button boutonFaux2=(Button)findViewById(R.id.boutonFaux2); // Récupération de l'instance bouton 1
        boutonFaux2.setText(exo.getProposition3()); // mise en place du texte du boutton 3
        boutonFaux2.setOnClickListener( new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                testAndToast(exo.getProposition3(),exo.getReponse());
                                            }
                                        }
        );
        // récupération réponse
        String reponse = exo.getReponse();

        // vérification de la réponse

    }

    // Méthode déclecnchée par le listener lorsqu'on appui sur le bouton se produit
    public void testAndToast(String test,String reponse){








        if (test.equals(reponse)){
            Toast.makeText(this,"Super", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Faux", Toast.LENGTH_SHORT).show();
        }

    }


}