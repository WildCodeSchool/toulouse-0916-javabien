package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

public class MainActivity extends AppCompatActivity {
    private List<Contenant> mContenantList;
    private DBHandler mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


              mDBHelper= new DBHandler(this);
        mDBHelper.getReadableDatabase();
        // check database
        File database =getApplicationContext().getDatabasePath(DBHandler.DBNAME);
        if (false== database.exists()) {
            mDBHelper.getReadableDatabase();
        }
            //copy
            if(copyDatabase(this)){
                Toast.makeText(this,"elle existe déjà!",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
                        return;

        }


Button debouton = (Button)findViewById(R.id.button);
        debouton.setOnClickListener(new View.OnClickListener() {
@Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("listeExercices",mDBHelper.getListDebutant());
                startActivity(intent);


            }
        });

    }




    private boolean copyDatabase(Context context){
        try{

            InputStream inpuStream = context.getAssets().open(DBHandler.DBNAME);
            String outFileName = DBHandler.DBLOCATION+DBHandler.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length =0;
            while((length=inpuStream.read(buff))>0){
                outputStream.write(buff, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }




    }






}
