package com.example.myschool.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myschool.R
import com.example.myschool.model.Subject
import kotlinx.android.synthetic.main.home_recycler_list_item.view.*

class StudentAdapter(val context: Context?, val list: List<Subject>) :
    RecyclerView.Adapter<StudentAdapter.MyStudentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyStudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_recycler_list_item, parent, false)
        return MyStudentViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyStudentViewHolder, position: Int) {
        holder.setData(position)
    }

    inner class MyStudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(pos: Int) {
            itemView.itemUserSubCode.text = list.get(pos).subjectCode
            itemView.itemUserMid1.text = list.get(pos).mid1
            itemView.itemUserMid2.text = list.get(pos).mid2
            itemView.itemUserFinal.text = list.get(pos).final
            itemView.itemUserSubTeacher.text = list.get(pos).subjectTeacher
        }
    }
}