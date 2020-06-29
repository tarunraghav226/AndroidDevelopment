package com.example.kotlinfragment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinfragment.R
import kotlinx.android.synthetic.main.list_item.view.*


class MyListAdapter(private val context: Context, private val list: MutableList<String>) :
    RecyclerView.Adapter<MyListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) = holder.setData(position)

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var posi = 0

        init {
            itemView.setOnClickListener {
                list[posi] = "changed"
                Toast.makeText(context, "Changed", Toast.LENGTH_SHORT).show()
            }
        }

        fun setData(pos: Int) {
            posi = pos
            itemView.listText.text = list.get(pos).toString()
        }
    }
}