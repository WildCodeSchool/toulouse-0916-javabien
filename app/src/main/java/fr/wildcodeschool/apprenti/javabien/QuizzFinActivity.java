package fr.wildcodeschool.apprenti.javabien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class QuizzFinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_fin);

        // recupération de l'intent
        Intent recup =getIntent();
        Contenant exo =(Contenant)recup.getSerializableExtra("amont");

        // etablissement de l'arrayList du type de quizz passé
        ArrayList<Contenant> listQuizz = new ArrayList<Contenant>();

        listQuizz.addAll(ListCategorie.redirect(exo,0,getApplicationContext()));

        // comptage de bonnes réponses

        int result = 0;
        for (int i =0;i < listQuizz.size();i++){

            if(listQuizz.get(i).getAvancement() ==1)
                result++;
        }

        // remplissage du texte

        TextView bravo = (TextView)findViewById(R.id.bravo);
        bravo.setText("Bravo tu as fini le quizz "+exo.getQuizz_categorie()+" tu as un résultat de "+
        result+"/"+listQuizz.size());


        //renvoi à la page d'accueil

        Button retour = (Button)findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizzFinActivity.this,MainActivity.class);

                finish();
                startActivity(intent);
            }
        });
    }




}
