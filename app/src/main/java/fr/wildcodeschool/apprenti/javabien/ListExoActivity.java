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
        final ArrayList<Exercice> listcategorie = (ArrayList<Exercice>) getIntent().getSerializableExtra(Constante.SERIALIZED_LIST);
        //refreshing arrayList
        final ArrayList<Exercice> listExo = new ArrayList<>();
        listExo.addAll(ListCategorie.redirect(listcategorie.get(0), this));
        // add button quizz
        Exercice quizz = new Exercice(Constante.QUIZZ, listExo.get(0).getCategorie(), listExo.size(),Constante.QUIZZ, Constante.QUIZZ, 1);
        listExo.add(quizz);
        // gridView
        final GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new CustomGridAdapter(this, listExo));
        gridView.setOnItemClickListener(
                new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        // if exercice type is QCM
                        if (listExo.get(position).getExoType().equals(Constante.QCM)) {
                            Intent intent = new Intent(ListExoActivity.this, ExoQcmActivity.class);
                            // send exercice
                            intent.putExtra(Constante.SERIALIZED_EXERCICE, listExo.get(position));
                            //ask for result to refresh
                            startActivityForResult(intent, requestCode = Constante.REFRESH);
                            // if exercice type is Vrai
                        } else if (listExo.get(position).getExoType().equals(Constante.VRAI)) {
                            Intent intent = new Intent(ListExoActivity.this, ExoVraiActivity.class);
                            //envoi de l'exo à l'activité suivante
                            intent.putExtra(Constante.SERIALIZED_EXERCICE, listExo.get(position));
                            //ask for result to refresh
                            startActivityForResult(intent, requestCode = Constante.REFRESH);
                            // Quizz
                        } else if (listExo.get(position).getCategorie().equals(Constante.QUIZZ)) {
                            ArrayList<Exercice> quizz = new ArrayList<Exercice>();
                            // get list of quizz exercices
                            quizz.addAll(ListCategorie.redirect(listExo.get(position), getApplicationContext()));
                            // send exercice
                            Intent intent = new Intent(ListExoActivity.this, QuizzQcmActivity.class);
                            intent.putExtra(Constante.SERIALIZED_EXERCICE, quizz.get(0));
                            startActivity(intent);
                        }
                        // Drag
                        else {
                            Intent intent = new Intent(ListExoActivity.this, ExoDragActivity.class);
                            // send exercice
                            intent.putExtra(Constante.SERIALIZED_EXERCICE, listExo.get(position));
                            startActivityForResult(intent, requestCode = Constante.REFRESH);
                        }
                    }
                }
        );
    }
    // refresher
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constante.REFRESH) {
            final ArrayList<Exercice> renvoi = (ArrayList<Exercice>) getIntent().getSerializableExtra(Constante.SERIALIZED_LIST);
            Intent refresh = new Intent(this, ListExoActivity.class);
            //add arraylist cause needed to launch activity
            refresh.putExtra(Constante.SERIALIZED_LIST, renvoi);
            startActivity(refresh);
            this.finish();
        }

    }
}