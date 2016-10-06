package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;


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
        final ArrayList<Contenant> listcategorie = ListCategorie.redirect(listcategorie1.get(0),listcategorie1.get(0).getId_exos(),getApplicationContext());

        //creation de l'array pour l'adapter
      final  ArrayList<Contenant> listExo= new ArrayList<Contenant>();
        listExo.addAll(ListCategorie.redirect(listcategorie.get(0),0,this));
       /* for(int i=0;i<listExo.size();i++){



            listenom.add(listExo.get(i).getExonom());

        }*/

        final GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new CustomGridAdapter(this,listExo));


        gridView.setOnItemClickListener(
                new OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id){



                        if(listExo.get(position).getExoType().equals("qcm")){
                            Intent intent = new Intent(SecondActivity.this,ExoActivityQcm.class);
                            intent.putExtra("amont",listExo.get(position));
                            intent.putExtra("position",position);
                            startActivityForResult(intent,requestCode =1);

                        }else if(listExo.get(position).getExoType().equals("insert")) {
                            Intent intent = new Intent(SecondActivity.this,ExoActivityInsert.class);
                            intent.putExtra("amont",listExo.get(position));
                            intent.putExtra("position",position);
                            startActivity(intent);

                        }else {
                            Intent intent = new Intent(SecondActivity.this,ExoActivityDrag.class);
                            intent.putExtra("amont",listExo.get(position));
                            intent.putExtra("position",position);
                            startActivity(intent);
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