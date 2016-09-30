package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

public class ExoActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo2);
        Intent intent = getIntent();

        final Contenant exo =  (Contenant)intent.getSerializableExtra("amont");

        // texte du cours
        TextView info = (TextView)findViewById(R.id.info);
        info.setText(exo.getCours());


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
        Button reponseValid = (Button)findViewById(R.id.boutonReponse);
        final String reponseExpected = exo.getPropositon();
        final String reponseExpected2 = exo.getProposition2();
        final String reponseExpected3 = exo.getProposition3();
        //vrai réponse
        final String vraiReponse = exo.getReponse();

        reponseValid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Gros Nul !", Toast.LENGTH_SHORT);
                View toastView = toast.getView(); //This'll return the default View of the Toast.

        /* And now you can get the TextView of the default View of the Toast. */
                TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
                toastMessage.setTextSize(25);
                toastMessage.setTextColor(Color.BLACK);

                toastMessage.setGravity(Gravity.CENTER);
                toastMessage.setCompoundDrawablePadding(16);
                toastView.setBackgroundColor(Color.RED);
                toastMessage.setBackgroundResource(R.drawable.deer);
                toast.setGravity(Gravity.CENTER|Gravity.CENTER,0,280);

                String reponseEntry = reponse.getText().toString();

                if(reponseEntry.equals(reponseExpected)) {
                    toastView.setBackgroundColor(Color.WHITE);
                    toastMessage.setBackgroundColor(Color.WHITE);
                    toast.setText("Looser :P");
                    toast.show();

                }

                else if (reponseEntry.equals(vraiReponse)) {
                    toastView.setBackgroundColor(Color.WHITE);
                    toastMessage.setBackgroundColor(Color.WHITE);
                    toast.setText("Tu es une véritable sex-machine !");
                    toast.show();

                }

                else if (reponseEntry.equals(reponseExpected3)) {
                toastView.setBackgroundColor(Color.WHITE);
                toastMessage.setBackgroundColor(Color.WHITE);
                toast.setText("T'es une pov merde...");
                toast.show();

            }
                else{
                    toastMessage.setBackgroundColor(Color.RED);
                    toast.setText("Gros Nul!");
                    toast.show();

                }
            }

        });
        }






    }

