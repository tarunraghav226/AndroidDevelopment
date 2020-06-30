package com.example.kotlinfragment.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinfragment.R
import com.example.kotlinfragment.adapters.MyListAdapter
import com.example.kotlinfragment.model.Data
import kotlinx.android.synthetic.main.list_fragment.*

class ListActivity : AppCompatActivity() {

    private val list = Data.list
    private val adapter = MyListAdapter(this, list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_fragment)

        listbtn.setOnClickListener {
            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL

            listRecyclerView.layoutManager = layoutManager
            listRecyclerView.adapter = adapter
        }
    }

    open fun change(posi: Int, msg: String) {
        list[posi] = msg
        adapter.notifyDataSetChanged()
    }
}