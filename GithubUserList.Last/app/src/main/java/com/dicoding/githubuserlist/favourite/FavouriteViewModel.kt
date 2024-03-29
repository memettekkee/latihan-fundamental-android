package com.dicoding.githubuserlist.favourite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubuserlist.data.Repository
import com.dicoding.githubuserlist.data.database.FavouriteUser
import com.dicoding.githubuserlist.data.response.UserItems
import com.dicoding.githubuserlist.data.response.UserResponse

class FavouriteViewModel(private val repository: Repository) : ViewModel() {
//    private val _favouriteuser = MutableLiveData<List<UserItems>>()
//    val favouriteUser: LiveData<List<UserItems>> = _favouriteuser

   fun getFavoriteUser() : LiveData<List<FavouriteUser>> {
        val liveData = repository.getFavoriteUser()
//        _favouriteuser.addSource(liveData)
//        _favouriteuser.value = liveData.observe
//        { favoriteUserList ->
//            val convertedList = favoriteUserList.map { favoriteUser ->
//                UserItems(favoriteUser.username, favoriteUser.avatarUrl ?: "")
//            }
//            _favouriteuser.value = convertedList
//        }
        return liveData
    }

    init {
        getFavoriteUser()
    }
}