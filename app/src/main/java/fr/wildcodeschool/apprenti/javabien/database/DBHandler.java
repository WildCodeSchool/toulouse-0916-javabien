package fr.wildcodeschool.apprenti.javabien.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class DBHandler extends SQLiteOpenHelper implements Serializable{

    public static final String DBNAME = "base_de_donnees";
    public static final String DBLOCATION ="/data/data/fr.wildcodeschool.apprenti.javabien/databases/";
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
       // String[] jeanjose ={"categorie","id_exo","cours","question","proposition","proposition2","proposition3","reponse","avancement","exo_type","exo_nom"};

        Contenant contenant = null;
        ArrayList<Contenant> contenantList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM base_de_donnees",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            contenant = new Contenant(cursor.getString(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getString(6),
                    cursor.getString(7),cursor.getString(8),cursor.getString(9), cursor.getInt(10));
            contenantList.add(contenant);
            cursor.moveToNext();

        }
        cursor.close();
        closeDatabase();
        return contenantList;
    }
    public ArrayList<Contenant> getListDebutant(){
        Contenant contenant = null;
        ArrayList<Contenant> contenantList = new ArrayList<Contenant>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM base_de_donnees WHERE categorie='debutant' ORDER BY id_exo",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            contenant = new Contenant(cursor.getString(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getString(6),
                    cursor.getString(7),cursor.getString(8),cursor.getString(9), cursor.getInt(10));
            contenantList.add(contenant);
            cursor.moveToNext();

        }
        cursor.close();
        closeDatabase();
        return contenantList;
    }

    public void avancementUpgrade(Contenant contenant) {
        //activation du mode écriture
        SQLiteDatabase db = this.getWritableDatabase();
        // nouvelle valeur dans la base de donnée
        ContentValues values = new ContentValues();
        values.put("avancement", 1);


        //selection de la ligne et mise à jour

        String strSQL = "id_exo = "+(contenant.getId_exos()+1)+" AND categorie = '"+contenant.getCategorie()+"' ";

            db.update(DBNAME,values,strSQL,null);


        db.close();
    }

}