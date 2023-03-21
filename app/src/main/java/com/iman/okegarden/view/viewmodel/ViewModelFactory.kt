package com.iman.okegarden.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iman.okegarden.data.WeatherRepository
import com.iman.okegarden.di.Injection

class ViewModelFactory private constructor(private val weatherRepository: WeatherRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherActivityViewModel::class.java)) {
            return WeatherActivityViewModel(weatherRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)

    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }.also { instance = it }
    }
}