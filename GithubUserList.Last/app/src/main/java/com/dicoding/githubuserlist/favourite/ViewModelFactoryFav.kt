package com.dicoding.githubuserlist.favourite

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.githubuserlist.data.Repository
import com.dicoding.githubuserlist.detailuser.DetailViewModel
import com.dicoding.githubuserlist.di.Injection
import com.dicoding.githubuserlist.theme.ViewModelFactory

class ViewModelFactoryFav private constructor(private val repository: Repository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> return DetailViewModel(repository) as T
            modelClass.isAssignableFrom(FavouriteViewModel::class.java) -> return FavouriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactoryFav? = null
        fun getInstance(context: Context): ViewModelFactoryFav = instance ?: synchronized(this) {
            instance ?: ViewModelFactoryFav(Injection.provideRepository(context))
        }.also { instance = it }
    }
}