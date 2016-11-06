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

import fr.wildcodeschool.apprenti.javabien.Model.Exercice;

public class ExoDragActivity extends AppCompatActivity {
    private int[] correction = new int[4];
    private Exercice exo;
    private Typeface face;
    private Proposition[] ennonce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_drag);
        // set custom font
        this.face = Typeface.createFromAsset(getAssets(), Constante.FONT_ALWYN);
        // get exercice from listExoActivity
        this.exo = (Exercice) getIntent().getSerializableExtra(Constante.SERIALIZED_EXERCICE);

        // create array of Purposals
        ennonce = new Proposition[]{
                new Proposition(0, exo.getPropositon()),
                new Proposition(1, exo.getProposition2()),
                new Proposition(2, exo.getProposition3()),
                new Proposition(3, exo.getProposition4())
        };
        // set exercice course text
        TextView cours = (TextView) findViewById(R.id.cours);
        cours.setText(exo.getCours());
        //   convert exercice answer into int array
        String toconvert = exo.getReponse();
        String[] splited = toconvert.split(" ");
        for (int j = 0; j < splited.length; j++) {
            correction[j] = Integer.valueOf(splited[j]);
        }


        TextView v1 = (TextView) findViewById(R.id.drag1);
        TextView v2 = (TextView) findViewById(R.id.drag2);
        TextView v3 = (TextView) findViewById(R.id.drag3);
        TextView v4 = (TextView) findViewById(R.id.drag4);
        // show purposals text in views
        v1.setText(ennonce[0].text);
        // custom font
        v1.setTypeface(face);
        v2.setText(ennonce[1].text);
        v2.setTypeface(face);
        v3.setText(ennonce[2].text);
        v3.setTypeface(face);
        v4.setText(ennonce[3].text);
        v4.setTypeface(face);

        // creating DragLinearLayout to use drag&drop
        DragLinearLayout dragLinearLayout = (DragLinearLayout) findViewById(R.id.container);
        // set childs dragable
        for (int i = 0; i < dragLinearLayout.getChildCount(); i++) {
            View child = dragLinearLayout.getChildAt(i);
            dragLinearLayout.setViewDraggable(child, child);
        }
        // Listener allowing to switch position and update new position(usefull to check order)
        dragLinearLayout.setOnViewSwapListener(new DragLinearLayout.OnViewSwapListener() {
            @Override
            public void onSwap(View firstView, int firstPosition,
                               View secondView, int secondPosition) {
                Proposition tmp = ennonce[firstPosition];
                ennonce[firstPosition] = ennonce[secondPosition];
                ennonce[secondPosition] = tmp;
            }
        });
        Button validate = (Button) findViewById(R.id.validate_btn);
        validate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //variable to check right positions
                                            int juste = 0;
                                            // get occurences with exercice right answer
                                            for (int i = 0; i < ennonce.length; i++) {
                                                if (ennonce[i].id == correction[i])
                                                    juste++;
                                            }
                                            //if all is right
                                            if (juste == ennonce.length) {
                                                 // playing congratulation song
                                                MediaPlayer vrai = MediaPlayer.create(getApplicationContext(), R.raw.vrai);
                                                vrai.start();
                                                // save progress
                                                Sauvegarde.sauvegardeExo(exo, getApplicationContext());
                                                // get next button
                                                Button suivant = (Button) findViewById(R.id.suivant);
                                                // show next button
                                                suivant.setVisibility(View.VISIBLE);
                                                // hide validate
                                                Button validay = (Button) findViewById(R.id.validate_btn);
                                                validay.setVisibility(View.INVISIBLE);
                                                // next button action
                                                suivant.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Intent adios = new Intent();
                                                        adios.putExtra(Constante.SERIALIZED_LIST, ListCategorie.redirect(exo, getApplicationContext()));
                                                        setResult(Constante.REFRESH, adios);
                                                        finish();
                                                    }

                                                });
                                            } else {
                                                // play false song
                                                MediaPlayer wrong = MediaPlayer.create(getApplicationContext(), R.raw.faux);
                                                wrong.start();
                                            }
                                        }
                                    }
        );

    }

    // class used to put id and proposal together
    private class Proposition {
        public int id;
        public String text;

        public Proposition(int id, String text) {
            this.id = id;
            this.text = text;
        }
    }
}

