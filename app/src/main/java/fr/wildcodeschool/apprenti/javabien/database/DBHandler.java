package fr.wildcodeschool.apprenti.javabien.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;
import fr.wildcodeschool.apprenti.javabien.TItem;

public class DBHandler extends SQLiteOpenHelper{

    public static final String DBNAME = "base_de_donnees.sqlite";
    public static final String DBLOCATION ="/data/data/com.fr.wildcodeschool.apprenti.javabien/databases";
    private Context mContext;
    private  SQLiteDatabase mDatabase;

    public  DBHandler(Context context){
        super(context,DBNAME,null,1);
        this.mContext =context;


    }
    @Override
    public void onCreate(SQLiteDatabase db){

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){

    }

    public void openDatabase(){
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase !=null && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);

    }

    public void closeDatabase(){
        if (mDatabase!=null) {
            mDatabase.close();
        }
    }
    public List<Contenant> getListContenant(){

        Contenant contenant = null;
        List<Contenant> contenantList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM BASE_DE_DONNEES",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            contenant = new Contenant(cursor.getString(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getString(6),
                    cursor.getString(7),cursor.getInt(8),cursor.getString(9));
            contenantList.add(contenant);
            cursor.moveToNext();

        }
        cursor.close();
        closeDatabase();
        return contenantList;
    }
    public List<Contenant> getListDebutant(){

        Contenant contenant = null;
        List<Contenant> contenantList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM BASE_DE_DONNEES WHERE categorie = debutant",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            contenant = new Contenant(cursor.getString(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getString(6),
                    cursor.getString(7),cursor.getInt(8),cursor.getString(9));
            contenantList.add(contenant);
            cursor.moveToNext();

        }
        cursor.close();
        closeDatabase();
        return contenantList;
    }
}