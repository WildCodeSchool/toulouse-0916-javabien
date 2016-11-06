package fr.wildcodeschool.apprenti.javabien;

import android.app.Activity;
import android.content.Context;
import java.util.ArrayList;
import fr.wildcodeschool.apprenti.javabien.Model.Exercice;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

/**
 * ListCategorie contain redirect method that return arrayList of all exercices of the same category
 * this method is needed because when an exercice is launched only this exercice is in putextra
 * ListExoActivity need this arrayList to generate appropriate gridview
 * QuizzFinActivity need also this arrayList to check result of previous exercice and quizz have
 * subcategory wich contain the quizz level
 */
public class ListCategorie extends Activity {
    public static ArrayList<Exercice> redirect(Exercice exercice, Context context) {
        // creating DBHandler
        DBHandler plop = new DBHandler(context);
        ArrayList<Exercice> list2;
        if (exercice.getCategorie().equals("quizz")) {
            // get database list of this category and this subcategory
            list2 = plop.getListQuizz(exercice.getQuizz_categorie(), exercice.getCategorie());
            return list2;
        }else {
            // get list of this category in database
            list2 = plop.getListNiveau(exercice.getCategorie());
            return list2;
        }
    }
}