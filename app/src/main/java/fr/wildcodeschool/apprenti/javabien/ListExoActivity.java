package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class ListExoActivity extends Activity {
    int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // création de l'intent qui récupère arrayList des exos du niveau
        Intent prout = getIntent();
        // place l'intent dans l'arrayList
        final ArrayList<Contenant> listcategorie = (ArrayList<Contenant>) prout.getSerializableExtra("listeExercices");
        //creation de l'array pour l'adapter
        final ArrayList<Contenant> listExo = new ArrayList<Contenant>();
        listExo.addAll(ListCategorie.redirect(listcategorie.get(0), this));
        //ajout d'un élément quizz dans la gridview avec le niveau de la liste d'exos
        Contenant quizz = new Contenant("quizz", listExo.get(0).getCategorie(), 15, "", "", "", "",
                "", "", "", "", "", "", "", "quizz", 1);
        listExo.add(quizz);

        // recupération de l'id de la gridView
        final GridView gridView = (GridView) findViewById(R.id.gridview);


        // application de l'adapter
        gridView.setAdapter(new CustomGridAdapter(this, listExo));

        // conditions du click
        gridView.setOnItemClickListener(
                new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        // si l'exo est de type QCM
                        if (listExo.get(position).getExoType().equals("qcm")) {
                            Intent intent = new Intent(ListExoActivity.this, ExoQcmActivity.class);
                            //envoi de l'exo à l'activité suivante
                            intent.putExtra("amont", listExo.get(position));
                            //pour refresh la page on demande un resultat à l'activité suivante
                            startActivityForResult(intent, requestCode = 1);
                            // Exo de type Vrai
                        } else if (listExo.get(position).getExoType().equals("vrai")) {
                            Intent intent = new Intent(ListExoActivity.this, ExoVraiActivity.class);
                            //envoi de l'exo à l'activité suivante
                            intent.putExtra("amont", listExo.get(position));
                            //pour refresh la page on demande un resultat à l'activité suivante
                            startActivityForResult(intent, requestCode = 1);
                            // Quizz
                        } else if (listExo.get(position).getCategorie().equals("quizz")) {
                            //initialisation d'une arraylist pour l'étape suivante
                            ArrayList<Contenant> quizz = new ArrayList<Contenant>();
                            // recherche du type d'éxercice du premier exo du quizz dans la bdd
                            quizz.addAll(ListCategorie.redirect(listExo.get(position), getApplicationContext()));
                            // renvoie  type d'éxercice et envoi du premier exercice en extra
                            Intent intent = new Intent(ListExoActivity.this, QuizzQcmActivity.class);
                            intent.putExtra("amont", quizz.get(0));
                            startActivity(intent);
                        }
                        // Drag
                        else {
                            Intent intent = new Intent(ListExoActivity.this, ExoDragActivity.class);
                            //envoi de l'exo à l'activité suivante
                            intent.putExtra("amont", listExo.get(position));
                            startActivityForResult(intent, requestCode = 1);
                        }
                    }
                }
        );
    }
    // permet de refresh la page
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // à l'obtention du requestCode relance la page
        if (requestCode == 1) {
            Intent i = getIntent();
            final ArrayList<Contenant> renvoi = (ArrayList<Contenant>) i.getSerializableExtra("listeExercices");
            Intent refresh = new Intent(this, ListExoActivity.class);
            //renvoi de la liste des exos car indispensable au lancement de celle ci
            refresh.putExtra("listeExercices", renvoi);
            startActivity(refresh);
            this.finish();
        }

    }
}