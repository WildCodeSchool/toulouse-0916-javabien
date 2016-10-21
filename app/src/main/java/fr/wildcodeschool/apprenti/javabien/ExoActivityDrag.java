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
                {Toast.makeText(ExoActivityDrag.this, "victoire", Toast.LENGTH_SHORT).show();
                /* lancement du son juste */
                MediaPlayer vrai = MediaPlayer.create(getApplicationContext(),R.raw.vrai);
                vrai.start();}
                else{
                    // lancement du son faux
                    MediaPlayer wrong = MediaPlayer.create(getApplicationContext(),R.raw.faux);
                    wrong.start();
                    Toast.makeText(ExoActivityDrag.this, "Mauvay", Toast.LENGTH_SHORT).show();
                }

                 /*Toast Affichage position */
               // Toast.makeText(ExoActivityDrag.this,"["+ennonce[0].id+", "+ennonce[1].id+", "+ennonce[2].id+", "+ennonce[3].id+"]", Toast.LENGTH_LONG).show();

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
/*public class ExoActivityDrag extends Activity {

    private TextView option1, option2, option3, choix1, choix2, choix3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_drag);
        //views to drag
        option1 = (TextView)findViewById(R.id.option_1);
        option2 = (TextView)findViewById(R.id.option_2);
        option3 = (TextView)findViewById(R.id.option_3);

        //set touch listeners
        option1.setOnTouchListener(new ChoiceTouchListener());
        option2.setOnTouchListener(new ChoiceTouchListener());
        option3.setOnTouchListener(new ChoiceTouchListener());

        //views to drop onto
        choix1 = (TextView)findViewById(R.id.choix_1);
        choix2 = (TextView)findViewById(R.id.choix_2);
        choix3 = (TextView)findViewById(R.id.choix_3);

        //set drag listeners
        choix1.setOnDragListener(new ChoiceDragListener());
        choix2.setOnDragListener(new ChoiceDragListener());
        choix3.setOnDragListener(new ChoiceDragListener());
}
    private final class ChoiceTouchListener implements OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //setup drag
                ClipData data = ClipData.newPlainText("", "");
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            }
            else {
                return false;
            }
        }

    }
    private class ChoiceDragListener implements OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            //handle drag events
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a drop view
                    //handle the dragged view being dropped over a target view
                    View view = (View) event.getLocalState();
                    //stop displaying the view where it was before it was dragged
                    view.setVisibility(View.INVISIBLE);
                    //view dragged item is being dropped on
                    TextView dropTarget = (TextView) v;
                    //view being dragged and dropped
                    TextView dropped = (TextView) view;
                    //update the text in the target view to reflect the data being dropped
                    dropTarget.setText(dropped.getText());
                    //make it bold to highlight the fact that an item has been dropped
                    dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                    //if an item has already been dropped here, there will be a tag
                    Object tag = dropTarget.getTag();
                    //if there is already an item here, set it back visible in its original place
                    if(tag!=null)
                    {
                        //the tag is the view id already dropped here
                        int existingID = (Integer)tag;
                        //set the original view visible again
                        findViewById(existingID).setVisibility(View.VISIBLE);
                    }
                    //set the tag in the target view to the ID of the view being dropped
                    dropTarget.setTag(dropped.getId());


                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }

    }
}*/


