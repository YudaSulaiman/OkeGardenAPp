package com.iman.okegarden.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iman.okegarden.databinding.ActivityWeatherResultBinding

class WeatherResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setupView()
    }

    private fun setupView(){
        val location = intent.getStringExtra(LOCATION)
        val celsius = intent.getStringExtra(CELSIUS)
        val fahrenheit = intent.getStringExtra(FAHRENHEIT)

        binding.tvLocation.text = location
        binding.tvCelsiusValue.text = celsius
        binding.tvFahrenheitValue.text = fahrenheit
    }

    companion object{
        const val LOCATION = "location"
        const val CELSIUS = "Celsius"
        const val FAHRENHEIT = "fahrenheit"
    }
}