package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

public  class ListCategorie extends Activity {

    public static ArrayList<Contenant> redirect(Contenant exercice, int id,Context context){


        // création d'un DBHandler
        DBHandler plop = new DBHandler(context);

        ArrayList<Contenant> list2 = new ArrayList<Contenant>();

        // si le contenant entré a une valeur égale à débutant
        if(exercice.getCategorie().equals("1")) {

            list2 = plop.getListNiveau(exercice.getCategorie()); // récupération de la base de donnée des débutants
            return list2;

            // sinon si la valeur est quizz
        }else if(exercice.getCategorie().equals("quizz")){

            list2 = plop.getListQuizz(exercice.getQuizz_categorie(),exercice.getCategorie());
            return list2;

        }
return list2;









    }



}