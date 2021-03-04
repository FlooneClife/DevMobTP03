package com.example.devmobtp03;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlanningModel extends ViewModel {

    private String h8_10 = "Rencontre client Dupont";
    private String h10_12 = "Travailler le dossier recrutement";
    private String h14_16 = "Réunion équipe";
    private String h16_18 = "Préparer dossier vente";

    private String h8_10_2 = "Terminer dossier vente";
    private String h10_12_2 = "Terminer dossier recrutement";
    private String h14_16_2 = "Réunion équipe";

    private MutableLiveData<String> task;

    public MutableLiveData<String> getTask() {
        if(task == null)
            task = new MutableLiveData<>();
        return task;
    }

    public String getH8_10() {
        return h8_10;
    }

    public String getH10_12() {
        return h10_12;
    }

    public String getH14_16() {
        return h14_16;
    }

    public String getH16_18() {
        return h16_18;
    }

    public String getH8_10_2() {
        return h8_10_2;
    }

    public String getH10_12_2() {
        return h10_12_2;
    }

    public String getH14_16_2() {
        return h14_16_2;
    }
}
