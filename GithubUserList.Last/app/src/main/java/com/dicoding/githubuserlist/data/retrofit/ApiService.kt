package com.dicoding.githubuserlist.data.retrofit

import com.dicoding.githubuserlist.data.response.DetailUserResponse
import com.dicoding.githubuserlist.data.response.UserItems
import com.dicoding.githubuserlist.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("search/users")
    fun getGithubUser(
        @Query("q") username: String
    ): Call<UserResponse>

    @GET("users/{username}")
    fun getGithubUserDetail(
        @Path("username") username: String
    ):  Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowerList(
        @Path("username") username: String
    ): Call<List<UserItems>>

    @GET("users/{username}/following")
    fun getFollowingList(
        @Path("username") username: String
    ): Call<List<UserItems>>
}