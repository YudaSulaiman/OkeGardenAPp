package com.iman.okegarden.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.iman.okegarden.R
import com.iman.okegarden.data.Fetch
import com.iman.okegarden.databinding.ActivityWeatherBinding
import com.iman.okegarden.view.viewmodel.WeatherActivityViewModel
import com.iman.okegarden.view.viewmodel.ViewModelFactory

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    private var location: String? = null

    private val viewModel: WeatherActivityViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    override fun onResume() {
        super.onResume()
        val category = resources.getStringArray(R.array.city_name)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, category)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val key = binding.tfApikeyValues.text.toString()

        binding.autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            location = parent.getItemAtPosition(position).toString()
            submitData(key, location)
        }

        submitData(key, "Kuala Lumpur") // default value if user didnt choose the dropdown
    }

    private fun submitData(key: String, location: String?) {
        Log.d("lokasi", location.toString())
        binding.button.setOnClickListener {
            viewModel.getWeather(key, location).observe(this) { result ->
                if (result != null){
                    when (result) {
                        is Fetch.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Fetch.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val data = result.data
                            val toDetail = Intent(this@WeatherActivity, WeatherResultActivity::class.java)
                            toDetail.putExtra(WeatherResultActivity.LOCATION, location)
                            toDetail.putExtra(WeatherResultActivity.CELSIUS, data.tempC.toString())
                            toDetail.putExtra(WeatherResultActivity.FAHRENHEIT, data.tempF.toString())
                            startActivity(toDetail)
                        }
                        is Fetch.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                this,
                                result.error,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        else -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Data not Found", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}