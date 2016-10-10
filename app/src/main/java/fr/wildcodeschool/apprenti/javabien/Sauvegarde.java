package fr.wildcodeschool.apprenti.javabien;

import android.content.Context;

import fr.wildcodeschool.apprenti.javabien.Model.Contenant;
import fr.wildcodeschool.apprenti.javabien.database.DBHandler;

/**
 * Created by tuffery on 04/10/16.
 */

public class Sauvegarde {
    //methode de sauvegarde de l'avancement

    public static void sauvegardeExo(Contenant contenant, Context context){

        DBHandler db = new DBHandler(context);
        db.getListContenant();

        db.avancementUpgrade(contenant);




    }
}
