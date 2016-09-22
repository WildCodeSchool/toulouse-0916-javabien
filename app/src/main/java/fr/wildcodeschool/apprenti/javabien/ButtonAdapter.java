package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by apprenti on 21/09/16.
 */
public class ButtonAdapter extends BaseAdapter {

    private Context mContext;

    //constructeur
    public ButtonAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    //créer une nouvelle ImageView pour chaque item référencé avec l'Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        Button btn;
        if (convertView == null) {
            btn = new Button(mContext);
            // imageView.setMaxHeight(100);
            // imageView.setMaxWidth(100);
            //centrer et couper l'image
            btn.setLayoutParams(new GridView.LayoutParams(500, 500));
            //btn.setMaxWidth(50);
           // btn.setMaxHeight(50);
            btn.setPadding(8, 8, 8, 8);

        } else {
            btn = (Button) convertView;
        }

        btn.setBackgroundResource(mThumbIds[position]);
        btn.setId(position);
        return btn;
    }
    //Les images dans l'Array
    public Integer[] mThumbIds = {
            R.drawable.deer, R.drawable.deer,
            R.drawable.wild, R.drawable.wild,
            R.drawable.wild, R.drawable.wild
    };


}
