package com.dicoding.githubuserlist.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.githubuserlist.detailuser.FollowFragment

class PagerAdapter(activity: AppCompatActivity, username: String) : FragmentStateAdapter(activity) {

    private var userName2 = username
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        val fragment = FollowFragment()
            fragment.arguments = Bundle().apply {
                putInt(FollowFragment.ARG_SECTION_NUMBER, position + 1)
                putString(FollowFragment.ARG_NAME, userName2)
            }
        return fragment
    }
}