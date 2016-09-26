package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


//Created by apprenti on 21/09/16.



public class ButtonAdapter extends BaseAdapter {

    private Context mContext;
    private Integer[] imageIds = {
            R.drawable.boutonrect, R.drawable.boutonrect,
            R.drawable.boutonrect, R.drawable.boutonrect, R.drawable.boutonrect, R.drawable.boutonrect
    };
    public String[] txtIds = {
            "Exercices1", "Exercices2", "Exercuces1", "Exercices2", "Exercices1", "Exercices2"
    };

 public ButtonAdapter(Context mContext) {
 this.mContext = mContext;
 }

 public int getCount() {
 return 6;
 }

 public Object getItem(int position) {
 return null;
 }

 public long getItemId(int position) {
 return 0;
 }

    public View getView(int position, View view, ViewGroup parent) {
        Button iview;
        if (view == null) {

            iview = new Button(mContext);
            iview.setLayoutParams(new GridView.LayoutParams(500, 500));
            iview.setGravity(Gravity.CENTER_HORIZONTAL);
           // iview.setOnClickListener();
            //iview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //iview.setBaselineAlignBottom(true);
            //iview.setPadding(50, 50, 50, 50);
        } else {
            iview = (Button) view;
        }
        iview.setText(txtIds[position]);
        iview.setTextColor(Color.WHITE);
        iview.setBackgroundResource(imageIds[position]);
        return iview;
    }
}
