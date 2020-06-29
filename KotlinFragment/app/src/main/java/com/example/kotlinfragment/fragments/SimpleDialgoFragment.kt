package com.example.kotlinfragment.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.kotlinfragment.R

class SimpleDialgoFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)

        val inflater = activity!!.layoutInflater

        val view = inflater.inflate(R.layout.dialog_fragment, null)

        builder.setView(view).setMessage("Your dialog fragment")

        view.findViewById<Button>(R.id.simpleOkBtn).setOnClickListener {
            dismiss()
        }

        return builder.create()
    }
}