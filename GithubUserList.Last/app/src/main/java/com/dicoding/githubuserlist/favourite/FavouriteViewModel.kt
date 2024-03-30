package com.dicoding.githubuserlist.favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dicoding.githubuserlist.data.Repository
class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: Repository
    init {
        repository = Repository(application)
    }

    fun getFavoriteUser() = repository.getAllFavoriteUser()
}