package com.example.myschool.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.myschool.R
import kotlinx.android.synthetic.main.teacher_dialog_fragment.view.*

class TeacherFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.teacher_dialog_fragment, null)

        builder.setMessage("").setView(view)

        view.teacherSearchOKBtn.setOnClickListener { dismiss() }
        return builder.create()
    }
}