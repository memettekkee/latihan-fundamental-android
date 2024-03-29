package com.dicoding.githubuserlist.detailuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.githubuserlist.data.Repository
import com.dicoding.githubuserlist.data.database.FavouriteUser
import com.dicoding.githubuserlist.data.response.DetailUserResponse
import com.dicoding.githubuserlist.data.response.UserItems
import com.dicoding.githubuserlist.data.response.UserResponse
import com.dicoding.githubuserlist.data.retrofit.ApiConfig
import com.dicoding.githubuserlist.home.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val repository: Repository) : ViewModel() {
    private val _detailuser = MutableLiveData<DetailUserResponse>()
    val detailUser: LiveData<DetailUserResponse> = _detailuser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isFavourite = MediatorLiveData<Boolean>()
    val isFavourite: LiveData<Boolean> = _isFavourite

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

    fun isFavourite(username: String) {
        val liveData = repository.isFavorite(username)
        Log.d("info5", "ceks")
        _isFavourite.addSource(liveData) { result ->
            _isFavourite.value = result
        }
    } // disini errornya

    fun saveFavourite(favourite: FavouriteUser) =
        viewModelScope.launch(Dispatchers.IO) { repository.saveFavorite(favourite)}

    fun deleteFavourite(favourite: FavouriteUser) =
        viewModelScope.launch(Dispatchers.IO) { repository.delete(favourite)}
}