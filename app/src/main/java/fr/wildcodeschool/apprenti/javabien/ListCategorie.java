package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Context;
import java.util.ArrayList;
import fr.wildcodeschool.apprenti.javabien.Model.Contenant;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

public class ListCategorie extends Activity {
    public static ArrayList<Contenant> redirect(Contenant exercice, Context context) {
        // création d'un DBHandler
        DBHandler plop = new DBHandler(context);
        ArrayList<Contenant> list2;
        // si la valeur est quizz
        if (exercice.getCategorie().equals("quizz")) {
            // récupération de la base de donnée par catégorie et quizz catégorie
            list2 = plop.getListQuizz(exercice.getQuizz_categorie(), exercice.getCategorie());
            return list2;
         // sinon le contenant entré a une valeur égale à sa catégorie
        }else {
            // récupération de la base de donnée par catégorie
            list2 = plop.getListNiveau(exercice.getCategorie());
            return list2;
        }
    }
}