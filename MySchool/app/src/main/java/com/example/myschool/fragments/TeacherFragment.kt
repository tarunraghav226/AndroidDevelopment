package com.example.myschool.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myschool.R
import com.example.myschool.model.TeacherSearchResult
import com.example.myschool.services.AuthenticatedService
import com.example.myschool.services.ServiceBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.teacher_dialog_fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeacherFragment(val teacherID: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.teacher_dialog_fragment, null)

        view.teacherFragmentProgressBar.visibility = View.VISIBLE

        val pref =
            context!!.getSharedPreferences(R.string.app_name.toString(), Context.MODE_PRIVATE)
        val token = pref.getString("token", null)

        val service =
            ServiceBuilder.buildAuthenticatedService(AuthenticatedService::class.java, token!!)
        val reqCall = service.searchTeacher("Teacher", teacherID)

        reqCall.enqueue(object : Callback<TeacherSearchResult> {
            override fun onResponse(
                call: Call<TeacherSearchResult>,
                response: Response<TeacherSearchResult>
            ) {
                view.teacherFragmentProgressBar.visibility = View.INVISIBLE
                if (response.isSuccessful) {
                    val result = response.body()!!.result!!
                    view.teacherID.text = result.id
                    view.teacherName.text = result.name
                    view.classTeacherOf.text = result.classTeacherOf
                    view.imageView3.layoutParams.height = 100
                    view.imageView3.layoutParams.width = 100
                    Picasso.with(context).load(result.teacherImg).into(view.imageView3)
                } else {
                    Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TeacherSearchResult>, t: Throwable) {
                view.teacherFragmentProgressBar.visibility = View.INVISIBLE
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        })

        builder.setMessage("").setView(view)

        view.teacherSearchOKBtn.setOnClickListener { dismiss() }
        return builder.create()
    }
}