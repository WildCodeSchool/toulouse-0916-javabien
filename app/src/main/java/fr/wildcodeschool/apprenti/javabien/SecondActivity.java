package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class SecondActivity extends Activity implements View.OnClickListener {

    private ArrayList mButtons = new ArrayList();

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent prout = getIntent();
        ArrayList<Contenant> listExo = (ArrayList<Contenant>)prout.getSerializableExtra("listExercices");

        Button cb = null;

        for (int i =0; i<10; i++) {
            cb = new Button(this);
            cb.setText(Integer.toString(i));
            cb.setLayoutParams(new  GridView.LayoutParams(500, 450));
            cb.setBackgroundResource(R.drawable.boutonrect);
            cb.setOnClickListener(this);
            cb.setId(i);
            mButtons.add(cb);
        }

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new CustomAdapter(mButtons));

    }


    @Override
    public void onClick(View v) {
       Button selection = (Button)v;
       if (selection.getText().equals("0")){ Intent intent = new Intent(SecondActivity.this, ExoActivity2.class);
        startActivity(intent);}else if (selection.getText().equals("1")){
           Intent intent = new Intent(SecondActivity.this, ExoActivity.class);
           startActivity(intent);

       }
       //Toast.makeText(getBaseContext(), selection.getText()+ " was pressed!", Toast.LENGTH_SHORT).show();
    }
    public class CustomAdapter extends BaseAdapter {

        private ArrayList mButtons = null;

        public CustomAdapter(ArrayList b)
        {
            mButtons = b;
        }

        @Override
        public int getCount() {
            return mButtons.size();
        }

        @Override
        public Object getItem(int position) {
            return (Object) mButtons.get(position);
        }

        @Override
        public long getItemId(int position) {
//in our case position and id are synonymous
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Button button;
            if (convertView == null) {
                button = (Button) mButtons.get(position);
            } else {
                button = (Button) convertView;
            }
            return button;
        }
    }
}