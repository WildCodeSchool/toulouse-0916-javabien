package fr.wildcodeschool.apprenti.javabien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import fr.wildcodeschool.apprenti.javabien.Model.Exercice;

public class ExoVraiActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // left button
        this.boutonGauche.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickPop(1,exo);
                    }
                }
        );
        //middle button
        this.boutonCentre.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               clickPop(2,exo);
                                           }
                                       }
        );
        //right button
        this.boutonDroite.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                clickPop(3,exo);
                                            }
                                        }
        );
    }
    // show answer information corresponding to id of button
    public void clickPop(int id, final Exercice exo){
    Sauvegarde.sauvegardeExo(exo,getApplicationContext());
        // show exercice info
        if(id==1){
            setInfoMessage();
            reponseInfo.setText(exo.getInfo_reponse());
        }
        else if (id==2){
            setInfoMessage();
            reponseInfo.setText(exo.getInfo_reponse2());
        }
        else if (id==3){
            setInfoMessage();
            reponseInfo.setText(exo.getInfo_reponse3());
        }
        //next button
        this.suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adios = new Intent();
                setResult(1,adios);
                finish();
            }
        });
    }


}
