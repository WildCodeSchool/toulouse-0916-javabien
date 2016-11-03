package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class ExoQcmActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boutonGauche.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        testAndToast(exo.getPropositon(), exo.getReponse(), getApplicationContext(), exo, 1);
                    }
                }
        );
        boutonCentre.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              testAndToast(exo.getProposition2(), exo.getReponse(), getApplicationContext(), exo, 2);
                                          }
                                      }
        );

        boutonDroite.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               testAndToast(exo.getProposition3(), exo.getReponse(), getApplicationContext(), exo, 3);
                                           }
                                       }
        );
    }
    // Méthode déclenchée par le listener lorsqu'on appuie sur le bouton
    public void testAndToast(String test, String reponse, final Context context, final Contenant exo, int idButton) {

        // si la valeur testée est égale à la bonne réponse
        if (test.equals(reponse)) {
            //sauvegarde
            Sauvegarde.sauvegardeExo(exo, context);

            // lancement du son juste
            MediaPlayer juste = MediaPlayer.create(getApplicationContext(), R.raw.vrai);
            juste.start();
            //affichage du message de confirmation
            messageperso();
            // applique la police et la couleur du texte
            textstyle();
            // message de congratulations reponseInfo correspond à la valeur juste dans la bdd
            TextView message = (TextView) findViewById(R.id.layout_message).findViewById(R.id.reponseInfo);
            message.setText(exo.getInfo_reponse());

            // masquage des boutons
            RelativeLayout buttons = (RelativeLayout) findViewById(R.id.relox);
            buttons.removeAllViews();

            //config du bouton "suivant"
            Button suivant = (Button) findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.suivant);
            suivant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent adios = new Intent();
                    adios.putExtra("listExercices", ListCategorie.redirect(exo, getApplicationContext()));
                    setResult(1, adios);
                    finish();
                }

            });
          // si la valeur testée n'est pas égale à la bonne réponse
        } else {
            // lancement du son faux
            MediaPlayer wrong = MediaPlayer.create(getApplicationContext(), R.raw.faux);
            wrong.start();
            //affichage du message de confirmation
            messageperso();
            // applique la police et la couleur du texte
            textstyle();
            //affichage du texte en fonction du bouton
            // assignation de l'info texte correspondant
            // exo.getInforeponse2 correspond au premier bouton faux (en lisant de gauche à droite)
            //exo.getInforeponse3 correspond au deuxième bouton faux (en lisant de gauche à droite)

            // on détermine le premier bouton qui sera une fausse reponse (de gauche à droite)
            int pasBon1 = 0;
            // recherche du bouton qui est la bonne réponse et entre dans pasBon1 l'id du premier bouton faux
            if (exo.getPropositon().equals(exo.getReponse())) {
                pasBon1 = 2;
            }
            if (exo.getProposition2().equals(exo.getReponse())) {
                pasBon1 = 1;
            }
            if (exo.getProposition3().equals(exo.getReponse())) {
                pasBon1 = 1;
            }
            // on affiche l'info réponse en fonction de la valeur du bouton cliqué
            final TextView message = (TextView) findViewById(R.id.layout_message).findViewById(R.id.reponseInfo);
            // si le bouton 1 est faux
            if (idButton == 1)
                // l'info reponse affiché sera l'Info_reponse2
                message.setText(exo.getInfo_reponse2());
            //sinon si l'id du bouton est de 2 et que le premier bouton aussi est faux
            else if (idButton == 2 && pasBon1 == 1)
                // l'info reponse affiché sera l'Info_reponse3
                message.setText(exo.getInfo_reponse3());
            //sinon si l'id du bouton est de 2 et que le deuxième bouton aussi est faux
            else if (idButton == 2 && pasBon1 == 2)
                // l'info reponse affiché sera l'Info_reponse2
                message.setText(exo.getInfo_reponse2());
            //sinon si l'id du bouton est de 3
            else if (idButton == 3)
                // l'info reponse affiché sera l'Info_reponse3
                message.setText(exo.getInfo_reponse3());
            //masquage du bouton suivant
            Button suivant = (Button) findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.suivant);
            suivant.setVisibility(View.INVISIBLE);
        }

    }

}