package fr.wildcodeschool.apprenti.javabien;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

public class Accueil extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        /* Timer pour chargement de page*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent pass = (new Intent(Accueil.this,MainActivity.class));
                startActivity(pass);
            }
        }, 10000);

        /* Listener sur le Layout */
        LinearLayout jac = (LinearLayout) findViewById(R.id.javaccueil);
        jac.setOnClickListener(new View.OnClickListener() {

            @Override /* Intent pour passer à la page suivante en clickant*/
            public void onClick(View v) {
                Intent intent = new Intent(Accueil.this, MainActivity.class);
                startActivity(intent);
            }

        });

    }}
