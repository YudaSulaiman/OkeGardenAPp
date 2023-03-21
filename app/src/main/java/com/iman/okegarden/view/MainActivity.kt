package com.iman.okegarden.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.iman.okegarden.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_layout1).setOnClickListener {
            val toWeatherActivity = Intent(this@MainActivity, WeatherActivity::class.java)
            startActivity(toWeatherActivity)
        }

        findViewById<Button>(R.id.btn_layout2).setOnClickListener {
            val toWeatherActivity = Intent(this@MainActivity, DetailProdukActivity::class.java)
            startActivity(toWeatherActivity)
        }
    }
}