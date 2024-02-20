package com.example.movie.api.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.movie.api.model.movies


@Dao
interface albumdao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: movies)
    @Update
    suspend fun updateMovie(movies: movies)
    @Delete
    suspend fun deleteMovie(movies: movies)

    //بيدور على الmovie  بالاسم
    @Query("Select Exists(Select 1 From MovieTable where title = :titleName ) ")
    suspend fun  isExsiste(titleName: String) : Boolean

    @Query("select * from MovieTable ")
    fun getalbumlistroom():LiveData<movies>


}