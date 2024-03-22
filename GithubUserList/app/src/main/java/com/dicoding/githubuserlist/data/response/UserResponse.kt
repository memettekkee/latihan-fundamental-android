package com.dicoding.githubuserlist.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(

	@field:SerializedName("items")
	val items: List<UserItems>
) : Parcelable

@Parcelize
data class UserItems(

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

) : Parcelable
