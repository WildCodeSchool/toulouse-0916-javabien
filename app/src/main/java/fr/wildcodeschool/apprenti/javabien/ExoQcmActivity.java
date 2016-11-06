package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import fr.wildcodeschool.apprenti.javabien.Model.Exercice;

public class ExoQcmActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // left button
        this.boutonGauche.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        verifyOnClick(exo.getPropositon(), exo.getReponse(), getApplicationContext(), exo, 1);
                    }
                }
        );
        // midle button
        this.boutonCentre.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              verifyOnClick(exo.getProposition2(), exo.getReponse(), getApplicationContext(), exo, 2);
                                          }
                                      }
        );
        //right button
        this.boutonDroite.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               verifyOnClick(exo.getProposition3(), exo.getReponse(), getApplicationContext(), exo, 3);
                                           }
                                       }
        );
    }

    public void verifyOnClick(String test, String reponse, final Context context, final Exercice exo, int idButton) {


        if (test.equals(reponse)) {
            //Save progress
            Sauvegarde.sauvegardeExo(exo, context);

            // playing true sound
            this.juste.start();
            // show information
            this.setInfoMessage();
            // with exercice custom text
            this.reponseInfo.setText(exo.getInfo_reponse());

            // hiding buttons
            RelativeLayout buttons = (RelativeLayout) findViewById(R.id.relox);
            buttons.removeAllViews();

            //next button
            this.suivant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent adios = new Intent();
                    adios.putExtra(Constante.SERIALIZED_LIST, ListCategorie.redirect(exo, getApplicationContext()));
                    setResult(1, adios);
                    finish();
                }

            });
        } else {
            // playing false sound
            this.wrong1.start();
            //show information
            setInfoMessage();
            //setting exercice text information depending of button id
            // exo.getInforeponse2 is the first false answer message (reading from left to right)
            //exo.getInforeponse3 is the second false anwser(reading from left to right)

            // variable to put id of first false button
            int pasBon1 = 0;
            //checking the first false button (from left to right)
            if (exo.getPropositon().equals(exo.getReponse())) {
                pasBon1 = 2;
            }else {
                pasBon1 = 1;
            }
            // show appropriate information depending of button id
            if (idButton == 1)
                // show exercice info_reponse2
                reponseInfo.setText(exo.getInfo_reponse2());
            else if (idButton == 2 && pasBon1 == 1)
                // show exercice info_reponse3
                reponseInfo.setText(exo.getInfo_reponse3());
            else if (idButton == 2 && pasBon1 == 2)
                // show exercice info_reponse2
                reponseInfo.setText(exo.getInfo_reponse2());
            else if (idButton == 3)
                // show exercice info_reponse3
                reponseInfo.setText(exo.getInfo_reponse3());
            //hiding next button
            suivant.setVisibility(View.INVISIBLE);
        }

    }

}