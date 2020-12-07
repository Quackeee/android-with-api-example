package com.example.pollutionapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pollutionapi.MainActivityVM
import com.example.pollutionapi.R

class MainActivity : AppCompatActivity() {

    val viewModel = MainActivityVM()

    lateinit var stationView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stationView = findViewById(R.id.station_rv)

        val linearLayoutManager = LinearLayoutManager(this)
        stationView.layoutManager = linearLayoutManager
        stationView.adapter = StationAdapter(emptyList())

        viewModel.getStations().observe(this, Observer {
            (stationView.adapter as StationAdapter).load(it)
        })
    }

}