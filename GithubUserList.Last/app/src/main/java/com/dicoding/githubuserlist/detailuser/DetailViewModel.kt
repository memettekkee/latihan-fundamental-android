package com.dicoding.githubuserlist.detailuser

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.githubuserlist.data.Repository
import com.dicoding.githubuserlist.data.response.DetailUserResponse
import com.dicoding.githubuserlist.data.response.UserItems
import com.dicoding.githubuserlist.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: Repository
    init {
        repository = Repository(application)
    }

    private val _detailuser = MutableLiveData<DetailUserResponse>()
    val detailUser: LiveData<DetailUserResponse> = _detailuser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "DetailViewModel"
    }

    fun getDetailUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getGithubUserDetail(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(call: Call<DetailUserResponse>, response: Response<DetailUserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _detailuser.value = responseBody!!
                } else {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun insertToDb(user: UserItems) {
        repository.insert(user)
    }

    fun deleteFromDb(user: UserItems){
        repository.delete(user)
    }

    fun checkUserFavorite(username: String, callback: (Boolean) -> Unit) {
        repository.checkByUsername(username) { isFavorited ->
            callback(isFavorited)
        }
    }
}