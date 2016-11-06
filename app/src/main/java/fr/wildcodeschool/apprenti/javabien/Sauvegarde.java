package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import fr.wildcodeschool.apprenti.javabien.Model.Exercice;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

/**
 * Class used to save progress in database column  : avancement
 * Value of 1 (SAVE_TRUE) is used to save progress
 * Value of 0 (SAVE_FALSE) is used to default value
 *
 */
public class Sauvegarde {
    //unlock next exercice. Also used with mainActivity buttons
    public static void sauvegardeExo(Exercice contenant, Context context){
        DBHandler db = new DBHandler(context);
        //DBHandler method next exercice avancement is set to SAVE_TRUE
        db.setNextAvcancement(contenant,Constante.SAVE_TRUE);
    }
    // save wrong answer in quizz
    public static void sauvegardeFaux(Exercice contenant, Context context){
        DBHandler db = new DBHandler(context);
        // DBHandler method set exercice avancement to SAVE_FALSE
        db.avancementQuizz(contenant,Constante.SAVE_FALSE);
    }
    // save right answer in quizz
    public static void sauvegardeJuste(Exercice contenant, Context context){
        DBHandler db = new DBHandler(context);
        // DBHandler method set exercice avancement to SAVE_TRUE
        db.avancementQuizz(contenant,Constante.SAVE_TRUE);
    }
}
