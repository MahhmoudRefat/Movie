package com.example.movie.api

import com.example.movie.api.model.movies
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitClient {
    @GET("")
    suspend fun getAllMovies(): Response<movies>

    companion object {
        var instance: RetrofitClient? = null
        fun getInstance():RetrofitClient{
            if (instance == null) {
                val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
                instance= retrofit.create(RetrofitClient::class.java)

            }
            return instance!!

        }
    }
}