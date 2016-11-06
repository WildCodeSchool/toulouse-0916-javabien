package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import fr.wildcodeschool.apprenti.javabien.Model.Exercice;

public class QuizzQcmActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // left button
        this.boutonGauche.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testAndToast(exo.getPropositon(), exo.getReponse(), getApplicationContext(), exo);
                    }
                }
        );
        // middle button
        this.boutonCentre.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              testAndToast(exo.getProposition2(), exo.getReponse(), getApplicationContext(), exo);
                                          }
                                      }
        );
        // right button
        this.boutonDroite.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               testAndToast(exo.getProposition3(), exo.getReponse(), getApplicationContext(), exo);
                                           }
                                       }
        );
    }
    // Méthode déclenchée par le listener lorsqu'on appui sur le bouton se produit
    public void testAndToast(String test, String reponse, final Context context, final Exercice exo) {

        if (test.equals(reponse)) {
            //sauvegarde  pour le quizz
            Sauvegarde.sauvegardeJuste(exo, context);

            // lancement du son juste
            this.juste.start();
            // apparition du bouton suivant et création de l'intent
            exo_Suivant(exo);

        } else {
            // lancement du son faux
            this.wrong2.start();
            // sauvegarde faux
            Sauvegarde.sauvegardeFaux(exo,context);
            exo_Suivant(exo);

        }
    }

    // methode de redirection et de lancement de l'exercice suivant
    public void exo_Suivant(Exercice exo) {

        //recupération de la liste des exos du quizz dans listQuizz
        ArrayList<Exercice> listQuizz = new ArrayList<>();
        listQuizz = ListCategorie.redirect(exo, getApplicationContext());


            //création d'un exo moisi pour la fin du quizz
            Exercice quizzEnder = new Exercice(Constante.QUIZZ, exo.getQuizz_categorie(), listQuizz.size(),"end", 1);
            // ajout du contenant moisi pour la fin
            listQuizz.add(quizzEnder);

        // si l'exo suivant de la liste egal qcm

        if (listQuizz.get(exo.getId_exos() + 1).getExoType().equals(Constante.QCM)) {
            Intent intent = new Intent(QuizzQcmActivity.this, QuizzQcmActivity.class);
            intent.putExtra(Constante.SERIALIZED_EXERCICE, listQuizz.get(exo.getId_exos() + 1));
            finish();
            startActivity(intent);

        }  else {
            Intent intent = new Intent(QuizzQcmActivity.this, QuizzFinActivity.class);
            Sauvegarde.sauvegardeExo(exo,getApplicationContext());
            intent.putExtra(Constante.SERIALIZED_EXERCICE,exo);
            finish();
            startActivity(intent);
        }

    }
}
