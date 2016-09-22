package fr.wildcodeschool.apprenti.javabien;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ButtonAdapter(this));

      /**  gridView.setOnClickListener((parent, view, position, id)) {
            Intent secondActivite = new Intent(SecondActivity.this, ExoActivity)**/
        }



    
    /**@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }**/
}
