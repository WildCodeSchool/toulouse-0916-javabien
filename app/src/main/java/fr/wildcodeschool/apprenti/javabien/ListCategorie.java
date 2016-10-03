package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

public  class ListCategorie extends Activity {

    public static ArrayList<Contenant> redirect(Contenant exercice, int id,Context context){



        DBHandler plop = new DBHandler(context);

        ArrayList<Contenant> list2 = new ArrayList<Contenant>();
        if(exercice.getCategorie().equals("debutant")) {

            list2 = plop.getListDebutant();
            return list2;
        }
return list2;









    }
}