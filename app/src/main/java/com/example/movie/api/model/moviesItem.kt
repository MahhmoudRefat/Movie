package com.example.movie.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "MovieTable")
data class moviesItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id_auto: Int = 0

}