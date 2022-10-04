package com.rich.tugas_api_chapter5.network

import com.rich.tugas_api_chapter5.model.*
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("film")
    fun getAllFilm() : Call<List<GetFilmResponseItem>>

    @POST("film")
    fun addDataFilm(@Body request : DataFilm): Call<PostDataFilm>

    @GET("user")
    fun getAllUser(): Call<List<GetUserResponseItem>>

    @POST("user")
    fun registerUser(@Body request : DataFilm): Call<PostUserResponse>

    @PUT("film/{id}")
    fun updateDataFilm(@Path("id") id : Int,@Body reques :DataFilm ): Call<List<PutFilmResponseItem>>

}