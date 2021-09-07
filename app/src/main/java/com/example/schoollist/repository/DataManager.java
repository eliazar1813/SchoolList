package com.example.schoollist.repository;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.example.schoollist.models.SatScoreModel;
import com.example.schoollist.models.SchoolModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.List;

public class DataManager {

    public static final String TAG = "DataManager Class";

    MutableLiveData<List<SchoolModel>> schoolList;
    MutableLiveData<HashMap<String, SatScoreModel>> satScoreTable;
    SchoolDataService apiService;

    private static DataManager instance;

    public static DataManager getInstance(){
        if(instance == null){
            instance = new DataManager();
        }
        return instance;
    }


    private SchoolDataService initRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://data.cityofnewyork.us/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Retrofit class generates implementation for SchoolDataAPI interface
        return retrofit.create(SchoolDataService.class);
    }

    //----------------------------- School Data retrieval -------------------------------------------------------------//

    public MutableLiveData<List<SchoolModel>> getSchoolData(){
        schoolList = new MutableLiveData<>();
        schoolDataCall();
        return schoolList;
    }

    private void schoolDataCall(){
        //Start retrofit implementation
        if(apiService == null) apiService = initRetrofit();

        Call<List<SchoolModel>> school_Call = apiService.getDataSchool();

        school_Call.enqueue(new Callback<List<SchoolModel>>() {
            @Override
            public void onResponse(Call<List<SchoolModel>> call, Response<List<SchoolModel>> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse " + response.code());
                    return;
                }
                schoolList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<SchoolModel>> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });
    }

    //----------------------------- Sat Scores retrieval ----------------------------------------------------------//

    public MutableLiveData<HashMap<String,SatScoreModel>> getSatScores(){
        satScoreTable = new MutableLiveData<>();
        schoolDataCall();
        return satScoreTable;
    }

    public void satScoresCall(){

        if(apiService == null) apiService = initRetrofit();

        Call<List<SatScoreModel>> sat_Call = apiService.getSatScores();
        sat_Call.enqueue(new Callback<List<SatScoreModel>>() {
            @Override
            public void onResponse(Call<List<SatScoreModel>> call, Response<List<SatScoreModel>> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse " + response.code());
                    return;
                }
                HashMap<String,SatScoreModel> resultResponse = new HashMap<>();
                for(SatScoreModel satScoreModel: response.body()){
                    resultResponse.put(satScoreModel.getDbn(), satScoreModel);
                }
                satScoreTable.setValue(resultResponse);
            }
            @Override
            public void onFailure(Call<List<SatScoreModel>> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });
    }
}
