package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import java.util.ArrayList;
import fr.wildcodeschool.apprenti.javabien.Model.Exercice;

//custom gridview
public class CustomGridAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<Exercice> listExo;
    Button buttonExercice;

    public CustomGridAdapter(Context context, ArrayList<Exercice> listExo) {
        this.context = context;
        this.listExo = listExo;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //set image for buttons depending of saved status of exercices
        if (listExo.get(position).getAvancement() == Constante.SAVE_TRUE) {
            convertView = inflater.inflate(R.layout.row_grid, parent, false);
            this.buttonExercice = (Button) convertView
                    .findViewById(R.id.imgProfilePicture);
            // button can be clicked
            this.buttonExercice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((GridView) parent).performItemClick(v, position, 0);
                }
            });

        } else {
            convertView = inflater.inflate(R.layout.row_grid_locked, parent, false);
        }
        // set text in buttons
        this.buttonExercice = (Button) convertView
                .findViewById(R.id.imgProfilePicture);
        this.buttonExercice.setTextColor(Color.WHITE);
        this.buttonExercice.setText(this.listExo.get(position).getExonom());

        return convertView;
    }
    // parent methods
    @Override
    public int getCount() {
        return listExo.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}