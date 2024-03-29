package com.dicoding.githubuserlist.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface FavouriteUserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteUser: FavouriteUser)

//    @Update
//    suspend fun update(favoriteUser: FavouriteUser)

    @Delete
    suspend fun delete(favoriteUser: FavouriteUser)

    @Query("SELECT * from FavouriteUser")
    fun getAllFavoriteUser(): LiveData<List<FavouriteUser>>

    @Query("SELECT EXISTS(SELECT * FROM FavouriteUser WHERE FavouriteUser.username = :username)")
    fun isFavorite(username: String): LiveData<Boolean>
}