package com.example.tarunkukreja.event_log_sponsor.Database;

/**
 * Created by tarunkukreja on 22/07/17.
 */

public class Question {
    public int ID;
    public String question;
    public String category;

    public String option;
    public String totalQuestions;
    public String questionNumber;

    public Question() {
        ID = 0;
        question = "";
        category = "";

        option = "";
        totalQuestions="";
        questionNumber="";
    }


    public String gettotalQuestions() {
        return totalQuestions;
    }

    public void settotalQuestions(String totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public String getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(String totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Question(String question, String category, String totalQuestions, String option , String questionNumber) {

        this.question = question;
        this.category = category;

        this.option = option;
        this.totalQuestions=totalQuestions;
        this.questionNumber=questionNumber;



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
