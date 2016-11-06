package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.content.Intent;
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
                        checkTrue(exo.getPropositon(), exo.getReponse(), getApplicationContext(), exo);
                    }
                }
        );
        // middle button
        this.boutonCentre.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              checkTrue(exo.getProposition2(), exo.getReponse(), getApplicationContext(), exo);
                                          }
                                      }
        );
        // right button
        this.boutonDroite.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               checkTrue(exo.getProposition3(), exo.getReponse(), getApplicationContext(), exo);
                                           }
                                       }
        );
    }
    public void checkTrue(String test, String reponse, final Context context, final Exercice exo) {

        if (test.equals(reponse)) {
            //save true
            Sauvegarde.sauvegardeJuste(exo, context);

            // play right sound
            this.juste.start();
            // apparition du bouton suivant et création de l'intent
            next(exo);

        } else {
            // play false sound
            this.wrong2.start();
            // save false
            Sauvegarde.sauvegardeFaux(exo,context);
            next(exo);

        }
    }

    // close this and launch next activity
    public void next(Exercice exo) {

        //arralist of quizz exercices
        ArrayList<Exercice> listQuizz = new ArrayList<>();
        listQuizz = ListCategorie.redirect(exo, getApplicationContext());


            //create exercice to end quizz
            Exercice quizzEnder = new Exercice(Constante.QUIZZ, exo.getQuizz_categorie(), listQuizz.size(),"end", 1);
            listQuizz.add(quizzEnder);

        // if next is exercice
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
