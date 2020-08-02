package com.example.myschool.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myschool.R
import com.example.myschool.model.StudentSearchResult
import com.example.myschool.services.AuthenticatedService
import com.example.myschool.services.ServiceBuilder
import kotlinx.android.synthetic.main.student_dialog_fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentFragment(val stdID: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.student_dialog_fragment, null)


        val pref =
            activity!!.getSharedPreferences(R.string.app_name.toString(), Context.MODE_PRIVATE)
        val token = pref.getString("token", null)
        val service =
            ServiceBuilder.buildAuthenticatedService(AuthenticatedService::class.java, token!!)

        val reqCall = service.searchStudent("Student", stdID)

        view.studentDialogProgressBar.visibility = View.VISIBLE

        reqCall.enqueue(object : Callback<StudentSearchResult> {
            override fun onFailure(call: Call<StudentSearchResult>, t: Throwable) {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                view.studentDialogProgressBar.visibility = View.INVISIBLE
            }

            override fun onResponse(
                call: Call<StudentSearchResult>,
                response: Response<StudentSearchResult>
            ) {
                view.studentDialogProgressBar.visibility = View.INVISIBLE
                if (response.isSuccessful) {
                    val result = response.body()!!.result!!

                    view.studAdmNum.text = result.admNo
                    view.studName.text = result.name
                    view.studClass.text = result.classOfStudent
                    view.studMid1.text = result.mid1
                    view.studMid2.text = result.mid2
                    view.studMidFinal.text = result.final
                    view.studRollNum.text = result.rollNo


                } else {
                    Toast.makeText(context, "Wrong credentials", Toast.LENGTH_SHORT).show()
                }
            }
        })

        builder.setMessage("").setView(view)

        view.findViewById<Button>(R.id.fragOKBtn).setOnClickListener {
            dismiss()
        }

        return builder.create()
    }
}