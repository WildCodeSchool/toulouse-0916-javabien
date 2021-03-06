package fr.wildcodeschool.apprenti.javabien;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);

        //
        TextView texteView = (TextView) findViewById(R.id.balbla);
        Typeface face = Typeface.createFromAsset(getAssets(), Constante.FONT_ALWYN);
        texteView.setTypeface(face);

        //button facebook
        Button bouc = (Button) findViewById(R.id.bouc);
        bouc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(getFacebookIntent(Constante.URL_FACEBOOK_WCS));
            }


        });
        //button instagram
        Button kilo = (Button) findViewById(R.id.gram);
        kilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent balance = new Intent(Intent.ACTION_VIEW, Uri.parse(Constante.URL_INSTAGRAM));
                startActivity(balance);

            }
        });
        //button sur twitter
        Button pioupiou = (Button) findViewById(R.id.piou);
        pioupiou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri moineau = Uri.parse(Constante.URL_TWTTER_WCS);
                Intent merle = new Intent(Intent.ACTION_VIEW, moineau);
                startActivity(merle);

            }
        });
        //button linkedin
        Button lalala = (Button) findViewById(R.id.link);
        lalala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri tralala = Uri.parse(Constante.URL_LINKEDIN);
                Intent lalaire = new Intent(Intent.ACTION_VIEW, tralala);
                startActivity(lalaire);

            }
        });
        //button retour
        Button boutinfo = (Button) findViewById(R.id.boutonretour);
        boutinfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }

        });

    }
    public Intent getFacebookIntent(String url) {

        PackageManager pm = getApplicationContext().getPackageManager();
        Uri uri = Uri.parse(url);

        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + "https://fr-fr.facebook.com/wildcodeschool");
            }
        }

        catch (PackageManager.NameNotFoundException ignored) {
        }

        return new Intent(Intent.ACTION_VIEW, uri);
    }
}
