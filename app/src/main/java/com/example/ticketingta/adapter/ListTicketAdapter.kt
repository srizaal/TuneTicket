package com.example.ticketingta.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketingta.R
import com.example.ticketingta.model.QRTiket
import com.example.ticketingta.model.response.HalamanListTiketSpesifikResponse


class ListTicketAdapter(context: Context): RecyclerView.Adapter<ListTicketAdapter.ViewHolder>(){

    private var ticketList = emptyList<QRTiket>()
    lateinit var response : HalamanListTiketSpesifikResponse


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val namaEvent : TextView = view.findViewById(R.id.tvNamaEvent)
        val tanggalEvent : TextView = view.findViewById(R.id.tvTanggalTiket)
        val jamEvent : TextView = view.findViewById(R.id.tvJam)
        val hargaTiketEvent : TextView = view.findViewById(R.id.tvHargaTiketDetail)
        val kodeBooking : TextView = view.findViewById(R.id.tvKodeBooking)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_detail_tiket, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tiket = ticketList[position]
        holder.namaEvent.text = response.namaEvent
        holder.tanggalEvent.text = response.tanggalEvent
        holder.jamEvent.text = response.jamEvent
        holder.hargaTiketEvent.text = response.hargaTiket
        holder.kodeBooking.text = tiket.idQRTiket.toString()
    }

    fun setData(response: HalamanListTiketSpesifikResponse) {
        this.ticketList = response.listQRTiket
        this.response = response
        Log.d("TicketlList", ticketList.toString())
        notifyDataSetChanged()
    }
}