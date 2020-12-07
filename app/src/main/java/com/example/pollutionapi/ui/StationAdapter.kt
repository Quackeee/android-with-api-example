package com.example.pollutionapi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pollutionapi.R
import com.example.pollutionapi.data.model.Station

class StationAdapter(private var data: List<Station>): RecyclerView.Adapter<StationAdapter.StationHolder>() {

    class StationHolder(view: View): RecyclerView.ViewHolder(view) {
        val idTextView: TextView = view.findViewById(R.id.id_tv)
        val stationNameTextView: TextView = view.findViewById(R.id.station_name_tv)
        val cityNameTextView: TextView = view.findViewById(R.id.city_name_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.station_row, parent,false)
        return StationHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: StationHolder, position: Int) {
        val station = data[position]

        holder.idTextView.text = station.id.toString()
        holder.stationNameTextView.text = station.stationName
        holder.cityNameTextView.text = station.city?.name
    }

    fun load(list: List<Station>) {
        data = list
        notifyDataSetChanged()
    }
}