package com.dicoding.githubuserlist.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.dicoding.githubuserlist.data.response.UserItems
import com.dicoding.githubuserlist.data.response.UserResponse
import com.dicoding.githubuserlist.data.retrofit.ApiConfig
import com.dicoding.githubuserlist.theme.SettingPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val pref: SettingPreferences) : ViewModel() {
    private val _setuser = MutableLiveData<List<UserItems>>()
    val setUser: LiveData<List<UserItems>> = _setuser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val GITHUB_ID = "memettekkee"
        private const val TAG = "UserViewModel"
    }

    init {
        ListGithubUser()
    }

    private fun ListGithubUser() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getGithubUser(GITHUB_ID)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _setuser.value = responseBody?.items
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun findUsers(q: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getGithubUser(q)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _setuser.value = responseBody?.items
                } else {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
    fun getTheme() = pref.getThemeSetting().asLiveData()

    class Factory(private val preferences: SettingPreferences) :
            ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = UserViewModel(preferences) as T
    }
}
