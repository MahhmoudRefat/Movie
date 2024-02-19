package com.example.movie.api.data

import android.content.Context
import android.provider.MediaStore.Audio.Albums
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movie.api.model.movies
import kotlinx.coroutines.internal.synchronized

//الداتا بيز بتربط ال doa  وال tables  مع بعض
@Database(entities = arrayOf(movies::class), version = 1)
abstract class albumDB : RoomDatabase() {
    abstract fun getDBDao(): albumdao

    companion object {
        private var instance: albumDB? = null
        fun getInstance(context: Context): albumDB {
            if (instance == null) {
                kotlin.synchronized(albumDB::class.java) {
                    if (instance == null) {
                        instancesetupub(context)
                    }
                }
            }
            return instance!!
        }

        private fun instancesetupub(context: Context): albumDB? =
            Room.databaseBuilder(
                context.applicationContext, albumDB::class.java, "albumDB"
            ).build()


    }
}
/*
*  companion object {
        private var database: albumDB? = null

        fun getInstance(): albumDB {
            if (database == null) {
                Synchronized(albumDB::class.java)
                {

                }
            }
        }*/