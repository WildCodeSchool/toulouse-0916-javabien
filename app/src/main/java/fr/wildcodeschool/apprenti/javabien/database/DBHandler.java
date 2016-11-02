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

public class DBHandler extends SQLiteOpenHelper implements Serializable {
    //nom du fichier sqlite
    public static final String DBNAME = "base_de_donnees.sqlite";
    //endroit où se trouve le fichier
    public static final String DBLOCATION = "/data/data/fr.wildcodeschool.apprenti.javabien/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    //constructeur
    public DBHandler(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //ouvre la bdd
    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);

    }
    //ferme la bdd
    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }
    //méthode pour récupérer toute la bdd
    public List<Contenant> getListContenant() {
        //initialise l'objet qui correspond à une ligne de la bdd
        Contenant contenant = null;
        //arraylist qui récupère l'ensemble des lignes
        ArrayList<Contenant> contenantList = new ArrayList<>();
        //ouvre le flux de bdd
        openDatabase();
        //curseur qui se déplace dans chaque colonne, rawQuery=permet d'utiliser un langage sql
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM base_de_donnees", null);
        //met le curseur au début de la bdd
        cursor.moveToFirst();
        //tant que le curseur n'est pas arrivé à la fin de la bdd
        while (!cursor.isAfterLast()) {
            //remplir le contenant
            contenant = new Contenant(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9)
                    , cursor.getString(10), cursor.getString(11),
                    cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getInt(15));
            //ajoute à l'arraylist
            contenantList.add(contenant);
            //passe à la ligne suivante
            cursor.moveToNext();

        }
        //arrête le curseur
        cursor.close();
        //ferme le flux
        closeDatabase();
        //renvoie l'arraylist
        return contenantList;
    }
    //récupère la liste des exercices d'un niveau entré en paramètre
    public ArrayList<Contenant> getListNiveau(String niveau) {
        Contenant contenant = null;
        ArrayList<Contenant> contenantList = new ArrayList<Contenant>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM base_de_donnees WHERE categorie='" + niveau + "' ORDER BY id_exo", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            contenant = new Contenant(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),
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
    //sauvegarde l'avancement d'un exercice
    public void avancementUpgrade(Contenant contenant, int validation) {
        //activation du mode écriture
        SQLiteDatabase db = this.getWritableDatabase();
        // nouvelle valeur dans la base de donnée
        ContentValues values = new ContentValues();
        values.put("avancement", validation);
        //selection de la ligne et mise à jour de l'avancement de l'exo suivant
        String strSQL = "id_exo = " + (contenant.getId_exos() + 1) + " AND categorie = '" + contenant.getCategorie() + "' AND quizz_categorie = '" + contenant.getQuizz_categorie() + "'";
        db.update("base_de_donnees", values, strSQL, null);
        db.close();
    }

    // base de donnée pour les quizz et récupère la liste des exos du quizz du niveau
    public ArrayList<Contenant> getListQuizz(String quizzCategorie, String categorie) {
        Contenant contenant = null;
        ArrayList<Contenant> contenantList = new ArrayList<Contenant>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM base_de_donnees WHERE quizz_categorie='" + quizzCategorie + "'AND categorie='" + categorie + "' ORDER BY id_exo", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            contenant = new Contenant(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),
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
    //sauvegarde réponse du quizz
    public void avancementQuizz(Contenant contenant, int validation) {
        //activation du mode écriture
        SQLiteDatabase db = this.getWritableDatabase();
        // nouvelle valeur dans la base de données
        ContentValues values = new ContentValues();
        values.put("avancement", validation);
        //selection de la ligne et mise à jour de l'avancement
        String strSQL = "id_exo = " + (contenant.getId_exos()) + " AND categorie = '" + contenant.getCategorie() + "' AND quizz_categorie = '" + contenant.getQuizz_categorie() + "'";
        db.update("base_de_donnees", values, strSQL, null);
        db.close();
    }
    //récupère la liste de sauvegarde de l'avancement des quizz
    public ArrayList<Contenant> getQuizzPass() {
        Contenant contenant = null;
        ArrayList<Contenant> contenantList = new ArrayList<Contenant>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM base_de_donnees WHERE categorie='quizz_valide' ORDER BY id_exo", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            contenant = new Contenant(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),
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