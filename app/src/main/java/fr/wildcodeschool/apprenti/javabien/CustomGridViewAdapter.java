package fr.wildcodeschool.apprenti.javabien;

/**
 * Created by tuffery on 22/09/16.
 */
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *
 * @author manish.s
 *
 */
public class CustomGridViewAdapter extends ArrayAdapter<TItem> {
    Context context;
    int layoutResourceId;
    ArrayList<TItem> data = new ArrayList<TItem>();

    public CustomGridViewAdapter(Context context, int layoutResourceId,ArrayList<TItem> data
                                 ) {
        super(context, R.layout.row_grid);
        this.layoutResourceId = layoutResourceId;
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

      /*  LayoutInflater menuInflater= LayoutInflater.from(getContext());
        View customView = menuInflater.inflate(R.layout.row_grid,parent,false);

        long singleMenuItem = getPosition(position);
        TextView menuTexts = (TextView) customView.findViewById(R.id.item_text);
        ImageView egg = (ImageView) customView.findViewById(R.id.item_image);

        menuTexts.setText(singleMenuItem);


        egg.setImageResource(layoutResourceId[position]);*/

       View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
            holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
            row.setTag(holder);

        } else {
            holder = (RecordHolder) row.getTag();
        }

        TItem item = data.get(position);
        holder.txtTitle.setText(item.getChok());
        //holder.imageItem.setImageResource(item.getBlob());
        return row;

    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;

    }
}