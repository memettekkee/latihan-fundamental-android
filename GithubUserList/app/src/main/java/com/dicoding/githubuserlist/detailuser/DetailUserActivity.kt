package com.dicoding.githubuserlist.detailuser

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.githubuserlist.R
import com.dicoding.githubuserlist.adapter.PagerAdapter
import com.dicoding.githubuserlist.adapter.UserListAdapter
import com.dicoding.githubuserlist.data.response.UserItems
import com.dicoding.githubuserlist.data.response.UserResponse
import com.dicoding.githubuserlist.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    private lateinit var dataUser: UserItems

    companion object {
        const val EXTRA_ID = "extra_id"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            dataUser = intent.getParcelableExtra(EXTRA_ID, UserItems::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            dataUser = intent.getParcelableExtra(EXTRA_ID)!!
        }

        val detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val username = intent.getStringExtra(EXTRA_ID)

        detailViewModel.getDetailUser(dataUser.login)
        detailViewModel.detailUser.observe(this) {
            userData ->
            binding.tvUsername.text = userData.login
            binding.tvName.text = userData.name
            binding.tvFollower.text = "${userData.followers} Followers"
            binding.tvFollowing.text = "${userData.following} Following"
            Glide
                .with(this)
                .load(userData.avatarUrl)
                .into(binding.ivPhoto)
        }

        val pagerAdapter = PagerAdapter(this, dataUser.login)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) {tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar2.visibility = View.VISIBLE
        } else {
            binding.progressBar2.visibility = View.GONE
        }
    }
}