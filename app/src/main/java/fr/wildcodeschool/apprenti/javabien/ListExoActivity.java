package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import fr.wildcodeschool.apprenti.javabien.Model.Exercice;

public class ListExoActivity extends Activity {
    int requestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exo);
        // création de l'intent qui récupère arrayList des exos du niveau
        Intent prout = getIntent();
        // place l'intent dans l'arrayList
        final ArrayList<Exercice> listcategorie = (ArrayList<Exercice>) prout.getSerializableExtra(Constante.SERIALIZED_LIST);
        //creation de l'array pour l'adapter
        final ArrayList<Exercice> listExo = new ArrayList<>();
        listExo.addAll(ListCategorie.redirect(listcategorie.get(0), this));
        //ajout d'un élément quizz dans la gridview avec le niveau de la liste d'exos
        Exercice quizz = new Exercice(Constante.QUIZZ, listExo.get(0).getCategorie(), 15, Constante.QUIZZ, Constante.QUIZZ, 1);
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
                        if (listExo.get(position).getExoType().equals(Constante.QCM)) {
                            Intent intent = new Intent(ListExoActivity.this, ExoQcmActivity.class);
                            //envoi de l'exo à l'activité suivante
                            intent.putExtra(Constante.SERIALIZED_EXERCICE, listExo.get(position));
                            //pour refresh la page on demande un resultat à l'activité suivante
                            startActivityForResult(intent, requestCode = Constante.REFRESH);
                            // Exo de type Vrai
                        } else if (listExo.get(position).getExoType().equals(Constante.VRAI)) {
                            Intent intent = new Intent(ListExoActivity.this, ExoVraiActivity.class);
                            //envoi de l'exo à l'activité suivante
                            intent.putExtra(Constante.SERIALIZED_EXERCICE, listExo.get(position));
                            //pour refresh la page on demande un resultat à l'activité suivante
                            startActivityForResult(intent, requestCode = Constante.REFRESH);
                            // Quizz
                        } else if (listExo.get(position).getCategorie().equals(Constante.QUIZZ)) {
                            //initialisation d'une arraylist pour l'étape suivante
                            ArrayList<Exercice> quizz = new ArrayList<Exercice>();
                            // recherche du type d'éxercice du premier exo du quizz dans la bdd
                            quizz.addAll(ListCategorie.redirect(listExo.get(position), getApplicationContext()));
                            // renvoie  type d'éxercice et envoi du premier exercice en extra
                            Intent intent = new Intent(ListExoActivity.this, QuizzQcmActivity.class);
                            intent.putExtra(Constante.SERIALIZED_EXERCICE, quizz.get(0));
                            startActivity(intent);
                        }
                        // Drag
                        else {
                            Intent intent = new Intent(ListExoActivity.this, ExoDragActivity.class);
                            //envoi de l'exo à l'activité suivante
                            intent.putExtra(Constante.SERIALIZED_EXERCICE, listExo.get(position));
                            startActivityForResult(intent, requestCode = Constante.REFRESH);
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
        if (requestCode == Constante.REFRESH) {
            Intent i = getIntent();
            final ArrayList<Exercice> renvoi = (ArrayList<Exercice>) i.getSerializableExtra(Constante.SERIALIZED_LIST);
            Intent refresh = new Intent(this, ListExoActivity.class);
            //renvoi de la liste des exos car indispensable au lancement de celle ci
            refresh.putExtra(Constante.SERIALIZED_LIST, renvoi);
            startActivity(refresh);
            this.finish();
        }

    }
}