package com.dicoding.githubuserlist.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.dicoding.githubuserlist.data.database.FavouriteUser
import com.dicoding.githubuserlist.data.database.FavouriteUserDAO
import com.dicoding.githubuserlist.data.retrofit.ApiService

class Repository(
    private val favoriteUserDao: FavouriteUserDAO
) {
    suspend fun saveFavorite(favorite: FavouriteUser) {
        favoriteUserDao.insert(favorite)
    }

    suspend fun delete(favorite: FavouriteUser) {
        favoriteUserDao.delete(favorite)
    }

    fun getFavoriteUser(): LiveData<List<FavouriteUser>> =
        favoriteUserDao.getAllFavoriteUser()

    fun isFavorite(username: String): LiveData<Boolean> {
        val tes = favoriteUserDao.isFavorite(username)

        Log.d("info6", "bisa dongggggggggggggggg")

        return tes
    }
//        favoriteUserDao.isFavorite(username)
    // disini

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            favoriteUserDao: FavouriteUserDAO,
        ): Repository = instance ?: synchronized(this) {
            instance ?: Repository(favoriteUserDao)
        }.also { instance = it }
    }
}