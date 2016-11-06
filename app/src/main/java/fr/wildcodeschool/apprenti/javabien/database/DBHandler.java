package fr.wildcodeschool.apprenti.javabien.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.wildcodeschool.apprenti.javabien.Constante;
import fr.wildcodeschool.apprenti.javabien.Model.Exercice;

public class DBHandler extends SQLiteOpenHelper implements Serializable {
    private Context mContext;
    private SQLiteDatabase mDatabase;

    /**
     * Class to communicate with database
     */
    public DBHandler(Context context) {
        super(context, Constante.DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(Constante.DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);

    }
    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    //get complete list of one category (entered in parameter)
    public ArrayList<Exercice> getListNiveau(String niveau) {
        //init model
        Exercice exo = null;
        ArrayList<Exercice> ExerciceList = new ArrayList<>();
        openDatabase();
        // init rawQuery in cursor
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM base_de_donnees WHERE categorie='" + niveau + "' ORDER BY id_exo", null);
        // read database with cursor
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            exo = new Exercice(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9)
                    , cursor.getString(10), cursor.getString(11),
                    cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getInt(15));
            ExerciceList.add(exo);
            cursor.moveToNext();

        }
        cursor.close();
        closeDatabase();
        return ExerciceList;
    }
    //save exercice
    public void setNextAvcancement(Exercice exo, int validation) {
        //activate writing
        SQLiteDatabase db = this.getWritableDatabase();
        // new data value
        ContentValues values = new ContentValues();
        values.put("avancement", validation);
        //select line and update
        String strSQL = "id_exo = " + (exo.getId_exos() + 1) + " AND categorie = '" + exo.getCategorie() + "' AND quizz_categorie = '" + exo.getQuizz_categorie() + "'";
        db.update("base_de_donnees", values, strSQL, null);
        db.close();
    }

    // get complete list of a subcategory
    public ArrayList<Exercice> getListQuizz(String souscategorie, String categorie) {
        Exercice exercice = null;
        ArrayList<Exercice> contenantList = new ArrayList<Exercice>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM base_de_donnees WHERE quizz_categorie='" + souscategorie + "'AND categorie='" + categorie + "' ORDER BY id_exo", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            exercice = new Exercice(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9)
                    , cursor.getString(10), cursor.getString(11),
                    cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getInt(15));
            contenantList.add(exercice);
            cursor.moveToNext();

        }
        cursor.close();
        closeDatabase();
        return contenantList;
    }
    //save current exercice used for quizz parameter validation is SAVE_TRUE or SAVE_FALSE
    public void avancementQuizz(Exercice exo, int validation) {
        //activate writing
        SQLiteDatabase db = this.getWritableDatabase();
        // new data value
        ContentValues values = new ContentValues();
        values.put("avancement", validation);
        //select line and update
        String strSQL = "id_exo = " + (exo.getId_exos()) + " AND categorie = '" + exo.getCategorie() + "' AND quizz_categorie = '" + exo.getQuizz_categorie() + "'";
        db.update("base_de_donnees", values, strSQL, null);
        db.close();
    }
    //get list with Level quizz status (passed or not)
    public ArrayList<Exercice> getQuizzPass() {
        Exercice contenant = null;
        ArrayList<Exercice> contenantList = new ArrayList<Exercice>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM base_de_donnees WHERE categorie='quizz_valide' ORDER BY id_exo", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            contenant = new Exercice(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9)
                    , cursor.getString(10), cursor.getString(11),
                    cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getInt(15));
            contenantList.add(contenant);
            cursor.moveToNext();

        }
        cursor.close();
        closeDatabase();
        return contenantList;

    }
}