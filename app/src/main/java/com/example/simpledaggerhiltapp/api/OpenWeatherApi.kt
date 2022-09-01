
package com.zaidzakir.mediumexample.model.api


import com.example.simpledaggerhiltapp.utils.AppConstants
import com.example.simpledaggerhiltapp.response.WeatherCityResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {

  @GET("weather")
  suspend fun getWeatherOfCity(
    @Query("q") q: String,
    @Query("units") units: String = AppConstants.WEATHER_UNIT,
    @Query("appid") appid: String = AppConstants.WEATHER_API_KEY
  ): Response<WeatherCityResponse>

}