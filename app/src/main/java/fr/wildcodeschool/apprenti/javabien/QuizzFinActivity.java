package fr.wildcodeschool.apprenti.javabien;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import java.util.ArrayList;
import fr.wildcodeschool.apprenti.javabien.Model.Exercice;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

public class QuizzFinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_fin);
        //mais que fait la police
        Typeface face= Typeface.createFromAsset(getAssets(), Constante.FONT_ALWYN);
        //facebook
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        AppEventsLogger.activateApp(this);

        // recupération de l'intent
        final Exercice exo =(Exercice)getIntent().getSerializableExtra(Constante.SERIALIZED_EXERCICE);

        // etablissement de l'arrayList du type de quizz passé
        ArrayList<Exercice> listQuizz = new ArrayList<>();

        listQuizz.addAll(ListCategorie.redirect(exo, this.getApplicationContext()));

        // comptage de bonnes réponses

        int result = 0;
        for (int i =0;i < listQuizz.size();i++){

            if(listQuizz.get(i).getAvancement() ==1)
                result++;
        }
        // sauvegarde pour informer la database du franchissement du quizz
        Exercice quizzPass = new Exercice(Constante.QUIZZ_VALIDE,listQuizz.get(0).getQuizz_categorie(),-1,null,0);
        Sauvegarde.sauvegardeExo(quizzPass,this.getApplicationContext());

        // facebook
        Button facebook = (Button) findViewById(R.id.fb_share_button);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            final ShareLinkContent content = new ShareLinkContent.Builder()
                    //lien vers l'icone dy playstore
                    .setImageUrl(Uri.parse(Constante.PLAYSTORE_IMG_URL))
                    .setContentDescription("j'apprends à coder grâce à #javabien, la nouvelle appli de la @WildCodeSchool." + " Niveau : " + exo.getQuizz_categorie() + " atteint ")
                    .setContentUrl(Uri.parse(Constante.PLAYSTORE_JAVABIEN_LINK))
                    .build();
            ShareDialog.show(QuizzFinActivity.this,content);
            }
        });


            //facebook.setShareContent(content);

        //;

        /**
         *  Dès que l'utilisateur clique sur notre ShareButton, cela
         *  lui affiche une vue qui lui permet de partager le lien
         */



        TextView bravo = (TextView)findViewById(R.id.bravo);
        bravo.setTypeface(face);
        bravo.setText("Bravo ! Tu as fini le quizz du niveau "+exo.getQuizz_categorie()+".\nTu as un score de "+
        result+"/"+listQuizz.size());

        //twitter

        final Button pioupiou = (Button)findViewById(R.id.twitter);
        pioupiou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //File demon = new File("http://static.s-sfr.fr/media/icone-google-play-store.jpg");
                Uri diable = Uri.parse(Constante.PLAYSTORE_IMG_URL);
                TweetComposer.Builder builder = new TweetComposer.Builder(QuizzFinActivity.this)
                        .text("j'apprends à coder grâce à #javabien, la nouvelle appli de la @WildCodeSchool." + " Niveau : " + exo.getQuizz_categorie()+" atteint ")
                        .image(diable);
                builder.show();
            }
        });



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
