package com.dicoding.githubuserlist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.githubuserlist.data.response.UserItems


@Database(entities = [UserItems::class], version = 3)
abstract class FavouriteUserDatabase: RoomDatabase() {

    abstract fun favoriteUserDao(): FavouriteUserDAO

    companion object {
        @Volatile
        private var INSTANCE: FavouriteUserDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavouriteUserDatabase {
            if (INSTANCE == null) {
                synchronized(FavouriteUserDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavouriteUserDatabase::class.java, "favorite_user_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as FavouriteUserDatabase
        }
    }
}