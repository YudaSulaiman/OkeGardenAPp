package com.iman.okegarden.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.iman.okegarden.data.response.Current
import com.iman.okegarden.data.retrofit.ApiService

class WeatherRepository private constructor(
    private val apiService: ApiService,
) {
    fun getWeather(key: String, query: String?): LiveData<Fetch<Current>> = liveData {
        emit(Fetch.Loading)
        try {
            val response = apiService.getWeather(key, query)
            emit(Fetch.Success(response.current))
        } catch (e: Exception){
            Log.d("Repository", "getWeather: ${e.message.toString()}")
            emit(Fetch.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: WeatherRepository? = null
        fun getInstance(apiService: ApiService): WeatherRepository =
            instance ?: synchronized(this){
                instance ?: WeatherRepository(apiService)
            }.also { instance = it }
    }
}