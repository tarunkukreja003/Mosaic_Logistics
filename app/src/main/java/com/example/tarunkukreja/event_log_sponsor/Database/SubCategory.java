package com.example.tarunkukreja.event_log_sponsor.Database;

/**
 * Created by deepak on 26/07/17.
 */

public class SubCategory {

    public int ID;
    public String question;
    public String category;

    public String option;



    public SubCategory() {
        ID = 0;
        question = "";
        category = "";

        option = "";


    }



    public SubCategory(String question, String category, String option ) {

        this.question = question;
        this.category = category;

        this.option = option;



    }

    public SubCategory(String question, String option) {
        this.question = question ;
        this.option = option;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
