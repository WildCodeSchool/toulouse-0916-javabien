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

public class ExoActivity extends MainActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo);
        Button boutonVrai=(Button)findViewById(R.id.boutonVrai); // Récupération de l'instance bouton 1
        boutonVrai.setOnClickListener((View.OnClickListener)this);

        Button boutonFaux=(Button)findViewById(R.id.boutonFaux); // Récupération de l'instance bouton 1
        boutonFaux.setOnClickListener((View.OnClickListener)this);

        Button boutonFaux2=(Button)findViewById(R.id.boutonFaux2); // Récupération de l'instance bouton 1
        boutonFaux2.setOnClickListener((View.OnClickListener)this);
    }

    // Méthode déclecnchée par le listener lorsqu'un appui sur le bouton se produit
    public void onClick(View view){
        if (view.getId()==R.id.boutonVrai){
            Toast.makeText(this,"Super", Toast.LENGTH_SHORT).show();}
        if (view.getId()==R.id.boutonFaux){
            Toast.makeText(this,"Mauvay", Toast.LENGTH_SHORT).show();}

        if (view.getId()==R.id.boutonFaux2){Toast.makeText(this,"Bouhou", Toast.LENGTH_SHORT).show();}
    }


}