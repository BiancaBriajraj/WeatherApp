package com.example.weatherapp.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherInfo
import com.example.weatherapp.view.ListFragmentDirections
import org.w3c.dom.Text

class ItemAdapter(private val infoList: ArrayList<WeatherInfo>): RecyclerView.Adapter<ItemAdapter.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_value, parent, false)

        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val currentInfo: WeatherInfo = infoList[position]

        holder.itemView.findViewById<TextView>(R.id.itemCountryName).text = infoList[position].name

    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    fun update(newList: List<WeatherInfo>){
        infoList.clear()
        infoList.addAll(newList)
        notifyDataSetChanged()

    }


}