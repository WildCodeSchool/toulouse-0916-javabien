package fr.wildcodeschool.apprenti.javabien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuffery on 22/09/16.
 */

public class DBHandler extends SQLiteOpenHelper{

    //Database version

    private static final int DATABASE_VERSION = 1;

    //Database name

    private static final String DATABASE_NAME = "exosInfos";

    //Contacts table name
    private static final String TABLE_EXOS = "exos";


    // Exos Columns names

    private static final String KEY_ID = "id";
    private static final String KEY_NAME ="name";
    private static final String KEY_INFO ="info";
    private static final String KEY_QUESTION ="question";
    private static final String KEY_REPONSE ="reponse";
    public DBHandler(Context context){
        super(context,DATABASE_NAME,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){

        String CREATE_CONTACTS_TABLE ="CREATE TABLE"+TABLE_EXOS+"("+KEY_ID+"INTEGER PRIMARY KEY"+KEY_NAME+"TEXT"+
                KEY_INFO+"TEXT"+KEY_QUESTION+"TEXT"+KEY_REPONSE+"TEXT"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        //drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_EXOS);
        //create table again
        onCreate(db);

    }
    //adding new shop
    public void addShop(Shop shop){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,shop.getName());//shop name
        values.put(KEY_INFO,shop.getInfo());//cours
        values.put(KEY_QUESTION,shop.getQuestion());// question
        values.put(KEY_REPONSE,shop.getReponse());// reponse
        // inserting row
        db.insert(TABLE_EXOS,null,values);
        db.close();// Closing connection
    }

    //getting one shop

    public Shop getShop(int id){

        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor =db.query(TABLE_EXOS,new String[]{KEY_ID,KEY_NAME,KEY_INFO,KEY_QUESTION,KEY_QUESTION},KEY_ID +"=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor!=null)
        cursor.moveToFirst();
        Shop contact = new Shop(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4));
        //return shop
        return contact;


    }

    // getting all shops

    public List<Shop> getAllShops(){

        List<Shop> shopList = new ArrayList<Shop>();
         // select all query
        String selectQuery = "SELECT * FROM"+TABLE_EXOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        //looping thro all rows and adding to list

        if(cursor.moveToFirst()){
            do{
                Shop shop = new Shop();
                shop.setId(Integer.parseInt(cursor.getString(0)));
                shop.setName(cursor.getString(1));
                shop.setInfo(cursor.getString(2));
                shop.setQuestion(cursor.getString(3));
                shop.setReponse(cursor.getString(4));
                //adding to list
                shopList.add(shop);
            }while(cursor.moveToNext());
        }
        //return contact list
        return shopList;
    }

    //getting shops count

    public int getShopsCount(){

        String countQuery = "SELECT * FROM"+TABLE_EXOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }
}
