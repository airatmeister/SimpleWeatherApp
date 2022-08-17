package com.weather.weather.data.cloud

import com.weather.weather.data.cloud.model.Temp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?units=metric&appid=86b46d0f343f828c91d9293c9c47d01d")
    fun fetchWeather(@Query("q") city: String): Call<Temp>
}