package fr.wildcodeschool.apprenti.javabien;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by apprenti on 24/10/16.
 */

public class InfoPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_page);

        //bouton facebook
        Button bouc = (Button)findViewById(R.id.bouc);
        bouc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://fr-fr.facebook.com/wildcodeschool"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        //bouton instagram
        Button kilo = (Button)findViewById(R.id.gram);
        kilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uri poids = Uri.parse("https://www.instagram.com/wildcodeschool/"); // missing 'http://' will cause crashed
                Intent balance = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/wildcodeschool/"));
                startActivity(balance);

            }
        });
        //bouton sur twitter
        Button pioupiou = (Button)findViewById(R.id.piou);
        pioupiou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri moineau = Uri.parse("https://twitter.com/wildcodeschool?lang=fr");
                Intent merle = new Intent(Intent.ACTION_VIEW, moineau);
                startActivity(merle);

            }
        });
        //bouton linkedin
        Button lalala = (Button)findViewById(R.id.link);
        lalala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri tralala = Uri.parse("https://fr.linkedin.com/edu/wild-code-school-192048"); // missing 'http://' will cause crashed
                Intent lalaire = new Intent(Intent.ACTION_VIEW, tralala);
                startActivity(lalaire);

            }
        });
        //bouton retour
        Button boutinfo = (Button) findViewById(R.id.boutonretour);
        boutinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intentinfo = new Intent(InfoPage.this, MainActivity.class);
                startActivity(intentinfo);

            }

        });

    }
}
