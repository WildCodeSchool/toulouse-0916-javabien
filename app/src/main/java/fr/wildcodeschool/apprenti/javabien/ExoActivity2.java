package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExoActivity2 extends SecondActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo2);

        // texte du cours
        TextView info = (TextView)findViewById(R.id.info);
        info.setText("Les nombres peuvent avoir plusieurs types :\n" +
                "Pour les nombres entiers on utilise integers (entiers en anglais), pour les nombres avec une décimale on utilise double. Le type des nombres dépends de l’information que l’on souhaite utiliser.\n" +
                "\n" +
                "1 int monEntier = Réponse\n" +
                "2 double monDouble = 2.0;\n");


        //récupération de la réponse
        final EditText reponse = (EditText)findViewById(R.id.reponse);



// vérification au click
        Button reponseValid = (Button)findViewById(R.id.boutonReponse);
        final String reponseExpected = '"'+"4"+'"';
        final String reponseExpected2 = "4";
        final String reponseExpected3 = "4.0";

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
                toast.setGravity(Gravity.CENTER|Gravity.CENTER,0,280);

                String reponseEntry = reponse.getText().toString();

                if(reponseEntry.equals(reponseExpected)) {
                    toastView.setBackgroundColor(Color.WHITE);
                    toastMessage.setBackgroundColor(Color.WHITE);
                    toast.setText("Looser :P");
                    toast.show();

                }

                else if (reponseEntry.equals(reponseExpected2)) {
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

