package com.dicoding.githubuserlist.data.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(

	@field:SerializedName("items")
	val items: List<UserItems>
) : Parcelable

@Parcelize @Entity(tableName = "FavoriteUser")
data class UserItems(

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("id")
	@PrimaryKey
	val id: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

) : Parcelable
