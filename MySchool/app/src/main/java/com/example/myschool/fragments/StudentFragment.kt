package com.example.myschool.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.myschool.R

class StudentFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.student_dialog_fragment, null)
        builder.setMessage("").setView(view)

        view.findViewById<Button>(R.id.fragOKBtn).setOnClickListener {
            dismiss()
        }

        return builder.create()
    }
}