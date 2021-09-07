package com.example.schoollist.fragments.accepted;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.schoollist.models.SchoolModel;

import java.util.ArrayList;
import java.util.List;

public class AcceptedViewModel extends ViewModel {

    private MutableLiveData<List<SchoolModel>> schoolList;

    private static AcceptedViewModel instance;

    public static AcceptedViewModel getInstance(){
        if(instance == null){
            instance = new AcceptedViewModel();
        }

        return instance;
    }

    public void init(){
        if(schoolList != null) return;
        schoolList = new MutableLiveData<>();
    }

    public boolean addToList(SchoolModel currentSchool){
        if(schoolList.getValue() == null){
            List<SchoolModel> currentList = new ArrayList<>();
            currentList.add(currentSchool);
            schoolList.setValue(currentList);
        }else{
            if(schoolList.getValue().contains(currentSchool)) return false;
            schoolList.getValue().add(currentSchool);
        }

        return true;
    }

    public LiveData<List<SchoolModel>> getSchoolList(){
        return schoolList;
    }
}
