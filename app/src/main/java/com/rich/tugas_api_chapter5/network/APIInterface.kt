package com.rich.tugas_api_chapter5.network

import com.rich.tugas_api_chapter5.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {

    @GET("film")
    fun getAllFilm() : Call<List<GetFilmResponseItem>>

    @POST("film")
    fun addDataFilm(@Body request : DataFilm): Call<PostDataFilm>

    @GET("user")
    fun getAllUser(): Call<List<GetUserResponseItem>>

    @POST("user")
    fun registerUser(@Body request : DataUser): Call<PostUserResponse>

}