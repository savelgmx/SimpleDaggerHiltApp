package com.example.simpledaggerhiltapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.simpledaggerhiltapp.R
import com.example.simpledaggerhiltapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel:WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { value: WeatherViewModel.WeatherEvent ->
                when(value){
                    is WeatherViewModel.WeatherEvent.Success ->{
                        progressBar.visibility = View.GONE
                        openWeather.setText(value.resultText)
                    }
                    is WeatherViewModel.WeatherEvent.Failure -> {
                        progressBar.visibility = View.GONE
                        openWeather.setText(value.errorText)
                    }
                    is WeatherViewModel.WeatherEvent.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    else -> Unit
                }
            }
        }

    }
}