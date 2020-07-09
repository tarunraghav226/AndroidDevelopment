package com.example.myschool.activities.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myschool.R
import com.example.myschool.adapter.StudentAdapter
import com.example.myschool.model.StudentUser
import com.example.myschool.model.Subject
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        val listOfSub = MutableList<Subject>(0) { Subject("1SDF32", "10", "10", "100", "NA") }
        listOfSub.add(Subject("1SDF32", "10", "10", "100", "NA"))
        listOfSub.add(Subject("1QDF32", "10", "10", "100", "NA"))
        listOfSub.add(Subject("1SDF52", "10", "10", "100", "NA"))
        val student = StudentUser("Tarun", "1", "XII", "10", "10", "10", "10", listOfSub)

        view.homeUserAdmNumTxt.text = student.admNo
        view.homeUserClassTxt.text = student.classOfStudent
        view.homeUserRollTxt.text = student.rollNo
        view.homeUserMid1Txt.text = student.mid1
        view.homeUserMid2Txt.text = student.mid2
        view.homeUserFinalTxt.text = student.final

        val layout = LinearLayoutManager(context)
        layout.orientation = LinearLayoutManager.VERTICAL

        view.homerecyclerView.layoutManager = layout
        view.homerecyclerView.adapter = StudentAdapter(context, student.list)

        return view
    }
}