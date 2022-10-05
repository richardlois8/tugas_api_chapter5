package com.rich.tugas_api_chapter5.network

import com.rich.tugas_api_chapter5.model.*
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("film")
    fun getAllFilm() : Call<List<GetFilmResponseItem>>

    @GET("film/{id}")
    fun getFilmById(@Path("id") id: Int) : Call<PostDataFilmItem>

    @POST("film")
    fun addDataFilm(@Body request : DataFilm): Call<PostDataFilm>

    @PUT("film/{id}")
    fun updateDataFilm(@Path("id") id : Int,@Body reques :DataFilm ): Call<PostDataFilmItem>

    @DELETE("film/{id}")
    fun deleteFilm(@Path("id") id : Int): Call<PostDataFilmItem>

    @GET("user")
    fun getAllUser(): Call<List<GetUserResponseItem>>

    @POST("user")
    fun registerUser(@Body request : DataUser): Call<PostUserResponse>


}