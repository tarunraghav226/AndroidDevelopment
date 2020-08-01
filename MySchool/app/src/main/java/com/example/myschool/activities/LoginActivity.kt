package com.example.myschool.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myschool.R
import com.example.myschool.model.UserAuthenticationToken
import com.example.myschool.services.SchoolService
import com.example.myschool.services.ServiceBuilder
import com.example.myschool.services.TokenPreference
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
            loginUserID.isEnabled = false
            loginPassword.isEnabled = false
            loginButton.isEnabled = false
            logInProgressBar.visibility = View.VISIBLE;
            val studentService = ServiceBuilder.buildService(SchoolService::class.java)
            val reqCall = studentService.authorizeUser(
                loginUserID.text.toString(),
                loginPassword.text.toString()
            )

            reqCall.enqueue(object : Callback<UserAuthenticationToken> {

                //function called when a request is successfully made to a server
                override fun onResponse(
                    call: Call<UserAuthenticationToken>,
                    response: Response<UserAuthenticationToken>
                ) {
                    //setting visibility of progress bar
                    logInProgressBar.visibility = View.INVISIBLE

                    //checking response status
                    if (response.isSuccessful) {
                        val intent =
                            Intent(applicationContext, NavigationDrawerActivity::class.java)
                        val tokenPref = TokenPreference(applicationContext)
                        tokenPref.setToken(response.body()!!.token)
                        Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(intent)

                        finish()
                    } else {
                        loginButton.isEnabled = true
                        loginUserID.isEnabled = true
                        loginPassword.isEnabled = true
                        Toast.makeText(
                            this@LoginActivity,
                            "Wrong Username or Password",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }

                //function is called when some problem occurred during request
                override fun onFailure(call: Call<UserAuthenticationToken>, t: Throwable) {
                    loginButton.isEnabled = true
                    loginUserID.isEnabled = true
                    loginPassword.isEnabled = true
                    logInProgressBar.visibility = View.INVISIBLE
                    Toast.makeText(this@LoginActivity, "Error Occurred", Toast.LENGTH_SHORT).show()
                }
            })


        }
    }
}