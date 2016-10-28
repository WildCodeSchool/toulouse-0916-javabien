package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ScrollView;


import java.util.ArrayList;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

import static android.R.attr.data;

public class SecondActivity extends Activity {

    private ArrayList mButtons = new ArrayList();
    int requestCode;

    private Context context;
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent prout = getIntent();

      final  ArrayList<Contenant> listcategorie1 = (ArrayList<Contenant>)prout.getSerializableExtra("listeExercices");
        // pour refresh la page récupération de la liste dans la database en fonction de la catégorie des extras
        final ArrayList<Contenant> listcategorie = ListCategorie.redirect(listcategorie1.get(0),listcategorie1.get(0).getId_exos(),getApplicationContext());

        //creation de l'array pour l'adapter
      final  ArrayList<Contenant> listExo= new ArrayList<Contenant>();
        listExo.addAll(ListCategorie.redirect(listcategorie.get(0),0,this));
       /* for(int i=0;i<listExo.size();i++){



            listenom.add(listExo.get(i).getExonom());

        }*/
        //ajout d'un élément quizz dans la gridview avec le niveau de la liste d'exos
        Contenant quizz = new Contenant("quizz",listExo.get(0).getCategorie(),15,"","", "", "",
                "", "","","","", "","", "quizz",1) ;
        listExo.add(quizz);

        // recupération de l'id de la gridView
        final GridView gridView = (GridView) findViewById(R.id.gridview);


        // application de l'adapter
        gridView.setAdapter(new CustomGridAdapter(this,listExo));

        // conditions du click
        gridView.setOnItemClickListener(
                new OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id){


                        // si l'exo est de type QCM
                        if(listExo.get(position).getExoType().equals("qcm")){
                            Intent intent = new Intent(SecondActivity.this,ExoActivityQcm.class);
                            intent.putExtra("amont",listExo.get(position));
                            intent.putExtra("position",position);
                            startActivityForResult(intent,requestCode =1);
                        // Exo de type Insert
                        }else if(listExo.get(position).getExoType().equals("vrai")) {
                            Intent intent = new Intent(SecondActivity.this,ExoVraiActivity.class);
                            intent.putExtra("amont",listExo.get(position));
                            intent.putExtra("position",position);
                            startActivityForResult(intent,requestCode =1);
                            // Quizz
                        }else if(listExo.get(position).getCategorie().equals("quizz")){
                            //initialisation d'une arraylist pour l'étape suivante
                            ArrayList<Contenant> quizz = new ArrayList<Contenant>();
                            // recherche du type d'éxercice du premier exo du quizz dans la bdd
                            quizz.addAll(ListCategorie.redirect(listExo.get(position),position,getApplicationContext()));

                            // renvoi vers le bon type d'éxercice et envoi du premier exercice en extra

                            if(quizz.get(0).getExoType().equals("qcm")) {

                               Intent intent = new Intent(SecondActivity.this, QuizzQcmActivity.class);
                                intent.putExtra("amont", quizz.get(0));
                                startActivity(intent);
                            }
                        }

                        // Drag
                        else {
                            Intent intent = new Intent(SecondActivity.this,ExoActivityDrag.class);
                            intent.putExtra("amont",listExo.get(position));
                            intent.putExtra("position",position);
                            startActivityForResult(intent,requestCode =1);
                        }

                    }



                }
        );



        }


    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        super.onActivityResult(requestCode,resultCode,data);


        if(requestCode== 1){


            Intent i =getIntent();
            final  ArrayList<Contenant> renvoi = (ArrayList<Contenant>)i.getSerializableExtra("listeExercices");

            Intent refresh = new Intent(this,SecondActivity.class);
            refresh.putExtra("listeExercices",renvoi);
            startActivity(refresh);
            this.finish();
        }

    }


}