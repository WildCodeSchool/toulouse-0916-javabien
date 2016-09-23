package fr.wildcodeschool.apprenti.javabien;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<TItem> gridArray = new ArrayList<TItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //set grid view item
        Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.deer);
        Bitmap userIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.boutonrond);

        gridArray.add(new TItem(R.drawable.deer,"Home"));
        gridArray.add(new TItem(R.drawable.deer,"User"));
        gridArray.add(new TItem(R.drawable.deer,"House"));
        gridArray.add(new TItem(R.drawable.deer,"Friend"));
        gridArray.add(new TItem(R.drawable.deer,"Home"));
        gridArray.add(new TItem(R.drawable.deer,"Personal"));
        gridArray.add(new TItem(R.drawable.deer,"Home"));
        gridArray.add(new TItem(R.drawable.deer,"User"));
        gridArray.add(new TItem(R.drawable.deer,"Building"));
        gridArray.add(new TItem(R.drawable.deer,"User"));
        gridArray.add(new TItem(R.drawable.deer,"Home"));
        gridArray.add(new TItem(R.drawable.deer,"xyz"));


//images
        int[] imageList = new int[gridArray.size()];
        for(int i=0;i<gridArray.size();i++){
            imageList[i]= gridArray.get(i).getBlob();
        }
 //text
        String[] nameList = new String[gridArray.size()];
        for (int i =0;i<gridArray.size();i++){
            nameList[i]= gridArray.get(i).getChok();
        }

        gridView = (GridView) findViewById(R.id.gridview);
        ButtonAdapter customGridAdapter = new ButtonAdapter(this);
        gridView.setAdapter(customGridAdapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position,
                                    long arg3) {
                Intent secondActivite = new Intent(SecondActivity.this, ExoActivity.class);
                startActivity(secondActivite);

            }
        });
            }

      /* gridView.setOnClickListener((parent, view, position, id)) {
            Intent secondActivite = new Intent(SecondActivity.this, ExoActivity)**/
        }



    
    /**@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }**/

