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

//la gridview custom
public class CustomGridAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<Exercice> listExo;
    private ArrayList<String> listenom = new ArrayList<>();

    //constructeur
    public CustomGridAdapter(Context context, ArrayList<Exercice> listExo) {
        this.context = context;
        this.listExo = listExo;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        // boucle pour récupérer les noms des exos(placés dans listenom)
        for (int i = 0; i < listExo.size(); i++) {
            listenom.add(listExo.get(i).getExonom());
        }
        // combine le xml du bouton et la vue
        final ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_grid_locked, parent, false);
            // les boutons sont vraiment créés
            holder = new ViewHolder();
            holder.btn1 = (Button) convertView.findViewById(R.id.imgProfilePicture);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //attribution de l'image d'activation du boutton
        if (listExo.get(position).getAvancement() == 1) {
            convertView = inflater.inflate(R.layout.row_grid, parent, false);
        } else {
            convertView = inflater.inflate(R.layout.row_grid_locked, parent, false);
        }
        // configure le texte dans le bouton
        Button textView = (Button) convertView
                .findViewById(R.id.imgProfilePicture);
        textView.setTextColor(Color.WHITE);
        textView.setText(listenom.get(position));

        // si l'avancement est !1  alors le bouton n'est pas clickable
        if (listExo.get(position).getAvancement() == 1) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((GridView) parent).performItemClick(v, position, 0);
                }
            });
        }
        return convertView; // renvoie la vue
    }

    //class cointaining elements of view
    static class ViewHolder {
        private Button btn1;

    }
    // méthodes obligatoires non utilisées
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