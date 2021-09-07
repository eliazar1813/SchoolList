package com.example.schoollist.fragments.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.schoollist.models.SatScoreModel;
import com.example.schoollist.models.SchoolModel;
import com.example.schoollist.repository.DataManager;

import java.util.HashMap;

public class DetailsViewModel extends ViewModel {

    private SchoolModel currentSchool;
    private MutableLiveData<HashMap<String,SatScoreModel>> satScoreTable;
    private DataManager dataManager;
    private static DetailsViewModel instance;

    public static DetailsViewModel getInstance(){
        if(instance == null){
            instance = new DetailsViewModel();
        }

        return instance;
    }

    public void init(SchoolModel currentSchool){
        this.currentSchool = currentSchool;
        dataManager = DataManager.getInstance();
        satScoreTable = dataManager.getSatScores();
    }

    public LiveData<HashMap<String,SatScoreModel>> getSatScores(){
        return satScoreTable;
    }

    public SchoolModel getCurrentSchool(){
        return currentSchool;
    }


}
