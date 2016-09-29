package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


import java.util.ArrayList;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

import static fr.wildcodeschool.apprenti.javabien.R.id.button;

public class SecondActivity extends Activity {

    private ArrayList mButtons = new ArrayList();

private Context context;
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent prout = getIntent();

      final  ArrayList<Contenant> listExo = (ArrayList<Contenant>)prout.getSerializableExtra("listeExercices");


        final ArrayList<String> listenom =new ArrayList<String>();
        for(int i=0;i<listExo.size();i++){


            listenom.add(listExo.get(i).getExonom());

        }

        final GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new CustomGridAdapter(this,listenom));


        gridView.setOnItemClickListener(
                new OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id){



                        if(listExo.get(position).getExoType().equals("0")){
                            Intent intent = new Intent(SecondActivity.this,ExoActivity.class);
                            intent.putExtra("amont",listExo.get(position));
                            intent.putExtra("position",position);
                            startActivity(intent);

                        }else if(listExo.get(position).getExoType().equals("1")) {
                            Intent intent = new Intent(SecondActivity.this,ExoActivity2.class);
                            intent.putExtra("amont",listExo.get(position));
                            intent.putExtra("position",position);
                            startActivity(intent);

                        }else {
                            Intent intent = new Intent(SecondActivity.this,ExoActivity.class);
                            intent.putExtra("amont",listExo.get(position));
                            intent.putExtra("position",position);
                            startActivity(intent);
                        }

                    }



                }
        );
    }



}