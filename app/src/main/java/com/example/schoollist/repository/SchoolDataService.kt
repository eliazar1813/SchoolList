package com.example.schoollist.repository

import com.example.schoollist.models.SatScoreModel
import com.example.schoollist.models.SchoolModel
import retrofit2.Call
import retrofit2.http.GET

interface SchoolDataService {

    @GET("s3k6-pzi2.json")
    fun getDataSchool(): Call<List<SchoolModel>>

    @GET("f9bf-2cp4.json")
    fun getSatScores(): Call<List<SatScoreModel>>
}