package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

import static android.graphics.Color.rgb;

public class ExoActivityInsert extends Activity {

private View.OnClickListener actionClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_insert);





        final Intent intent = getIntent();
        Button suivant =(Button)findViewById(R.id.suivant);
        final Contenant exo =  (Contenant)intent.getSerializableExtra("amont");

        // texte du cours
        TextView info = (TextView)findViewById(R.id.info);
        info.setText(exo.getCours());
        //font
        Typeface face= Typeface.createFromAsset(getAssets(), "alwyn.ttf");
        info.setTypeface(face);
        info.setTextSize(20);

        //récupération de la réponse
        final EditText reponse = (EditText)findViewById(R.id.reponse);


        //affichage de la question :
        TextView question = (TextView)findViewById(R.id.question);
        question.setText(exo.getQuestion());
        //taille max de l'editText basée sur la taille de la réponse attendue+5
       if(exo.getReponse()!=null) {
           int maxLength = exo.getReponse().length() + 5;
           reponse.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});

       }



            // vérification au click
        final Button reponseValid = (Button)findViewById(R.id.boutonReponse);
        //textbouton color
        reponseValid.setTextColor(Color.WHITE);

        final String reponseExpected = exo.getPropositon();
        final String reponseExpected2 = exo.getProposition2();
        final String reponseExpected3 = exo.getProposition3();
        //vrai réponse
        final String vraiReponse = exo.getReponse();
        //configuration des tâches à effectuer si clickée ou entrée tapée
        actionClick =new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
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

                if(reponseEntry.equals(reponseExpected)) {
                    //toastView.setBackgroundColor(Color.WHITE);
                   // toastMessage.setBackgroundColor(Color.rgb(255, 222, 165));
                    toast.setText("Essaie encore");
                    //toastMessage.setPadding(2,2,2,2);
                    toast.show();
                    //lancement du son faux
                    MediaPlayer wrong = MediaPlayer.create(getApplicationContext(),R.raw.faux);
                    wrong.start();
                }

                else if (reponseEntry.equals(vraiReponse)) {
                    //toastView.setBackgroundColor(Color.rgb(255, 222, 165));

                    //toastMessage.setBackgroundColor(Color.rgb(255, 222, 165));
                    toast.setText("Super!!\nAu suivant!");
                    //toastMessage.setPadding(2,2,2,2);
                    toast.show();
                    //lancement du son juste
                    MediaPlayer vrai = MediaPlayer.create(getApplicationContext(),R.raw.vrai);
                    vrai.start();

                    //affichage du bouton suivant
                    Button suivant =(Button)findViewById(R.id.suivant);
                    suivant.setVisibility(View.VISIBLE);
                    //suivant.setBackgroundColor(rgb(128, 203, 196));
                    // sauvegarde de l'avancement dans la base de donnée

                Sauvegarde.sauvegardeExo(exo,context);

                }

                else if (reponseEntry.equals(reponseExpected3)) {
                    //toastView.setBackgroundColor(Color.WHITE);

                    //toastMessage.setBackgroundColor(Color.rgb(255, 222, 165));
                    toast.setText("Tu vas y\narriver");
                   // toastMessage.setPadding(2,2,2,2);
                    toast.show();
                    //lancement du son faux
                    MediaPlayer clap = MediaPlayer.create(getApplicationContext(),R.raw.faux);
                    clap.start();

            }
                else{
                  //  toastMessage.setBackgroundColor(Color.rgb(255, 222, 165));
                    toast.setText("Recommence!!!");
                    toast.show();
                    //lancement du son faux
                    MediaPlayer wrong = MediaPlayer.create(getApplicationContext(),R.raw.faux);
                    wrong.start();

                }
            }

        };
        // si clické
        reponseValid.setOnClickListener(actionClick);


        // recupération de l'action entrée et action du click

        reponse.setFocusableInTouchMode(true);
        reponse.requestFocus();
        reponse.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //planquage du clavier
                    InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.hideSoftInputFromWindow(reponse.getWindowToken(), 0);
                    // clickage
                    reponseValid.performClick();
                    return true;
                }

                return false;
            }
        });



        //action du bouton suivant
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



    }

