package fr.wildcodeschool.apprenti.javabien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import fr.wildcodeschool.apprenti.javabien.Model.Contenant;

public class ExoVraiActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boutonGauche.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickPop(1,exo);
                    }
                }
        );
        boutonCentre.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               clickPop(2,exo);
                                           }
                                       }
        );
         boutonDroite.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                clickPop(3,exo);
                                            }
                                        }
        );
    }
    public void clickPop(int id, final Contenant exo){
Sauvegarde.sauvegardeExo(exo,getApplicationContext());
        // texte du message complementaire
        if(id==1){
            messageperso();
            textstyle();
            TextView reponseInfo = (TextView)findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.reponseInfo);
            reponseInfo.setText(exo.getInfo_reponse());
        }
        else if (id==2){
            messageperso();
            textstyle();
            TextView reponseInfo = (TextView)findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.reponseInfo);

            reponseInfo.setText(exo.getInfo_reponse2());
        }
        else if (id==3){
            messageperso();
            textstyle();
            TextView reponseInfo = (TextView)findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.reponseInfo);
            reponseInfo.setText(exo.getInfo_reponse3());
        }
        //config du bouton suivant
        Button suivant =(Button)findViewById(R.id.receveur_dinfos_qcm).findViewById(R.id.suivant);
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
