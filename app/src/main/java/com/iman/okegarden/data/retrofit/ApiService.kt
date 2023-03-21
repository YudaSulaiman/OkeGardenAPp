package com.iman.okegarden.data.retrofit

import com.iman.okegarden.data.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String?
    ):WeatherResponse
}
