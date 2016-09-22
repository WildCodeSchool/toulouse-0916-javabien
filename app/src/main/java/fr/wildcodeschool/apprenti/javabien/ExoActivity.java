package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExoActivity extends SecondActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo);

        // texte du cours
        TextView info = (TextView)findViewById(R.id.info);
        info.setText("En Java vous allez manger du code, alors bon courage à vous !!");


        //récupération de la réponse
        final EditText reponse = (EditText)findViewById(R.id.reponse);



// vérification au click
        Button reponseValid = (Button)findViewById(R.id.boutonReponse);
        final String reponseExpected = "code";

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
                toastMessage.setTextColor(Color.WHITE);

                toastMessage.setGravity(Gravity.CENTER_VERTICAL);
                toastMessage.setCompoundDrawablePadding(16);
                toastView.setBackgroundColor(Color.RED);

                String reponseEntry = reponse.getText().toString();

                if(reponseEntry.equals(reponseExpected)) {
                    toastMessage.setBackgroundColor(Color.GREEN);
                    toast.setText("Tu es trop fort !");
                    toast.show();

                }else{
                    toastMessage.setBackgroundColor(Color.RED);
                    toast.setText("Gros Nul!");
                    toast.show();

                }
            }

        });
        }






    }

