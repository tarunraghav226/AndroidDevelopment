package com.example.kotlinfragment.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinfragment.R
import com.example.kotlinfragment.fragments.SimpleDialgoFragment
import kotlinx.android.synthetic.main.simple_activity.*

class SimpleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_activity)

        simpleBtn.setOnClickListener {
            val simpleDialgoFragment = SimpleDialgoFragment()
            simpleDialgoFragment.show(supportFragmentManager, "")
        }

    }
}