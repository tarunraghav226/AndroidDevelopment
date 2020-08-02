package com.example.myschool.model

import com.google.gson.annotations.SerializedName

data class Subject(

    @SerializedName("subject_code")
    val subjectCode: String? = null,

    @SerializedName("mid1")
    val mid1: String? = null,

    @SerializedName("mid2")
    val mid2: String? = null,

    @SerializedName("final")
    val final: String? = null,

    @SerializedName("subject_teacher")
    val subjectTeacher: String? = null
)