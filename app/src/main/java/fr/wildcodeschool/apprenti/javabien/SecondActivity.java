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

public class SecondActivity extends Activity {

    private ArrayList mButtons = new ArrayList();

private Context context;
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent prout = getIntent();

      final  ArrayList<Contenant> listcategorie = (ArrayList<Contenant>)prout.getSerializableExtra("listeExercices");


        final ArrayList<String> listenom =new ArrayList<String>();
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
                            startActivity(intent);

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



}