package com.example.kotlinfragment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinfragment.R
import com.example.kotlinfragment.activities.ListActivity
import com.example.kotlinfragment.fragments.ListDialogFragment
import kotlinx.android.synthetic.main.list_item.view.*


class MyListAdapter(private val context: ListActivity, private val list: MutableList<String>) :
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
                val dialog = ListDialogFragment(posi, context)
                dialog.show(context.supportFragmentManager, "")
            }
        }

        fun setData(pos: Int) {
            posi = pos
            itemView.listText.text = list.get(pos).toString()
        }
    }
}