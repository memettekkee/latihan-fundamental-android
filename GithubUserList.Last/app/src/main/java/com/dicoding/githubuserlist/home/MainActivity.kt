package com.dicoding.githubuserlist.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuserlist.R
import com.dicoding.githubuserlist.adapter.UserListAdapter
import com.dicoding.githubuserlist.data.response.UserItems
import com.dicoding.githubuserlist.databinding.ActivityMainBinding
import com.dicoding.githubuserlist.favourite.FavouriteActivity
import com.dicoding.githubuserlist.theme.SettingPreferences
import com.dicoding.githubuserlist.theme.ThemeActivity
import com.dicoding.githubuserlist.theme.dataStore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<UserViewModel> {
        UserViewModel.Factory(SettingPreferences(dataStore))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val layoutManager = LinearLayoutManager(this)
        binding.rvGithub.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvGithub.addItemDecoration(itemDecoration)

        viewModel.setUser.observe(this) {
            setUser(it)
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionID, event ->
                    viewModel.findUsers(searchView.text.toString())
                    searchBar.text = searchView.text
                    searchView.hide()
                    Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menuTheme -> {
                    val intent = Intent(this, ThemeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.menuFav -> {
                    val intent = Intent(this, FavouriteActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        setTheme()
    }

    private fun setTheme() {
        viewModel.getTheme().observe(this) {
                themeChanged: Boolean ->
            if (themeChanged) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun setUser(userData: List<UserItems>) {
        val adapter = UserListAdapter()
        adapter.submitList(userData)
        binding.rvGithub.adapter = adapter

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}