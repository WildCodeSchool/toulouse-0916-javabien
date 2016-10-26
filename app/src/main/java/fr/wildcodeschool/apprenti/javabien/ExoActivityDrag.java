package fr.wildcodeschool.apprenti.javabien;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.graphics.Typeface;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jmedeisis.draglinearlayout.DragLinearLayout;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class ExoActivityDrag extends Activity {

    private int[] correction = new int[]{1,2,0,3};
    private Answer[] ennonce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_drag);
        /* Récupération infos exo en cours envoyé par l'activité précédente */
        Intent recup = getIntent();

        final Contenant exo = (Contenant) recup.getSerializableExtra("amont");

        ennonce = new Answer[]{
                new Answer(0, exo.getPropositon()),
                new Answer(1, exo.getProposition2()),
                new Answer(2, exo.getProposition3()),
                new Answer(3, exo.getProposition4())
        };
        TextView cours = (TextView)findViewById(R.id.cours);
        cours.setText(exo.getCours());
        String toconvert = exo.getReponse();
        char[] splited = toconvert.toCharArray();

        for (int j = 0 ; j < 4 ; j++){
            if(splited[j]== '0'){
                correction[j] = 0;
            }else  if(splited[j]== '1'){
                correction[j] = 1;
            }else  if(splited[j]== '2'){
                correction[j] = 2;
            }else  if(splited[j]== '3'){
                correction[j] = 3;
            }
        }


        TextView v1 = (TextView) findViewById(R.id.drag1);
        TextView v2 = (TextView) findViewById(R.id.drag2);
        TextView v3 = (TextView) findViewById(R.id.drag3);
        TextView v4 = (TextView) findViewById(R.id.drag4);
        v1.setText(ennonce[0].text);
        v2.setText(ennonce[1].text);
        v3.setText(ennonce[2].text);
        v4.setText(ennonce[3].text);



        DragLinearLayout dragLinearLayout = (DragLinearLayout) findViewById(R.id.container);
        // set all children draggable except the first (the header)
        for(int i = 0; i < dragLinearLayout.getChildCount(); i++){
            View child = dragLinearLayout.getChildAt(i);
            dragLinearLayout.setViewDraggable(child, child); // the child is its own drag handle
        }

        dragLinearLayout.setOnViewSwapListener(new DragLinearLayout.OnViewSwapListener() {
            @Override
            public void onSwap(View firstView, int firstPosition,
                               View secondView, int secondPosition) {
                Answer tmp = ennonce[firstPosition];
                ennonce[firstPosition] = ennonce[secondPosition];
                ennonce[secondPosition] = tmp;
            }
        });

        Button validate = (Button) findViewById(R.id.validate_btn);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* création de l'int (variable où on stocke les correpondances justes... */
                int juste = 0;
                /*Boucle tableau vérif*/
                for(int i = 0 ; i<4; i++){
                    if (ennonce[i].id==correction[i])
                        juste ++;
                }
                if (juste == 4)
                {/*Toast.makeText(ExoActivityDrag.this, "victoire", Toast.LENGTH_SHORT).show();*/
                /* lancement du son juste */
                MediaPlayer vrai = MediaPlayer.create(getApplicationContext(),R.raw.vrai);
                vrai.start();
                Button suivant = (Button)findViewById(R.id.suivant);
                suivant.setVisibility(View.VISIBLE);
                    Button validay = (Button)findViewById(R.id.validate_btn);
                    validay.setVisibility(View.INVISIBLE);

                    suivant.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent adios = new Intent();
                            adios.putExtra("listExercices",ListCategorie.redirect(exo,exo.getId_exos(),getApplicationContext()));
                            setResult(1,adios);

                            finish();

                        }

                    });}
                else{
                    // lancement du son faux
                    MediaPlayer wrong = MediaPlayer.create(getApplicationContext(),R.raw.faux);
                    wrong.start();
                   /* Toast.makeText(ExoActivityDrag.this, "Mauvay", Toast.LENGTH_SHORT).show();*/
                }

            }
        }

        );

    }

    private class Answer {
        public int id;
        public String text;

        public Answer(int id, String text) {
            this.id = id;
            this.text = text;
        }
    }
}

