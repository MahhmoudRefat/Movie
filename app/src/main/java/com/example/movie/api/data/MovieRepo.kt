package com.example.movie.api.data

import androidx.lifecycle.LiveData
import com.example.movie.api.RetrofitClient
import com.example.movie.api.model.movies
import com.example.movie.api.model.moviesItem


class MovieRepo(val dao: albumdao) {
    suspend fun insert(albumModel: movies) = dao.insertMovie(albumModel) // this is dao
    suspend fun update(albumModel: movies) = dao.updateMovie(albumModel)// this is dao
    suspend fun delete(albumModel: movies) = dao.deleteMovie(albumModel)// this is dao
    suspend fun isExists(titleName: String): Boolean = dao.isExsiste(titleName)// this is dao

    val getAlbumLive: LiveData<movies> = dao.getalbumlistroom() //this is room
    suspend fun getAlbumApi() = RetrofitClient.getInstance().getAllMovies() // this is the api
}