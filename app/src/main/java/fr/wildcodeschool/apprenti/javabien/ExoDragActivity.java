package fr.wildcodeschool.apprenti.javabien;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jmedeisis.draglinearlayout.DragLinearLayout;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class ExoDragActivity extends AppCompatActivity {
    // array qui reçoit l'ordre de correction
    private int[] correction = new int[4];
    private Contenant exo;
    private Typeface face;
    // propositions (lignes du drag & drop)
    private Proposition[] ennonce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_drag);
        // load custom font
        this.face = Typeface.createFromAsset(getAssets(), "alwyn.ttf");
        // get exercice from listExoActivity
        this.exo = (Contenant) getIntent().getSerializableExtra("serialized exercice");

        // entrée lignes à partir de l'exo dans le drag
        ennonce = new Proposition[]{
                new Proposition(0, exo.getPropositon()),
                new Proposition(1, exo.getProposition2()),
                new Proposition(2, exo.getProposition3()),
                new Proposition(3, exo.getProposition4())
        };
        this.exo = (Contenant) getIntent().getSerializableExtra("serialized exercice");
        // recupération du texte du cours dans l'exo
        TextView cours = (TextView) findViewById(R.id.cours);
        cours.setText(exo.getCours());
        //   convertion du string reponse de l'exo en array d'int
        String toconvert = exo.getReponse();
        String[] splited = toconvert.split(" ");

        for (int j = 0; j < splited.length; j++) {
            correction[j] = Integer.valueOf(splited[j]);
        }

        // affichage des lignes des propositions
        TextView v1 = (TextView) findViewById(R.id.drag1);
        TextView v2 = (TextView) findViewById(R.id.drag2);
        TextView v3 = (TextView) findViewById(R.id.drag3);
        TextView v4 = (TextView) findViewById(R.id.drag4);
        v1.setText(ennonce[0].text);
        // on applique la police
        v1.setTypeface(face);
        v2.setText(ennonce[1].text);
        v2.setTypeface(face);
        v3.setText(ennonce[2].text);
        v3.setTypeface(face);
        v4.setText(ennonce[3].text);
        v4.setTypeface(face);

        // création d'un DragLinearLayout permettant l'utilisation du drag & drop
        DragLinearLayout dragLinearLayout = (DragLinearLayout) findViewById(R.id.container);
        // rend les éléments du DragLinearLayout draggables
        for (int i = 0; i < dragLinearLayout.getChildCount(); i++) {
            View child = dragLinearLayout.getChildAt(i);
            dragLinearLayout.setViewDraggable(child, child);
        }
        // Listener qui permet de switcher entre deux propositions et met à jour la position(utile pour vérifier l'ordre)
        dragLinearLayout.setOnViewSwapListener(new DragLinearLayout.OnViewSwapListener() {
            @Override
            public void onSwap(View firstView, int firstPosition,
                               View secondView, int secondPosition) {
                Proposition tmp = ennonce[firstPosition];
                ennonce[firstPosition] = ennonce[secondPosition];
                ennonce[secondPosition] = tmp;
            }
        });
        // créatio bouton valider
        Button validate = (Button) findViewById(R.id.validate_btn);
        validate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            /* création de l'int (variable où on stocke les correpondances justes... */
                                            int juste = 0;
                                            /*Boucle tableau vérif*/
                                            for (int i = 0; i < ennonce.length; i++) {
                                                if (ennonce[i].id == correction[i])
                                                    juste++;
                                            }
                                            //si l'ordre est correct
                                            if (juste == 4) {
                                                 /* lancement du son juste */
                                                MediaPlayer vrai = MediaPlayer.create(getApplicationContext(), R.raw.vrai);
                                                vrai.start();
                                                // sauvegarde de l'avancement de l'exo
                                                Sauvegarde.sauvegardeExo(exo, getApplicationContext());
                                                // création du bouton suivant
                                                Button suivant = (Button) findViewById(R.id.suivant);
                                                // on rend le bouton suivant visible
                                                suivant.setVisibility(View.VISIBLE);
                                                // on cache le bouton valider
                                                Button validay = (Button) findViewById(R.id.validate_btn);
                                                validay.setVisibility(View.INVISIBLE);
                                                // le bouton suivant renvoi vers la second activity
                                                suivant.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Intent adios = new Intent();
                                                        adios.putExtra("listExercices", ListCategorie.redirect(exo, getApplicationContext()));
                                                        // envoie le resultcode pour le refresh
                                                        setResult(1, adios);
                                                        finish();
                                                    }

                                                });
                                            } else {
                                                // lancement du son faux
                                                MediaPlayer wrong = MediaPlayer.create(getApplicationContext(), R.raw.faux);
                                                wrong.start();
                                            }
                                        }
                                    }
        );

    }

    private class Proposition {
        public int id;
        public String text;

        public Proposition(int id, String text) {
            this.id = id;
            this.text = text;
        }
    }
}

