package com.example.simpledaggerhiltapp.di




import com.example.simpledaggerhiltapp.utils.AppConstants
import com.example.simpledaggerhiltapp.repository.OpenWeatherRepository
import com.example.simpledaggerhiltapp.repository.WeatherRepository
import com.zaidzakir.mediumexample.model.api.OpenWeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule{


    @Singleton
    @Provides
    fun provideOpenWeatherApi(): OpenWeatherApi = Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL_RETROFIT_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OpenWeatherApi::class.java)
    @Singleton
    @Provides
    fun provideWeatherRepository(openWeatherApi: OpenWeatherApi): WeatherRepository = OpenWeatherRepository(openWeatherApi)


}