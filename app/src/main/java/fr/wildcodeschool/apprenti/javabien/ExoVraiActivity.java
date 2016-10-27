package fr.wildcodeschool.apprenti.javabien;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class ExoVraiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_vrai);
        //mais que fait la police
        Typeface face= Typeface.createFromAsset(getAssets(), "alwyn.ttf");
        //récupération de l'objet
        Intent intent = getIntent();
        final Contenant exo = (Contenant)intent.getSerializableExtra("amont");

        //mise en place du cours

        TextView info = (TextView)findViewById(R.id.info);
        info.setText(exo.getCours());

        //mise en place de la question

        TextView question = (TextView)findViewById(R.id.question);
        question.setText(exo.getQuestion());
        question.setTypeface(null,face.BOLD);
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

                        clickPop(1,exo);
                    }
                }
        );


        boutonFaux.setText(exo.getProposition2()); //mise en place du texte du boutton 2
        boutonFaux.setTextSize(12);//couleur text bouton
        boutonFaux.setOnClickListener( new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                               clickPop(2,exo);
                                           }
                                       }
        );

        // Récupération de l'instance bouton 1
        boutonFaux2.setText(exo.getProposition3());// mise en place du texte du boutton 3
        boutonFaux2.setTextSize(12);//couleur text bouton
        boutonFaux2.setOnClickListener( new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                clickPop(3,exo);
                                            }
                                        }
        );
        // récupération réponse
        String reponse = exo.getReponse();

        // vérification de la réponse

    }
    public void clickPop(int id, final Contenant exo){
Sauvegarde.sauvegardeExo(exo,getApplicationContext());
        // texte du message complementaire
        if(id==1){
            messageperso();
            textstyle();
            TextView reponseInfo = (TextView)findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.reponseInfo);

            reponseInfo.setText(exo.getInfo_reponse());
        }
        else if (id==2){
            messageperso();
            textstyle();
            TextView reponseInfo = (TextView)findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.reponseInfo);

            reponseInfo.setText(exo.getInfo_reponse2());
        }
        else if (id==3){
            messageperso();
            textstyle();
            TextView reponseInfo = (TextView)findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.reponseInfo);

            reponseInfo.setText(exo.getInfo_reponse3());
        }
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
    public void textstyle(){

        // texte du message complementaire
        TextView reponseInfo = (TextView)findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.reponseInfo);

        reponseInfo.setTextColor(Color.rgb(110, 110, 110));
        //font
        Typeface face= Typeface.createFromAsset(getAssets(), "alwyn.ttf");
        reponseInfo.setTypeface(face);
        reponseInfo.setTextSize(16);
    }


}
