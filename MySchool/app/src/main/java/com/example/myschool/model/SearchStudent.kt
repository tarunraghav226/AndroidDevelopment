package com.example.myschool.model

import com.google.gson.annotations.SerializedName

data class SearchStudent(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("admission_number")
    val admNo: String? = null,

    @SerializedName("class_of_student")
    val classOfStudent: String? = null,

    @SerializedName("roll_number")
    val rollNo: String? = null,

    @SerializedName("percentage_of_mid1")
    val mid1: String? = null,

    @SerializedName("percentage_of_mid2")
    val mid2: String? = null,

    @SerializedName("percentage_of_final")
    val final: String? = null,

    @SerializedName("student_image")
    val studImg: String? = null
)

data class StudentSearchResult(
    @SerializedName("result")
    val result: SearchStudent? = null
)