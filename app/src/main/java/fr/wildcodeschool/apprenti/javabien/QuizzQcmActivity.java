package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class QuizzQcmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_qcm);
        //mais que fait la police
        Typeface face= Typeface.createFromAsset(getAssets(), "alwyn.ttf");
        //récupération de l'objet
        Intent intent = getIntent();
        final Contenant exo = (Contenant) intent.getSerializableExtra("amont");

        //mise en place du cours

        TextView info = (TextView) findViewById(R.id.info);
        info.setText(exo.getCours());
        info.setTypeface(face);

        //mise en place de la question

        TextView question = (TextView) findViewById(R.id.question);
        question.setText(exo.getQuestion());
        question.setTypeface(null,face.BOLD);


        final Button boutonVrai = (Button) findViewById(R.id.boutonVrai); // Récupération de l'instance bouton 1
        boutonVrai.setText(exo.getPropositon());// mise en place du texte du boutton 1
        boutonVrai.setTextSize(10);//couleur  textbouton
        boutonVrai.setTextIsSelectable(false);
        boutonVrai.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testAndToast(exo.getPropositon(), exo.getReponse(), getApplicationContext(), exo);
                    }
                }
        );

        Button boutonFaux = (Button) findViewById(R.id.boutonFaux); // Récupération de l'instance bouton 1
        boutonFaux.setText(exo.getProposition2()); //mise en place du texte du boutton 2
        boutonFaux.setTextSize(10);//couleur text bouton
        boutonFaux.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              testAndToast(exo.getProposition2(), exo.getReponse(), getApplicationContext(), exo);
                                          }
                                      }
        );

        Button boutonFaux2 = (Button) findViewById(R.id.boutonFaux2); // Récupération de l'instance bouton 1
        boutonFaux2.setText(exo.getProposition3());// mise en place du texte du boutton 3
        boutonFaux2.setTextSize(10);//couleur text bouton
        boutonFaux2.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               testAndToast(exo.getProposition3(), exo.getReponse(), getApplicationContext(), exo);
                                           }
                                       }
        );
        // récupération réponse
        String reponse = exo.getReponse();

        // vérification de la réponse

    }

    // Méthode déclecnchée par le listener lorsqu'on appui sur le bouton se produit
    public void testAndToast(String test, String reponse, final Context context, final Contenant exo) {


        if (test.equals(reponse)) {
           // Toast.makeText(this, "Super", Toast.LENGTH_SHORT).show();
            // lancement du son juste

            //sauvegarde  pour le quizz
            Sauvegarde.sauvegardeJuste(exo, context);

            // lancement du son juste
            MediaPlayer vrai = MediaPlayer.create(getApplicationContext(), R.raw.vrai);
            vrai.start();
            // apparition du bouton suivant et création de l'intent
            exo_Suivant(exo);

        } else {
           // Toast.makeText(this, "Faux", Toast.LENGTH_SHORT).show();
            // lancement du son faux
            MediaPlayer wrong = MediaPlayer.create(getApplicationContext(), R.raw.boo);
            wrong.start();
            // sauvegarde faux
            Sauvegarde.sauvegardeFaux(exo,context);
            exo_Suivant(exo);

        }

    }

    // methode de redirection et de lancement de l'exercice suivant
    public void exo_Suivant(Contenant exo) {

        //recupération de la liste des exos du quizz dans listQuizz
        ArrayList<Contenant> listQuizz = new ArrayList<Contenant>();
        listQuizz = ListCategorie.redirect(exo, 0, getApplicationContext());

        //création d'un exo moisi pour la fin du quizz
        Contenant moisi = new Contenant("quizz",exo.getQuizz_categorie(),150,"","", "", "",
                "", "","","","", "","", "",1);
        // ajout du contenant moisi pour la fin
        listQuizz.add(moisi);

        // si l'exo suivant de la liste egal qcm

        if (listQuizz.get(exo.getId_exos() + 1).getExoType().equals("qcm")) {
            Intent intent = new Intent(QuizzQcmActivity.this, QuizzQcmActivity.class);
            intent.putExtra("amont", listQuizz.get(exo.getId_exos() + 1));
            finish();
            startActivity(intent);
            // si l'exo suivant de la liste egal insert
        } else if (listQuizz.get(exo.getId_exos() + 1).getExoType().equals("insert")) {
            Intent intent = new Intent(QuizzQcmActivity.this, QuizzInsertActivity.class);
            intent.putExtra("amont", listQuizz.get(exo.getId_exos() + 1));
            finish();
            startActivity(intent);
            // sinon lancer la mainActivity
        } else {
            Intent intent = new Intent(QuizzQcmActivity.this, QuizzFinActivity.class);
            Sauvegarde.sauvegardeExo(exo,getApplicationContext());
            intent.putExtra("amont",exo);
            startActivity(intent);
        }

    }
}
