package com.iman.okegarden.view.viewmodel

import androidx.lifecycle.ViewModel
import com.iman.okegarden.data.WeatherRepository

class WeatherActivityViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun getWeather(key: String, query: String?) = weatherRepository.getWeather(key, query)
}