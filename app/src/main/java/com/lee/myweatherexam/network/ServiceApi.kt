package com.lee.myweatherexam.network

import com.lee.myweatherexam.model.WeatherDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("getMsrstnAcctoRltmMesureDnsty?")
    fun getMsrstnAcctoRltmMesureDnsty(
        @Query("numOfRows") numOfRows : String,
        @Query("pageNo") pageNo : String,
        @Query("stationName") stationName : String,
        @Query("dataTerm") dataTerm : String,
        @Query("ver") ver : String,
        @Query("_returnType") _returnType : String,
        @Query("ServiceKey") ServiceKey : String


    ): Call<WeatherDto>
}