package com.example.myschool.activities.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.myschool.R
import com.example.myschool.fragments.StudentFragment
import com.example.myschool.fragments.TeacherFragment
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var search_whom: String = ""

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        search_whom = parent?.getItemAtPosition(position).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, null)

        view.searchSpinner.onItemSelectedListener = this

        view.searchButton.setOnClickListener {
            when (search_whom) {
                "Student" -> {
                    val studentDialogFragment = StudentFragment()
                    studentDialogFragment.show(activity!!.supportFragmentManager, "")
                }
                "Teacher" -> {
                    val teacherDialogFragment = TeacherFragment()
                    teacherDialogFragment.show(activity!!.supportFragmentManager, "")
                }
                else -> {
                    Log.i("Error", search_whom)
                }
            }
        }
        return view
    }
}