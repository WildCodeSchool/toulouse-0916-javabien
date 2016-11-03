package fr.wildcodeschool.apprenti.javabien;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

/**
 *
 */

public abstract class BaseActivity extends AppCompatActivity {


    protected Typeface face;
    protected Contenant exo;
    protected Button boutonGauche;
    protected Button boutonCentre;
    protected Button boutonDroite;
    protected TextView question;
    protected LinearLayout receveur;
    protected LayoutInflater inflater;
    protected TextView reponseInfo;
    protected TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo);


        // load custom font
        this.face = Typeface.createFromAsset(getAssets(), "alwyn.ttf");
        // get exercice from listExoActivity
        this.exo = (Contenant) getIntent().getSerializableExtra("amont");

        //mise en place de la question s'il y en a une
        this.question = (TextView)findViewById(R.id.question);
        this.question.setText(exo.getQuestion());
        this.question.setTypeface(null,face.BOLD);
        //mise en place du cours
        this.info = (TextView)findViewById(R.id.info);
        this.info.setText(exo.getCours());
        this.info.setTypeface(face);

        this.boutonGauche =(Button)findViewById(R.id.bouton); // Récupération de l'instance bouton 1
        // mise en place du texte du boutton 1
        this.boutonGauche.setText(exo.getPropositon());
        //taille
        this.boutonGauche.setTextSize(10);

        this.boutonCentre =(Button)findViewById(R.id.bouton2); // Récupération de l'instance bouton 2
        this.boutonCentre.setText(exo.getProposition2()); //mise en place du texte du boutton 2
        this.boutonCentre.setTextSize(10);//couleur text bouton

        this.boutonDroite =(Button)findViewById(R.id.bouton3);// Récupération de l'instance bouton 2
        this.boutonDroite.setText(exo.getProposition3());// mise en place du texte du boutton 3
        this.boutonDroite.setTextSize(10);//couleur text bouton
    }
    public void messageperso(){
        //vidange avant de remplir
        this.receveur = (LinearLayout)findViewById(R.id.receveur_dinfos_qcm);
        this.receveur.removeAllViews();
        // insertion de layout dans un layout
        this.inflater = (LayoutInflater)      getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        this.inflater.inflate(R.layout.activity_exo_insert_pop,
                (ViewGroup) findViewById(R.id.receveur_dinfos_qcm));
    }
    public void textstyle(){
        // texte du message complementaire
        this.reponseInfo = (TextView)findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.reponseInfo);
        this.reponseInfo.setTextColor(Color.rgb(110, 110, 110));
        //font
        this.reponseInfo.setTypeface(this.face);
        this.reponseInfo.setTextSize(16);
    }

}
