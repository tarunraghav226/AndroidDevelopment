package com.example.kotlinfragment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlinfragment.R

class MyPageFragment : Fragment() {

    companion object {
        fun newInstance(name: String): MyPageFragment {
            val pageFragment = MyPageFragment()
            val bundle = Bundle(1)
            bundle.putString("MESSAGE", name)
            pageFragment.arguments = bundle
            return pageFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.page_fragment_layout, container, false)
        view.findViewById<TextView>(R.id.pageFragTxt).text = arguments!!.getString("MESSAGE")
        return view
    }

}