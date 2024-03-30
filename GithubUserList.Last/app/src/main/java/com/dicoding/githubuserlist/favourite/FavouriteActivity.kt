package com.dicoding.githubuserlist.favourite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuserlist.adapter.UserListAdapter
import com.dicoding.githubuserlist.databinding.ActivityFavouriteBinding

class FavouriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteBinding
    private var adapter: UserListAdapter = UserListAdapter()
    private lateinit var favouriteViewModel: FavouriteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvFavourite.layoutManager = LinearLayoutManager(this)
        binding.rvFavourite.adapter = adapter
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvFavourite.addItemDecoration(itemDecoration)

        favouriteViewModel = ViewModelProvider(this, ViewModelFactoryFav(application))[FavouriteViewModel::class.java]
        favouriteViewModel.getFavoriteUser().observe(this) {
            adapter.submitList(it)
        }
    }
}