package com.example.simpledaggerhiltapp.repository

import com.example.simpledaggerhiltapp.Resource
import com.example.simpledaggerhiltapp.response.WeatherCityResponse


interface WeatherRepository {


    suspend fun getWeatherCity(): Resource<WeatherCityResponse>


}
