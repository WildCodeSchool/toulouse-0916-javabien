package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class QuizzQcmActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // left button
        boutonGauche.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testAndToast(exo.getPropositon(), exo.getReponse(), getApplicationContext(), exo);
                    }
                }
        );
        // middle button
        boutonCentre.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              testAndToast(exo.getProposition2(), exo.getReponse(), getApplicationContext(), exo);
                                          }
                                      }
        );
        // right button
        boutonDroite.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               testAndToast(exo.getProposition3(), exo.getReponse(), getApplicationContext(), exo);
                                           }
                                       }
        );
    }
    // Méthode déclenchée par le listener lorsqu'on appui sur le bouton se produit
    public void testAndToast(String test, String reponse, final Context context, final Contenant exo) {

        if (test.equals(reponse)) {
            //sauvegarde  pour le quizz
            Sauvegarde.sauvegardeJuste(exo, context);

            // lancement du son juste
            MediaPlayer vrai = MediaPlayer.create(getApplicationContext(), R.raw.vrai);
            vrai.start();
            // apparition du bouton suivant et création de l'intent
            exo_Suivant(exo);

        } else {
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
        listQuizz = ListCategorie.redirect(exo, getApplicationContext());

        //création d'un exo moisi pour la fin du quizz
        Contenant moisi = new Contenant("quizz",exo.getQuizz_categorie(),150,"","", "", "",
                "", "","","","", "","", "",1);
        // ajout du contenant moisi pour la fin
        listQuizz.add(moisi);

        // si l'exo suivant de la liste egal qcm

        if (listQuizz.get(exo.getId_exos() + 1).getExoType().equals("qcm")) {
            Intent intent = new Intent(QuizzQcmActivity.this, QuizzQcmActivity.class);
            intent.putExtra("serialized exercice", listQuizz.get(exo.getId_exos() + 1));
            finish();
            startActivity(intent);

        }  else {
            Intent intent = new Intent(QuizzQcmActivity.this, QuizzFinActivity.class);
            Sauvegarde.sauvegardeExo(exo,getApplicationContext());
            intent.putExtra("serialized exercice",exo);
            finish();
            startActivity(intent);
        }

    }
}
