package com.own_world.weatherappversion_20

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.own_world.weatherappversion_20.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        fetchData()
    }

    private fun fetchData() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build().create(ApiInterface::class.java)

        val respose = retrofit.getWeatherData("Dhaka","4a4d0102e2ac2a7143f60fdbcd8c267b", "metric")
        respose.enqueue(object : Callback<weatherApi> {
            override fun onResponse(call: Call<weatherApi>, response: Response<weatherApi>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val logitute = responseBody.coord.lon
                    val latitude = responseBody.coord.lat
                    val clouds = responseBody.clouds.all
                    val temp = responseBody.main.temp
                    val feels_like = responseBody.main.feels_like
                    val temp_min = responseBody.main.temp_min
                    val temp_max = responseBody.main.temp_max
                    val humidity = responseBody.main.humidity
                    val pressure = responseBody.main.pressure
                    val sea_level = responseBody.main.sea_level
                    val grnd_level = responseBody.main.grnd_level

                    binding.lon.text = "Longitute :$logitute"
                    binding.lat.text = "Latitude :$latitude"
                    binding.textView.text = "Clouds :$clouds"
                    binding.temp.text = "Tempeture: $temp"
                    binding.feelsLike.text = "Feels : $feels_like"
                    binding.minTemp.text = "Minimum-Tempeture : $temp_min °C"
                    binding.maxTemp.text = "Max-Tempeture :$temp_max °C"
                    binding.textView3.text = "Humidity :$humidity"
                    binding.presure.text = "Presure :$pressure"
                    binding.grdnLevel.text = "Grands_Level : $grnd_level"


                }
            }

            override fun onFailure(call: Call<weatherApi>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
}
}

