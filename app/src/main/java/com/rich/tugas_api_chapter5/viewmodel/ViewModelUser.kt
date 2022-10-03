package com.rich.tugas_api_chapter5.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rich.tugas_api_chapter5.model.DataUser
import com.rich.tugas_api_chapter5.model.PostUserResponse
import com.rich.tugas_api_chapter5.network.APIClient

class ViewModelUser : ViewModel() {
    lateinit var postLDUser : MutableLiveData<PostUserResponse>

    init {
        postLDUser = MutableLiveData()
    }

    fun postLiveDataUser():MutableLiveData<PostUserResponse>{
        return postLDUser
    }

    fun callPostApiUser(name : String, username : String, password : String){
        APIClient.instance.registerUser(
            DataUser(name,username,password))
            .enqueue(object : retrofit2.Callback<PostUserResponse>{
                override fun onResponse(
                    call: retrofit2.Call<PostUserResponse>,
                    response: retrofit2.Response<PostUserResponse>
                ) {
                    if (response.isSuccessful){
                        postLDUser.postValue(response.body())
                    }else{
                        Log.d("Error", response.message())
                        postLDUser.postValue(null)
                    }
                }

                override fun onFailure(call: retrofit2.Call<PostUserResponse>, t: Throwable) {
                    Log.d("Error", t.message.toString())
                    postLDUser.postValue(null)
                }

            })
    }
}