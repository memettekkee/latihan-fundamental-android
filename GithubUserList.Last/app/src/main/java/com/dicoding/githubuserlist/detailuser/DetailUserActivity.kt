package com.dicoding.githubuserlist.detailuser

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.githubuserlist.R
import com.dicoding.githubuserlist.adapter.PagerAdapter
import com.dicoding.githubuserlist.adapter.UserListAdapter
import com.dicoding.githubuserlist.data.database.FavouriteUser
import com.dicoding.githubuserlist.data.response.DetailUserResponse
import com.dicoding.githubuserlist.data.response.UserItems
import com.dicoding.githubuserlist.data.response.UserResponse
import com.dicoding.githubuserlist.databinding.ActivityDetailUserBinding
import com.dicoding.githubuserlist.favourite.ViewModelFactoryFav
import com.dicoding.githubuserlist.theme.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var username: String
    private lateinit var dataUser: UserItems
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var detailUser: DetailUserResponse

    companion object {
        const val EXTRA_ID = "extra_id"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }



    private fun setFavourite() {
        Log.d("info2", "infosettt")
        val ivFav = binding.fabAdd
//        detailViewModel.isFavourite(username).observe(this@DetailUserActivity)
        detailViewModel.isFavourite(username)
        detailViewModel.isFavourite.observe(this)
        {

            Log.d("info4", "cek")

            when (it) {


                true -> {
                    ivFav.setImageDrawable(
                        ContextCompat.getDrawable(
                            ivFav.context,
                            R.drawable.ic_favourite
                        )
                    )
                    ivFav.setOnClickListener {
                        val user = FavouriteUser(detailUser.login, detailUser.avatarUrl)
                        detailViewModel.saveFavourite(user)

                        Log.d("info1", "onklik")
                    }
                }
                false -> {
                    ivFav.setImageDrawable(
                        ContextCompat.getDrawable(
                            ivFav.context,
                            R.drawable.ic_favourite_border
                        )
                    )
                    ivFav.setOnClickListener {
                        val user = FavouriteUser(detailUser.login, detailUser.avatarUrl)
                        detailViewModel.deleteFavourite(user)
                    }
                }



//                true -> {
//                    ivFav.setImageDrawable(
//                        ContextCompat.getDrawable(
//                            ivFav.context,
//                            R.drawable.ic_favourite
//                        )
//                    )
//                    ivFav.setOnClickListener {
//                        val user = FavouriteUser(detailUser.login, detailUser.avatarUrl)
//                        detailViewModel.deleteFavourite(user)
//                    }
//                }
//                false -> {
//                    ivFav.setImageDrawable(
//                        ContextCompat.getDrawable(
//                            ivFav.context,
//                            R.drawable.ic_favourite_border
//                        )
//                    )
//                    ivFav.setOnClickListener {
//                        val user = FavouriteUser(detailUser.login, detailUser.avatarUrl)
//                        detailViewModel.saveFavourite(user)
//                    }
//                }


            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar2.visibility = View.VISIBLE
        } else {
            binding.progressBar2.visibility = View.GONE
        }
    }
}