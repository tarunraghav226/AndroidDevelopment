package com.example.myschool.activities.ui.logout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myschool.R
import com.example.myschool.activities.LoginActivity

class Logout : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val pref =
            context!!.getSharedPreferences(R.string.app_name.toString(), Context.MODE_PRIVATE)
        pref.edit().remove("token").apply()

        val intent = Intent(activity!!, LoginActivity::class.java)
        startActivity(intent)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}