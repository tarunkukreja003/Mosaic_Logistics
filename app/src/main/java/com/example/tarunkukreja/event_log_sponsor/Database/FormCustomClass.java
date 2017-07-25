package com.example.tarunkukreja.event_log_sponsor.Database;

/**
 * Created by tarunkukreja on 20/07/17.
 */

public class FormCustomClass {

    String radioButtonText ;
    boolean selected ;


    public FormCustomClass(String radioButtonText) {
        this.radioButtonText = radioButtonText;
    }

    public String getRadioButtonText() {
        return radioButtonText;
    }

    public void setRadioButtonText(String radioButtonText) {
        this.radioButtonText = radioButtonText;
    }

    public boolean isRadioButtonSelected(){
        return true ;
    }

    public boolean notSelected(){
        return false ;
    }

    public void setSelected(boolean selected){
        if(selected) {
            selected = isRadioButtonSelected();
        }
    }
}
