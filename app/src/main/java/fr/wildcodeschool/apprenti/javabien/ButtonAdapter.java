package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;



 /* Created by apprenti on 21/09/16.*/

public class ButtonAdapter extends BaseAdapter {

 private Context context;
 private Integer[] imageIds = {
 R.drawable.boutonrond, R.drawable.boutonrond,
 R.drawable.boutonrond, R.drawable.boutonrond,R.drawable.boutonrond,R.drawable.boutonrond
 };

 public ButtonAdapter(Context c) {
 context = c;
 }

 public int getCount() {
 return imageIds.length;
 }

 public Object getItem(int position) {
 return imageIds[position];
 }

 public long getItemId(int position) {
 return 0;
 }

 public View getView(int position, View view, ViewGroup parent) {
 ImageView iview;

 if (view == null) {

 iview = new ImageView(context);
 iview.setLayoutParams(new GridView.LayoutParams(500,500));
 iview.setScaleType(ImageView.ScaleType.CENTER_CROP);
  iview.setBaselineAlignBottom(true);
 iview.setPadding(55, 55, 55, 55);
 } else {
 iview = (ImageView) view;
 }
 iview.setImageResource(imageIds[position]);
 return iview;
 }
}
