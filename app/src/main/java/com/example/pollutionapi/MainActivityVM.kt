package com.example.pollutionapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pollutionapi.data.Repository
import com.example.pollutionapi.data.model.Station
import com.google.gson.InstanceCreator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityVM: ViewModel() {
    val api = Retrofit.Builder()
        .addConverterFactory(
            GsonConverterFactory.create())
        .baseUrl("https://api.gios.gov.pl/pjp-api/rest/")
        .build()
        .create(Repository::class.java)

    private val _stations: MutableLiveData<List<Station>> by lazy {
        MutableLiveData<List<Station>>().also { fetchStations() }
    }

    fun getStations(): LiveData<List<Station>> = _stations

    fun fetchStations() {
        val call = api.findAll()
        call.enqueue(object: Callback<List<Station>>{

            override fun onResponse(call: Call<List<Station>>, response: Response<List<Station>>) {
                _stations.value = if (response.isSuccessful) response.body()!!.sortedBy { it.id } else emptyList()
            }

            override fun onFailure(call: Call<List<Station>>, t: Throwable) {
                _stations.value = emptyList()
            }
        })
    }
}