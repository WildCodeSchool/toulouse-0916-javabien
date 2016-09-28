package fr.wildcodeschool.apprenti.javabien.Model;

import java.io.Serializable;

/**
 * Created by tuffery on 24/09/16.
 */

public class Contenant implements Serializable {
    private String categorie;
    private int id_exos;
    private String cours;
    private String question;
    private String propositon;
    private String proposition2;
    private String proposition3;
    private String reponse;
    private int avancement;
    private String exoType;
    private String exonom;

    public Contenant(){

    }
    public Contenant(String categorie, int id_exos, String cours, String question, String propositon,
                     String proposition2, String proposition3, String reponse,String exoType, String exonom, int avancement) {

        this.categorie = categorie;
        this.id_exos = id_exos;
        this.cours = cours;
        this.question = question;
        this.propositon = propositon;
        this.proposition2 = proposition2;
        this.proposition3 = proposition3;
        this.reponse = reponse;
        this.avancement = avancement;
        this.exoType =exoType;
        this.exonom = exonom;
    }


    public String getCategorie() {
        return categorie;
    }

    public String getExoType() {
        return exoType;
    }

    public void setExoType(String exoType) {
        this.exoType = exoType;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getId_exos() {
        return id_exos;
    }

    public void setId_exos(int id_exos) {
        this.id_exos = id_exos;
    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPropositon() {
        return propositon;
    }

    public void setPropositon(String propositon) {
        this.propositon = propositon;
    }

    public String getProposition2() {
        return proposition2;
    }

    public void setProposition2(String proposition2) {
        this.proposition2 = proposition2;
    }

    public String getProposition3() {
        return proposition3;
    }

    public void setProposition3(String proposition3) {
        this.proposition3 = proposition3;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public int getAvancement() {
        return avancement;
    }

    public void setAvancement(int avancement) {
        this.avancement = avancement;
    }

    public String getExonom() {
        return exonom;
    }

    public void setExonom(String exonom) {
        this.exonom = exonom;
    }
}
