package com.example.myschool

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val SCREEN_TIMEOUT = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val topToBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.from_top)
        val bottomToTopAnimation = AnimationUtils.loadAnimation(this, R.anim.from_bottom)

        bottomToTopAnimation.duration = 2800
        topToBottomAnimation.duration = 2800

        splashAppName.startAnimation(topToBottomAnimation)
        splashImage.startAnimation(topToBottomAnimation)
        splashLogoLine.startAnimation(bottomToTopAnimation)

        Handler().postDelayed(Runnable {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, SCREEN_TIMEOUT.toLong())
    }
}
