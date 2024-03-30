package com.dicoding.githubuserlist.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.githubuserlist.data.database.FavouriteUserDAO
import com.dicoding.githubuserlist.data.database.FavouriteUserDatabase
import com.dicoding.githubuserlist.data.response.UserItems
import com.dicoding.githubuserlist.util.AppExecutors
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository(
    application: Application
) {

    private val mUserDAO: FavouriteUserDAO
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private val appExecutors: AppExecutors = AppExecutors()

    init {
        val db = FavouriteUserDatabase.getDatabase(application)
        mUserDAO = db.favoriteUserDao()
    }

    fun getAllFavoriteUser(): LiveData<List<UserItems>> = mUserDAO.getAllFavoriteUser()

    fun insert(user: UserItems) {
        executorService.execute { mUserDAO.insert(user) }
    }
    fun checkByUsername(username: String, callback: (Boolean) -> Unit) {
        appExecutors.diskIO.execute {
            val isFavorited = mUserDAO.isUserFavorited(username)
            callback(isFavorited)
        }
    }

    fun delete(user: UserItems) {
        executorService.execute { mUserDAO.delete(user) }
    }
}