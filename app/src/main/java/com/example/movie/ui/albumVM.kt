package com.example.movie.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movie.api.data.MovieRepo
import com.example.movie.api.data.albumDB
import com.example.movie.api.model.movies
import com.example.movie.utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class AlbumVM(context: Application) : AndroidViewModel(context) {
    lateinit var repo: MovieRepo

    //    lateinit var getAlbumInfo: LiveData<AlbumModel>
    private val _isExistsMovie: MutableLiveData<Boolean> = MutableLiveData()
    val isExists: LiveData<Boolean> = _isExistsMovie
    private val _getStatus: MutableLiveData<Resources<movies>> = MutableLiveData()
    val getStatus: LiveData<Resources<movies>> = _getStatus
    private var _getRetro = MutableLiveData<movies>()
    var getAlbum: LiveData<movies> = _getRetro

    // init for Room
    init {
        val dao = albumDB.getInstance(context).getDBDao() // dao with db
        repo = MovieRepo(dao)
        getAlbum = repo.getAlbumLive
    }

    suspend fun setuuRetro() {
        val response = repo.getAlbumApi()
        try {
            if (response.isSuccessful) {
                _getRetro.postValue(response.body())
            }
        } catch (e: Exception) {
        }
    }

    fun insert(albumModel: movies) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(albumModel)
    }

    fun delete(albumModel: movies) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(albumModel)
    }

    fun isExists(name: String) = viewModelScope.launch(Dispatchers.IO) {
        _isExistsMovie.postValue(repo.isExists(name))
    }

    suspend fun getMovieApiVM() {
        // we need to get the api response from the retrofit from the Repo
        val responseData = repo.getAlbumApi()
        // loading need before try
        _getStatus.postValue(Resources.Loading())
        try {
            if (responseData.isSuccessful) {
                _getStatus.postValue(Resources.Success(responseData.body()))
            } else {
                _getStatus.postValue(Resources.Error(responseData.message()))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _getStatus.postValue(Resources.Error("Error from Data"))
                else -> _getStatus.postValue(Resources.Error(t.message.toString()))
            }
        }
    }
}

