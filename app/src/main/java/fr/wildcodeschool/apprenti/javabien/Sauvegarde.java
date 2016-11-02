package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;
import fr.wildcodeschool.apprenti.javabien.Model.Contenant;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

public class Sauvegarde {
    //methode de sauvegarde de l'avancement à 1
    public static void sauvegardeExo(Contenant contenant, Context context){
        DBHandler db = new DBHandler(context);
        db.getListContenant();
        db.avancementUpgrade(contenant,1);
    }
    // methode de sauvegarde de l'avancement à 0 pour le quizz
    public static void sauvegardeFaux(Contenant contenant, Context context){
        DBHandler db = new DBHandler(context);
        db.getListContenant();
        db.avancementQuizz(contenant,0);
    }
    // methode de sauvegarde de l'avancement à 1 pour le quizz
    public static void sauvegardeJuste(Contenant contenant, Context context){
        DBHandler db = new DBHandler(context);
        db.getListContenant();
        db.avancementQuizz(contenant,1);
    }
}
