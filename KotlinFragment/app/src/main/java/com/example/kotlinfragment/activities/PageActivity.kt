package com.example.kotlinfragment.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kotlinfragment.R
import com.example.kotlinfragment.fragments.MyPageFragment

class PageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)

        val list = mutableListOf<Fragment>()
        list.add(MyPageFragment.newInstance("tarun"))
        list.add(MyPageFragment.newInstance("arun"))
        list.add(MyPageFragment.newInstance("varun"))
        list.add(MyPageFragment.newInstance("shonty"))
        list.add(MyPageFragment.newInstance("poplu"))
        val pageAdapter = SimplePageAdapter(supportFragmentManager, list)
        val pager = findViewById<ViewPager>(R.id.viewPager)
        pager.adapter = pageAdapter

    }

    inner class SimplePageAdapter(
        fragmentManager: FragmentManager,
        private val list: MutableList<Fragment>
    ) : FragmentPagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            return list.get(position)
        }

        override fun getCount(): Int {
            return list.size
        }

    }
}