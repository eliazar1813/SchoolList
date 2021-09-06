package com.example.schoollist.fragments.accepted;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.schoollist.models.SchoolModel;

import java.util.List;

public class AcceptedViewModel extends ViewModel {

    private MutableLiveData<List<SchoolModel>> schoolList;

    public void init(){
        if(schoolList != null) return;
        schoolList = new MutableLiveData<>();
    }

    public void addToList(SchoolModel currentSchool){
        schoolList.getValue().add(currentSchool);
    }

    public LiveData<List<SchoolModel>> getSchoolList(){
        return schoolList;
    }
}
