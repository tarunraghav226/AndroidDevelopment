package com.example.kotlinhelloworldandintent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class MyAdapter(val context: Context, val personList: List<Person>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = personList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.setData(personList.get(position), position)


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
            }
        }

        fun setData(txt: Person, pos: Int) {
            itemView.listTxt.text = personList.get(pos).toString()
        }
    }
}