package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class ExoQcmActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_qcm);
        //font alwyn
        Typeface face = Typeface.createFromAsset(getAssets(), "alwyn.ttf");
        //récupération de l'exo
        Intent intent = getIntent();
        final Contenant exo = (Contenant) intent.getSerializableExtra("amont");

        //mise en place du cours

        TextView info = (TextView) findViewById(R.id.info);
        info.setText(exo.getCours());
        info.setTypeface(face);

        //mise en place de la question

        TextView question = (TextView) findViewById(R.id.question);
        question.setText(exo.getQuestion());
        question.setTypeface(null, face.BOLD);
        // Récupération de l'instance bouton 1
        final Button boutonVrai = (Button) findViewById(R.id.bouton);
        // Récupération de l'instance bouton 2
        final Button boutonFaux = (Button) findViewById(R.id.bouton2);
        // Récupération de l'instance bouton 2
        final Button boutonFaux2 = (Button) findViewById(R.id.bouton3);

        // mise en place du texte du boutton 1
        boutonVrai.setText(exo.getPropositon());
        //taille du texte
        boutonVrai.setTextSize(12);
        // action au click
        boutonVrai.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //
                        testAndToast(exo.getPropositon(), exo.getReponse(), getApplicationContext(), exo, 1);
                    }
                }
        );


        boutonFaux.setText(exo.getProposition2()); //mise en place du texte du boutton 2
        boutonFaux.setTextSize(12);//couleur text bouton
        boutonFaux.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              testAndToast(exo.getProposition2(), exo.getReponse(), getApplicationContext(), exo, 2);
                                          }
                                      }
        );

        // Récupération de l'instance bouton 1
        boutonFaux2.setText(exo.getProposition3());// mise en place du texte du boutton 3
        boutonFaux2.setTextSize(12);//couleur text bouton
        boutonFaux2.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                               testAndToast(exo.getProposition3(), exo.getReponse(), getApplicationContext(), exo, 3);
                                           }
                                       }
        );
        // récupération réponse
        String reponse = exo.getReponse();

        // vérification de la réponse

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
            textstyle(exo);
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
            textstyle(exo);
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
    // affiche une info réponse dans un layout (receveur_dinfos) vide à la base
    public void messageperso() {
        //vidange avant de remplir au cas ou déjà utilisé
        LinearLayout receveur = (LinearLayout) findViewById(R.id.receveur_dinfos_qcm);
        receveur.removeAllViews();
        // insertion de layout dans un layout pour afficher l'info réponse
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_exo_insert_pop,(ViewGroup) findViewById(R.id.receveur_dinfos_qcm));
    }
    // applique la police et la couleur du texte
    public void textstyle(Contenant exo) {
        // texte du message complementaire
        TextView reponseInfo = (TextView) findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.reponseInfo);
        reponseInfo.setTextColor(Color.rgb(110, 110, 110));
        //font
        Typeface face = Typeface.createFromAsset(getAssets(), "alwyn.ttf");
        reponseInfo.setTypeface(face);
        reponseInfo.setTextSize(16);
    }
}