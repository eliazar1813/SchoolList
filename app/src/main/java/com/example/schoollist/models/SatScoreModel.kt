package com.example.schoollist.models

import com.google.gson.annotations.SerializedName

class SatScoreModel(
    val dbn: String,

    @SerializedName("sat_math_avg_score")
    val sat_Math_Avg: String,

    @SerializedName("sat_critical_reading_avg_score")
    val sat_read_Avg: String,

    @SerializedName("sat_writing_avg_score")
    val sat_write_Avg: String,

    @SerializedName("num_of_sat_test_takers")
    val numberOfTaker: String)