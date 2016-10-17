package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

import static android.graphics.Color.rgb;

public class QuizzInsertActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_insert);

        final Intent intent = getIntent();
        Button suivant = (Button) findViewById(R.id.suivant);

        // recupération de l'exercice
        final Contenant exo = (Contenant) intent.getSerializableExtra("amont");

        // texte du cours
        TextView info = (TextView) findViewById(R.id.info);
        info.setText(exo.getCours());


        //récupération de la réponse
        final EditText reponse = (EditText) findViewById(R.id.reponse);


        //affichage de la question :
        TextView question = (TextView) findViewById(R.id.question);
        question.setText(exo.getQuestion());
        //taille max de l'editText basée sur la taille de la réponse attendue+5
        if (exo.getReponse() != null) {
            int maxLength = exo.getReponse().length() + 5;
            reponse.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});

        }


        // vérification au click
        Button reponseValid = (Button) findViewById(R.id.boutonReponse);
        //textbouton color
        reponseValid.setTextColor(Color.WHITE);

        final String reponseExpected = exo.getPropositon();
        final String reponseExpected2 = exo.getProposition2();
        final String reponseExpected3 = exo.getProposition3();
        //vrai réponse
        final String vraiReponse = exo.getReponse();

        reponseValid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Recommence !", Toast.LENGTH_SHORT);
                View toastView = toast.getView(); //This'll return the default View of the Toast.

        /* And now you can get the TextView of the default View of the Toast. */
             /*   TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
                toastMessage.setTextSize(18);
                toastMessage.setTextColor(Color.DKGRAY);

                toastMessage.setGravity(Gravity.CENTER);
                toastMessage.setCompoundDrawablePadding(5);
                toastView.setBackgroundColor(Color.TRANSPARENT);
                //toastMessage.setBackgroundResource(R.drawable.deer);
                toast.setGravity(Gravity.CENTER|Gravity.CENTER,0,280);*/
                //Toast.makeText(context, "Recommence", Toast.LENGTH_SHORT).show();

                String reponseEntry = reponse.getText().toString();

                if (reponseEntry.equals(reponseExpected)) {
                    //toastView.setBackgroundColor(Color.WHITE);
                    // toastMessage.setBackgroundColor(Color.rgb(255, 222, 165));
                    toast.setText("Essaie encore");
                    //toastMessage.setPadding(2,2,2,2);
                    toast.show();
                    //lancement du son faux
                    MediaPlayer wrong = MediaPlayer.create(getApplicationContext(), R.raw.faux);
                    wrong.start();
                } else if (reponseEntry.equals(vraiReponse)) {
                    //toastView.setBackgroundColor(Color.rgb(255, 222, 165));

                    //toastMessage.setBackgroundColor(Color.rgb(255, 222, 165));
                    toast.setText("Super!!\nAu suivant!");
                    //toastMessage.setPadding(2,2,2,2);
                    toast.show();
                    //lancement du son juste
                    MediaPlayer vrai = MediaPlayer.create(getApplicationContext(), R.raw.vrai);
                    vrai.start();

                    //affichage du bouton suivant
                    Button suivant = (Button) findViewById(R.id.suivant);
                    suivant.setVisibility(View.VISIBLE);
                    // sauvegarde de l'avancement dans la base de donnée à améliorer pour le quizz

                    //Sauvegarde.sauvegardeExo(exo, context);

                } else if (reponseEntry.equals(reponseExpected3)) {
                    //toastView.setBackgroundColor(Color.WHITE);

                    //toastMessage.setBackgroundColor(Color.rgb(255, 222, 165));
                    toast.setText("Tu vas y\narriver");
                    // toastMessage.setPadding(2,2,2,2);
                    toast.show();
                    //lancement du son faux
                    MediaPlayer clap = MediaPlayer.create(getApplicationContext(), R.raw.faux);
                    clap.start();
                    // renvoi à l'exo suivant
                    exo_Suivant(exo);
                    // sauvegarde de l'avancement dans la base de donnée à améliorer pour le quizz

                    //Sauvegarde.sauvegardeExo(exo, context);

                } else {
                    //  toastMessage.setBackgroundColor(Color.rgb(255, 222, 165));
                    toast.setText("Recommence!!!");
                    toast.show();
                    //lancement du son faux
                    MediaPlayer wrong = MediaPlayer.create(getApplicationContext(), R.raw.faux);
                    wrong.start();
                    // sauvegarde faux
                    Sauvegarde.sauvegardeFaux(exo,getApplicationContext());
                    // renvoi à l'exo suivant (cf methode)
                    exo_Suivant(exo);

                }
            }

        });



    }

    // methode de redirection et de lancement de l'exercice suivant
    public void exo_Suivant(Contenant exo) {

        //recupération de la liste des exos du quizz dans listQuizz
        ArrayList<Contenant> listQuizz = new ArrayList<Contenant>();
        listQuizz = ListCategorie.redirect(exo, 0, getApplicationContext());
        //création d'un exo moisi pour la fin du quizz
        Contenant moisi = new Contenant("quizz",exo.getQuizz_categorie(),150,"","", "",
                "", "", "","", "",1);
        // ajout du contenant moisi pour la fin
        listQuizz.add(moisi);

        // si l'exo suivant de la liste egal qcm

        if (listQuizz.get(exo.getId_exos() + 1).getExoType().equals("qcm")) {
            Intent intent = new Intent(QuizzInsertActivity.this, QuizzQcmActivity.class);
            intent.putExtra("amont", listQuizz.get(exo.getId_exos() + 1));
            startActivity(intent);
            // si l'exo suivant de la liste egal insert
        } else if (listQuizz.get(exo.getId_exos() + 1).getExoType().equals("insert")) {
            Intent intent = new Intent(QuizzInsertActivity.this, QuizzInsertActivity.class);
            intent.putExtra("amont", listQuizz.get(exo.getId_exos() + 1));
            finish();
            startActivity(intent);
            // sinon lancer la mainActivity
        } else {
            Intent intent = new Intent(QuizzInsertActivity.this, QuizzFinActivity.class);
            intent.putExtra("amont",exo);
            finish();
            startActivity(intent);
        }


    }
}
