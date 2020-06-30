package com.example.kotlinfragment.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.kotlinfragment.R
import com.example.kotlinfragment.activities.ListActivity

class ListDialogFragment(private val posi: Int, private val context: ListActivity) :
    DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater

        val view = inflater.inflate(R.layout.list_dialog_fragment, null)
        builder.setView(view).setMessage("Enter name:")

        view.findViewById<Button>(R.id.listBtnOK).setOnClickListener {
            val name = view.findViewById<EditText>(R.id.listEditTxt).text.toString()
            context.change(posi, name)
            Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        view.findViewById<Button>(R.id.listBtnCancel).setOnClickListener {
            dismiss()
        }
        return builder.create()
    }
}