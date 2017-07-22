package com.example.tarunkukreja.event_log_sponsor.Database;

/**
 * Created by deepak on 22/07/17.
 */

public class SaveDetails extends Question {

    public String userkey;


    public SaveDetails() {
        ID = 0;
        question = "";
        category = "";
        option = "";
        userkey = "";
        questionNumber = "";
    }

    public SaveDetails(String question, String category, String userkey, String option, String questionNumber) {
        this.question = question;
        this.category = category;
        this.option = option;
        this.userkey = userkey;
        this.questionNumber = questionNumber;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }
}
