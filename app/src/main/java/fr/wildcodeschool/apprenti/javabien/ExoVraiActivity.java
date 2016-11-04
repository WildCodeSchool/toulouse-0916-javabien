package fr.wildcodeschool.apprenti.javabien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class ExoVraiActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // left button
        boutonGauche.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickPop(1,exo);
                    }
                }
        );
        //middle button
        boutonCentre.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               clickPop(2,exo);
                                           }
                                       }
        );
        //right button
         boutonDroite.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                clickPop(3,exo);
                                            }
                                        }
        );
    }
    // show answer information corresponding to id of button
    public void clickPop(int id, final Contenant exo){
Sauvegarde.sauvegardeExo(exo,getApplicationContext());
        // texte du message complementaire
        if(id==1){
            messageperso();
            textstyle();
            reponseInfo.setText(exo.getInfo_reponse());
        }
        else if (id==2){
            messageperso();
            textstyle();
            reponseInfo.setText(exo.getInfo_reponse2());
        }
        else if (id==3){
            messageperso();
            textstyle();
            reponseInfo.setText(exo.getInfo_reponse3());
        }
        //config du bouton suivant
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adios = new Intent();
                adios.putExtra("listExercices",ListCategorie.redirect(exo, getApplicationContext()));
                setResult(1,adios);
                finish();
            }
        });
    }


}
