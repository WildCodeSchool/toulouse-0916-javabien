package fr.wildcodeschool.apprenti.javabien.Model;

import java.io.Serializable;

public class Exercice implements Serializable {
    private String categorie;
    private int id_exos;
    private String cours;
    private String question;
    private String propositon;
    private String proposition2;
    private String proposition3;
    private String proposition4;
    private String info_reponse;
    private String info_reponse2;
    private String info_reponse3;
    private String reponse;
    private int avancement;
    private String exoType;
    private String exoNom;
    private String quizz_categorie;

    //constructeur vide
    public Exercice(){

    }

    // constructor to show only one quizz bouton in listExoActivity
    public Exercice(String categorie, String quizz_categorie, int id_exos, String exoNom, String exoType, int avancement) {
        this.categorie = categorie;
        this.quizz_categorie = quizz_categorie;
        this.id_exos = id_exos;
        this.avancement = avancement;
        this.exoNom = exoNom;
        this.exoType = exoType;
    }

    // constructor used to create an end quizz object

    public Exercice(String categorie, String quizz_categorie, int id_exos, String exoType, int avancement) {
        this.categorie = categorie;
        this.quizz_categorie = quizz_categorie;
        this.id_exos = id_exos;
        this.avancement = avancement;
        this.exoType =exoType;
    }
    // constructor used to create final quizz validation object

    public Exercice(String categorie, String quizz_categorie, int id_exos, int avancement) {
        this.categorie = categorie;
        this.quizz_categorie = quizz_categorie;
        this.id_exos = id_exos;
        this.avancement = avancement;
        this.exoType =exoType;
    }

    //constructeur avec les infos d'une ligne de la bdd
    public Exercice(String categorie, String quizz_categorie, int id_exos, String cours, String question, String propositon,
                    String proposition2, String proposition3, String proposition4, String info_reponse, String info_reponse2, String info_reponse3, String reponse, String exoType, String exoNom, int avancement) {

        this.categorie = categorie;
        this.id_exos = id_exos;
        this.cours = cours;
        this.question = question;
        this.propositon = propositon;
        this.proposition2 = proposition2;
        this.proposition3 = proposition3;
        this.proposition4 = proposition4;
        this.info_reponse = info_reponse;
        this.info_reponse2 = info_reponse2;
        this.info_reponse3 = info_reponse3;
        this.reponse = reponse;
        this.avancement = avancement;
        this.exoType =exoType;
        this.exoNom = exoNom;
        this.quizz_categorie = quizz_categorie;
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

    public String getProposition4() {
        return proposition4;
    }

    public void setProposition4(String proposition4) {
        this.proposition4 = proposition4;
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
        return exoNom;
    }

    public void setExonom(String exonom) {
        this.exoNom = exonom;
    }

    public String getQuizz_categorie() {
        return quizz_categorie;
    }

    public String getInfo_reponse() {
        return info_reponse;
    }

    public void setInfo_reponse(String info_reponse) {
        this.info_reponse = info_reponse;
    }

    public String getInfo_reponse2() {
        return info_reponse2;
    }

    public void setInfo_reponse2(String info_reponse2) {
        this.info_reponse2 = info_reponse2;
    }

    public String getInfo_reponse3() {
        return info_reponse3;
    }

    public void setInfo_reponse3(String info_reponse3) {
        this.info_reponse3 = info_reponse3;
    }

    public void setQuizz_categorie(String quizz_categorie) {
        this.quizz_categorie = quizz_categorie;
    }
}
