package mx.yorsh.barbershopkain.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfigOauth {

    companion object {
        fun getConfigOauth(): Retrofit {
            return Retrofit.Builder().baseUrl("http://192.168.0.100:8080/oauth/token")
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder().addInterceptor
                    { chain ->
                        val request =
                            chain.request().newBuilder().addHeader("Authorization", "Bearer xxx")
                                .build()
                        chain.proceed(request)
                    }.build()
                )
                .build()
        }
    }
}