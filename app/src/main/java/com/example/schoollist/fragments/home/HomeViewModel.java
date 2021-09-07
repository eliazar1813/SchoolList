package com.example.schoollist.fragments.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.schoollist.models.SatScoreModel;
import com.example.schoollist.models.SchoolModel;
import com.example.schoollist.repository.DataManager;

import java.util.HashMap;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<SchoolModel>> schoolList;

    private DataManager dataManager;

    public void init(){
        if(schoolList != null){
            return;
        }
        dataManager = DataManager.getInstance();
        schoolList = dataManager.getSchoolData();
    }

    public LiveData<List<SchoolModel>> getSchoolList(){
        return schoolList;
    }
}
