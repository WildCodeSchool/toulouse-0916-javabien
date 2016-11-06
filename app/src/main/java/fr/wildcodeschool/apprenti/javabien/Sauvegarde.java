package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import fr.wildcodeschool.apprenti.javabien.Model.Exercice;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

public class Sauvegarde {
    //methode de sauvegarde de l'avancement à 1
    public static void sauvegardeExo(Exercice contenant, Context context){
        DBHandler db = new DBHandler(context);
        db.getListContenant();
        db.avancementUpgrade(contenant,Constante.SAVE_TRUE);
    }
    // methode de sauvegarde de l'avancement à 0 pour le quizz
    public static void sauvegardeFaux(Exercice contenant, Context context){
        DBHandler db = new DBHandler(context);
        db.getListContenant();
        db.avancementQuizz(contenant,Constante.SAVE_FALSE);
    }
    // methode de sauvegarde de l'avancement à 1 pour le quizz
    public static void sauvegardeJuste(Exercice contenant, Context context){
        DBHandler db = new DBHandler(context);
        db.getListContenant();
        db.avancementQuizz(contenant,Constante.SAVE_TRUE);
    }
}
