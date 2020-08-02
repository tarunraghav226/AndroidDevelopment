package com.example.myschool.activities

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myschool.R
import com.example.myschool.model.Student
import com.example.myschool.services.AuthenticatedService
import com.example.myschool.services.ServiceBuilder
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NavigationDrawerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        val pref = applicationContext.getSharedPreferences(
            R.string.app_name.toString(),
            Context.MODE_PRIVATE
        )
        val token = pref.getString("token", null)

        val service =
            ServiceBuilder.buildAuthenticatedService(AuthenticatedService::class.java, token!!)
        val reqCall = service.getUserDetails()

        reqCall.enqueue(object : Callback<Student> {
            override fun onResponse(call: Call<Student>, response: Response<Student>) {
                if (response.isSuccessful) {
                    val student = response.body()!!.studentUser
                    val headerView = findViewById<NavigationView>(R.id.nav_view).getHeaderView(0)
                    headerView.findViewById<TextView>(R.id.navStudentName).text = student!!.name
                    val imageView = headerView.findViewById<ImageView>(R.id.navStudentImg)
                    imageView.layoutParams.height = 100
                    imageView.layoutParams.width = 100
                    Picasso.with(applicationContext).load(student.studImg).into(imageView)
                } else {
                    Toast.makeText(applicationContext, "Something Went Wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Student>, t: Throwable) {
                Toast.makeText(applicationContext, "Something Went Wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        })


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_search, R.id.nav_logout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
