package com.example.kotlinhelloworldandintent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username: String? = intent.extras?.get("username").toString()

        //second activity text view
        secondTxtMsg.text = username
    }
}