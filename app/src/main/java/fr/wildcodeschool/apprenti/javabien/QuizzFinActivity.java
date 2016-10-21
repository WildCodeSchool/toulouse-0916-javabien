package fr.wildcodeschool.apprenti.javabien;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;

import java.util.ArrayList;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

import static fr.wildcodeschool.apprenti.javabien.R.id.image;

public class QuizzFinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_fin);

        MediaPlayer applause = MediaPlayer.create(getApplicationContext(),R.raw.applause_fake);
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
        ShareButton shareButton = (ShareButton) findViewById(R.id.fb_share_button);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.iconej);


        /**
         *  Dès que l'utilisateur clique sur notre ShareButton, cela
         *  lui affiche une vue qui lui permet de partager le lien
         *  qu'on a mis <img draggable="false" class="emoji" alt="🙂" src="https://s.w.org/images/core/emoji/2/svg/1f642.svg">
         */
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("http://www.wildcodeschool.fr/"))
                .build();

        shareButton.setShareContent(content);


       /* TextView bravo = (TextView)findViewById(R.id.bravo);
        bravo.setText("Bravo tu as fini le quizz "+exo.getQuizz_categorie()+" tu as un résultat de "+
        result+"/"+listQuizz.size());*/


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
