package com.example.myschool.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myschool.R
import com.example.myschool.model.UserAuthenticationToken
import com.example.myschool.services.SchoolService
import com.example.myschool.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        logInProgressBar.visibility = View.INVISIBLE
        loginButton.setOnClickListener {
            logInProgressBar.visibility = View.VISIBLE;
            val studentService = ServiceBuilder.buildService(SchoolService::class.java)
            val reqCall = studentService.authorizeUser(
                loginUserID.text.toString(),
                loginPassword.text.toString()
            )

            reqCall.enqueue(object : Callback<UserAuthenticationToken> {
                override fun onResponse(
                    call: Call<UserAuthenticationToken>,
                    response: Response<UserAuthenticationToken>
                ) {
                    logInProgressBar.visibility = View.INVISIBLE;
                    if (response.isSuccessful) {
                        val intent =
                            Intent(applicationContext, NavigationDrawerActivity::class.java)
                        intent.putExtra("token", response.body()!!.token)
                        Log.i("info", response.body()!!.token)
                        Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Wrong Username or Password",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }

                override fun onFailure(call: Call<UserAuthenticationToken>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Error Occurred", Toast.LENGTH_SHORT).show()
                }
            })


        }
    }
}