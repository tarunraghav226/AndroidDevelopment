package com.example.myschool.model

data class StudentUser(
    val name: String, val admNo: String, val classOfStudent: String, val rollNo: String,
    val mid1: String, val mid2: String, val final: String,
    val list: List<Subject>
)