package mx.yorsh.barbershopkain.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfiguration {

    companion object {
        fun getConfiguration(): Retrofit {
            return Retrofit.Builder().baseUrl("http://192.168.0.100:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}