package com.example.myschool.model

import com.google.gson.annotations.SerializedName

data class Teacher(
    @SerializedName("teacher_id")
    val id: String? = null,

    @SerializedName("teacher_name")
    val name: String? = null,

    @SerializedName("class_teacher_of")
    val classTeacherOf: String? = null,

    @SerializedName("teacher_image")
    val teacherImg: String? = null
)

data class TeacherSearchResult(
    @SerializedName("result")
    val result: Teacher?
)