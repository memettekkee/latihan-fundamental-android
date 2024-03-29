package com.dicoding.githubuserlist.di

import android.content.Context
import com.dicoding.githubuserlist.data.Repository
import com.dicoding.githubuserlist.data.database.FavouriteUserDatabase

object Injection {
    fun provideRepository(context: Context): Repository {
        val database = FavouriteUserDatabase.getDatabase(context)
        val dao = database.favoriteUserDao()
        return Repository.getInstance(dao)
    }
}