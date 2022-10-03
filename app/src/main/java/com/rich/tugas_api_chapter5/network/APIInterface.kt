package com.rich.tugas_api_chapter5.network

import com.rich.tugas_api_chapter5.model.DataFilm
import com.rich.tugas_api_chapter5.model.PostDataFilm
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {

    @POST("film")
    fun addDataFilm(@Body request : DataFilm): Call<PostDataFilm>

}