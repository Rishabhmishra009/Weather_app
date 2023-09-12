import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitClient {
    private const val BASE_URL = "http://api.weatherapi.com/v1/current.json?key=1d0ddc16fc254a4086595524231708&q=bangalore&aqi=no"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
data class WeatherResponse(
    val temperature: Double,
    val description: String,
)
interface WeatherApiService {
    @GET("weather")
    fun getWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>
}

// here
val retrofit = RetrofitClient.instance
val apiService = retrofit.create(WeatherApiService::class.java)

val apiKey = "1d0ddc16fc254a4086595524231708"
val location = "Bangalore"

val call = apiService.getWeather(location, apiKey)

call.enqueue(object : Callback<WeatherResponse> {
    override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
        if (response.isSuccessful) {
            val weatherResponse = response.body()
            if (weatherResponse != null) {
                // Handle the weather data
            } else {
                // Handle the case where the response body is null
            }
        } else {
            // Handle error response (e.g., non-200 status code)
        }
    }

    /*override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
        // Handle network failure or other exceptions
    }*/
}




