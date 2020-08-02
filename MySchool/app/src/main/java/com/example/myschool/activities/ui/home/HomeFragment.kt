package com.example.myschool.activities.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myschool.R
import com.example.myschool.adapter.StudentAdapter
import com.example.myschool.model.Student
import com.example.myschool.services.AuthenticatedService
import com.example.myschool.services.ServiceBuilder
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val navView = inflater.inflate(R.layout.nav_header_navigation_drawer, container, false)

        val pref =
            context!!.getSharedPreferences(R.string.app_name.toString(), Context.MODE_PRIVATE)
        val token = pref.getString("token", null)


        lateinit var student: Student

        if (token != null) {
            view.recyclerProgressBar.visibility = View.VISIBLE
            view.homerecyclerView.visibility = View.VISIBLE

            val authService =
                ServiceBuilder.buildAuthenticatedService(AuthenticatedService::class.java, token)
            val reqCall = authService.getUserDetails()

            reqCall.enqueue(object : Callback<Student> {
                override fun onFailure(call: Call<Student>, t: Throwable) {
                    Toast.makeText(context, "Failed to connect", Toast.LENGTH_SHORT)
                    activity!!.finish()
                }

                override fun onResponse(call: Call<Student>, response: Response<Student>) {
                    if (response.isSuccessful) {
                        student = response.body()!!
                        Log.i("info", student.toString())

                        view.recyclerProgressBar.visibility = View.INVISIBLE
                        view.homeProgressBar.visibility = View.INVISIBLE

                        view.homeUserAdmNumTxt.text = student.studentUser!!.admNo
                        view.homeUserClassTxt.text = student.studentUser!!.classOfStudent
                        view.homeUserRollTxt.text = student.studentUser!!.rollNo
                        view.homeUserMid1Txt.text = student.studentUser!!.mid1
                        view.homeUserMid2Txt.text = student.studentUser!!.mid2
                        view.homeUserFinalTxt.text = student.studentUser!!.final

                        val layout = LinearLayoutManager(context)
                        layout.orientation = LinearLayoutManager.VERTICAL

                        view.homerecyclerView.layoutManager = layout
                        view.homerecyclerView.adapter =
                            StudentAdapter(context, student.studentUser!!.list!!)
                    } else {
                        Toast.makeText(context, "Some Error Occurred", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            activity!!.finish()
        }

        return view
    }
}