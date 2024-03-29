package com.dicoding.githubuserlist.detailuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubuserlist.data.response.UserItems
import com.dicoding.githubuserlist.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel : ViewModel() {
    private val _userFollowers = MutableLiveData<List<UserItems>>()
    val userFollowers: LiveData<List<UserItems>> = _userFollowers

    private val _userFollowing = MutableLiveData<List<UserItems>>()
    val userFollowing:  LiveData<List<UserItems>> = _userFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "FollowViewModel"
    }

    fun getFollowersUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowerList(username)
        client.enqueue(object : Callback<List<UserItems>> {
            override fun onResponse(
                call: Call<List<UserItems>>,
                response: Response<List<UserItems>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _userFollowers.value = responseBody!!
                } else {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<UserItems>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getFollowingUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowingList(username)
        client.enqueue(object : Callback<List<UserItems>> {
            override fun onResponse(
                call: Call<List<UserItems>>,
                response: Response<List<UserItems>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _userFollowing.value = responseBody!!
                } else {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<UserItems>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}