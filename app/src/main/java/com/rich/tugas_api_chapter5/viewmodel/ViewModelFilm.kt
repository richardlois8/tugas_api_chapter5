package com.rich.tugas_api_chapter5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rich.tugas_api_chapter5.model.DataFilm
import com.rich.tugas_api_chapter5.model.GetFilmResponseItem
import com.rich.tugas_api_chapter5.model.GetUserResponseItem
import com.rich.tugas_api_chapter5.model.PostDataFilm
import com.rich.tugas_api_chapter5.network.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ViewModelFilm :ViewModel() {

    lateinit var liveDataFilm : MutableLiveData<List<GetFilmResponseItem>>
    lateinit var postLDFilm :MutableLiveData<PostDataFilm>

    init {
        liveDataFilm = MutableLiveData()
        postLDFilm = MutableLiveData()
    }

    fun getLiveDataFilem() : MutableLiveData<List<GetFilmResponseItem>>{
        return liveDataFilm
    }

    fun postLiveDataFilm():MutableLiveData<PostDataFilm>{
        return postLDFilm
    }

    fun callPostApiFilm(name : String, image : String, director : String, desc : String){
        APIClient.instance.addDataFilm(DataFilm(name,image,director,desc))
            .enqueue(object : Callback<PostDataFilm>{
                override fun onResponse(
                    call: Call<PostDataFilm>,
                    response: Response<PostDataFilm>
                ) {
                    if (response.isSuccessful){
                        postLDFilm.postValue(response.body())
                    }else{
                        postLDFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<PostDataFilm>, t: Throwable) {
                    postLDFilm.postValue(null)
                }

            })
    }

    fun callApiFilm(){
        APIClient.instance.getAllFilm()
            .enqueue(object : Callback<List<GetFilmResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetFilmResponseItem>>,
                    response: Response<List<GetFilmResponseItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataFilm.postValue(response.body())
                    } else{
                        liveDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetFilmResponseItem>>, t: Throwable) {
                    liveDataFilm.postValue(null)
                }

            })
    }
}