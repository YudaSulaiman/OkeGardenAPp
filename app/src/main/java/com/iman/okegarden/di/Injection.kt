package com.iman.okegarden.di

import com.iman.okegarden.data.WeatherRepository
import com.iman.okegarden.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(): WeatherRepository {
        val apiService = ApiConfig.getApiService()
        return WeatherRepository.getInstance(apiService)
    }
}