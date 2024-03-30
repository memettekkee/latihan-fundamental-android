package com.dicoding.githubuserlist.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.githubuserlist.data.response.UserItems

@Dao
interface FavouriteUserDAO {
    @Query("SELECT EXISTS(SELECT * FROM FavoriteUser WHERE login = :username)")
    fun isUserFavorited(username: String): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: UserItems)

    @Delete
    fun delete(user: UserItems)

    @Query("SELECT * FROM FavoriteUser")
    fun getAllFavoriteUser(): LiveData<List<UserItems>>
}
