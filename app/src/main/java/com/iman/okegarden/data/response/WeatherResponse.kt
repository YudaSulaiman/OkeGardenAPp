package com.iman.okegarden.data.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

	@field:SerializedName("current")
	val current: Current,
)

data class Current(

	@field:SerializedName("temp_c")
	val tempC: Double,

	@field:SerializedName("temp_f")
	val tempF: Double,

	@field:SerializedName("condition")
	val condition: Condition,

	@field:SerializedName("humidity")
	val humidity: Int,

)

data class Condition(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("icon")
	val icon: String,

	@field:SerializedName("text")
	val text: String
)
