package com.example.pollutionapi.data

import com.example.pollutionapi.data.model.Station
import retrofit2.Call
import retrofit2.http.GET

interface Repository {
    @GET("station/findAll")
    fun findAll(): Call<List<Station>>
}