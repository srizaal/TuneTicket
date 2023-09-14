package com.example.ticketingta.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ticketingta.R
import com.example.ticketingta.model.Event
import de.hdodenhof.circleimageview.CircleImageView

class EventAdapter (private val context: Context, private val eventList: ArrayList<Event>):
    RecyclerView.Adapter<EventAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_event, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: EventAdapter.ViewHolder, position: Int) {
        val event = eventList[position]
        holder.txtNamaEvent.text = event.nama
        Glide.with(context).load(user.)a


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNamaEvent: TextView = view.findViewById(R.id.tvNamaEvent)
        val txtTanggalTiket: TextView = view.findViewById(R.id.tvTanggalTiket)
        val txtLokasi: TextView = view.findViewById(R.id.tvLokasi)
        val txtHargaTiket: TextView = view.findViewById(R.id.tvHargaTiket)
    }
}
)