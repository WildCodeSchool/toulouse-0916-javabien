package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

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
        ;
        DBHandler prout = new DBHandler(this);
        prout.getListContenant();



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
            Log.v("MainActivity","DB copied");
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }





    public void sendMessage(View view,Context context)
    {
        ArrayList proutlist = new ArrayList<Contenant>();
        DBHandler debutantList = new DBHandler(context);
        debutantList.getListDebutant().addAll(proutlist);
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("debutants",proutlist);
        startActivity(intent);
    }
}
