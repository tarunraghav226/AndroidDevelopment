package com.example.myschool.model

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("student")
    val studentUser: StudentUser? = null
)