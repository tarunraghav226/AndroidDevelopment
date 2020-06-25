package com.example.kotlinhelloworldandintent

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        //main activity button
        mainBtnSwtchAct.setOnClickListener {
            val username: String = mainEditTxtUsername.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }

        mainBtnShareOut.setOnClickListener {
            val username: String = mainEditTxtUsername.text.toString()
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.type = "plain/text"
            intent.putExtra(Intent.EXTRA_TEXT, username)
            startActivity(Intent.createChooser(intent, "Select:"))
        }

        mainBtnRecy.setOnClickListener {
            val intent = Intent(this, RecyclerActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
