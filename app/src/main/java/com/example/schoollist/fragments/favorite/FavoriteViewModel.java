package com.example.schoollist.fragments.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.schoollist.models.SchoolModel;
import java.util.List;

public class FavoriteViewModel extends ViewModel {

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
