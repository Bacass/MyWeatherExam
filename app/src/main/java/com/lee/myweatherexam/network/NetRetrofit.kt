package com.lee.myweatherexam.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


class NetRetrofit private constructor() {

    internal var retrofit = Retrofit.Builder()
        .baseUrl("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/")
        .addConverterFactory(GsonConverterFactory.create()) // 파싱등록
        .client(httpBuilder.build())
        .build()

    var service = retrofit.create<ServiceApi>(ServiceApi::class.java!!)
        internal set

    companion object {
        val instance = NetRetrofit()
    }


    val httpBuilder: OkHttpClient.Builder get() {
        // create http client
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val original = chain.request()

                //header
                val request = original.newBuilder()
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build()

                return@Interceptor chain.proceed(request)
            })
            .readTimeout(30, TimeUnit.SECONDS)

        // log interceptor
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(loggingInterceptor)

        return httpClient
    }
}

