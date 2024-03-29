package com.dicoding.githubuserlist.favourite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuserlist.adapter.UserListAdapter
import com.dicoding.githubuserlist.databinding.ActivityFavouriteBinding

class FavouriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteBinding
    private lateinit var adapter: UserListAdapter
    private lateinit var favouriteViewModel: FavouriteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factoryFV: ViewModelFactoryFav = ViewModelFactoryFav.getInstance(this)
        favouriteViewModel = ViewModelProvider(this, factoryFV)[FavouriteViewModel::class.java]

//        favouriteViewModel.favouriteUser.observe(this) {
        favouriteViewModel.getFavoriteUser().observe(this) {
            showLoading(false)
            if (!it.isNullOrEmpty()) {
                val layoutManager = LinearLayoutManager(this)
                binding.rvFavourite.layoutManager = layoutManager
                adapter = UserListAdapter()
                binding.rvFavourite.adapter = adapter
            } else {
                binding.rvFavourite.visibility = View.GONE
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar4.visibility = View.VISIBLE
        } else {
            binding.progressBar4.visibility = View.GONE
        }
    }
}