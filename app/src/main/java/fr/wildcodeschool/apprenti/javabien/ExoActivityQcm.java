package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

public class ExoActivityQcm extends Activity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_qcm);

        //récupération de l'objet
        Intent intent = getIntent();
        final Contenant exo = (Contenant)intent.getSerializableExtra("amont");

        //mise en place du cours

        TextView info = (TextView)findViewById(R.id.info);
        info.setText(exo.getCours());

        //mise en place de la question

        TextView question = (TextView)findViewById(R.id.question);
        question.setText(exo.getQuestion());
        final Button boutonVrai=(Button)findViewById(R.id.bouton); // Récupération de l'instance bouton 1
        final Button boutonFaux=(Button)findViewById(R.id.bouton2); // Récupération de l'instance bouton 2
        final Button boutonFaux2=(Button)findViewById(R.id.bouton3);// Récupération de l'instance bouton 2



        boutonVrai.setText(exo.getPropositon());// mise en place du texte du boutton 1
        boutonVrai.setTextSize(12);//taille
        boutonVrai.setTextIsSelectable(false);
        boutonVrai.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        testAndToast(exo.getPropositon(),exo.getReponse(),getApplicationContext(),exo,1,boutonVrai,boutonFaux,boutonFaux2);
                    }
                }
        );


        boutonFaux.setText(exo.getProposition2()); //mise en place du texte du boutton 2
        boutonFaux.setTextSize(12);//couleur text bouton
        boutonFaux.setOnClickListener( new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                               testAndToast(exo.getProposition2(),exo.getReponse(),getApplicationContext(),exo,2,boutonVrai,boutonFaux,boutonFaux2);
                                           }
                                       }
        );

         // Récupération de l'instance bouton 1
        boutonFaux2.setText(exo.getProposition3());// mise en place du texte du boutton 3
        boutonFaux2.setTextSize(12);//couleur text bouton
        boutonFaux2.setOnClickListener( new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                testAndToast(exo.getProposition3(),exo.getReponse(),getApplicationContext(),exo,3,boutonVrai,boutonFaux,boutonFaux2);
                                            }
                                        }
        );
        // récupération réponse
        String reponse = exo.getReponse();

        // vérification de la réponse

    }

    // Méthode déclecnchée par le listener lorsqu'on appui sur le bouton se produit
    public void testAndToast(String test, String reponse, final Context context, final Contenant exo,int idButton, Button un, Button deux, Button trois){








        if (test.equals(reponse)){
            /*Toast.makeText(this,"Super", Toast.LENGTH_SHORT).show();*/
            // lancement du son juste

            //sauvegarde
            Sauvegarde.sauvegardeExo(exo,context);

            // lancement du son juste
            MediaPlayer juste = MediaPlayer.create(getApplicationContext(),R.raw.vrai);
            juste.start();
            //affichage du message de confirmation
            messageperso();
            // application du style de texte
            textstyle(exo);
            //congratulations
            TextView message = (TextView)findViewById(R.id.layout_message).findViewById(R.id.reponseInfo);
            message.setText(exo.getInfo_reponse());

            // planquage des boutons
            RelativeLayout buttons = (RelativeLayout)findViewById(R.id.relox);
            buttons.removeAllViews();

            //config du bouton suivant
            Button suivant =(Button)findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.suivant);
            suivant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent adios = new Intent();
                    adios.putExtra("listExercices",ListCategorie.redirect(exo,exo.getId_exos(),getApplicationContext()));
                    setResult(1,adios);

                    finish();

                }

            });

        }else {
            /*Toast.makeText(this,"Faux", Toast.LENGTH_SHORT).show();*/
            // lancement du son faux
            MediaPlayer wrong = MediaPlayer.create(getApplicationContext(),R.raw.faux);
            wrong.start();
            //affichage du message de confirmation
            messageperso();
            // application du style de texte
            textstyle(exo);
            //affichage du texte en fonction du bouton
            // assignation du bon info text
            String vrai = null;
            String faux = null;
            String faux2 =null;
            int pasBon1 = 0;
            int pasBon2 = 0;
            if(exo.getPropositon().equals(exo.getReponse())) {
                pasBon1 = 2;
                pasBon2 = 3;
            }
            if(exo.getProposition2().equals(exo.getReponse())) {
                pasBon1 = 1;
                pasBon2 = 3;
            }
            if(exo.getProposition3().equals(exo.getReponse())) {
                pasBon1 = 1;
                pasBon2 = 2;
            }
            final TextView message = (TextView)findViewById(R.id.layout_message).findViewById(R.id.reponseInfo);
            if(idButton==1)
            message.setText(exo.getInfo_reponse2());
            else if (idButton==2 && pasBon1==1)
                message.setText(exo.getInfo_reponse3());
            else if (idButton==2 && pasBon1==2)
                message.setText(exo.getInfo_reponse2());
            else if (idButton==3)
                message.setText(exo.getInfo_reponse3());
            //planquage du bouton reessayer
             Button reessayer =(Button)findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.suivant);
            reessayer.setVisibility(View.INVISIBLE);
        }

    }
    public void messageperso(){
        //vidange avant de remplir
        LinearLayout receveur = (LinearLayout)findViewById(R.id.receveur_dinfos_qcm);
        receveur.removeAllViews();
        // insertion de layout dans un layout
        LinearLayout bouse = (LinearLayout)findViewById(R.id.layout_message);
        LayoutInflater inflater = (LayoutInflater)      getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View childLayout = inflater.inflate(R.layout.activity_exo_insert_pop,
                (ViewGroup) findViewById(R.id.receveur_dinfos_qcm));


    }
    public void textstyle(Contenant exo){

        // texte du message complementaire
        TextView reponseInfo = (TextView)findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.reponseInfo);
        reponseInfo.setText(exo.getCours());
        reponseInfo.setTextColor(Color.rgb(110, 110, 110));
        //font
        Typeface face= Typeface.createFromAsset(getAssets(), "alwyn.ttf");
        reponseInfo.setTypeface(face);
        reponseInfo.setTextSize(16);
    }


}