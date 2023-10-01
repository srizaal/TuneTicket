package com.example.ticketingta.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ticketingta.R
import com.example.ticketingta.model.Event
import com.example.ticketingta.model.response.ListTiketResponseItem

class GroupTicketAdapter(context: Context): RecyclerView.Adapter<GroupTicketAdapter.ViewHolder>(){

    private var ticketGroupList = emptyList<ListTiketResponseItem>()


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val namaEvent : TextView = view.findViewById(R.id.tvNamaEvent)
        val tanggalEvent : TextView = view.findViewById(R.id.tvTanggalTiket)
        val lokasiEvent : TextView = view.findViewById(R.id.tvLokasi)
        val jamEvent : TextView = view.findViewById(R.id.tvJam)
        val kodeBooking : TextView = view.findViewById(R.id.tvKodeBooking)
        val jumlahTiket : TextView = view.findViewById(R.id.tvQtyTiket)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupTicketAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_group_ticket, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ticketGroupList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = ticketGroupList[position]
        holder.namaEvent.text = item.namaEvent
        holder.tanggalEvent.text = item.tanggalEvent
        holder.lokasiEvent.text = item.lokasiEvent
        holder.jamEvent.text = item.jamEvent
        holder.kodeBooking.text = item.listQRTiket
        holder.jumlahTiket.text = item.jumlahTiket
    }


    fun setData(ticketGroupList: List<ListTiketResponseItem>) {
        this.ticketGroupList = ticketGroupList
        Log.d("EventList", ticketGroupList.toString())
        notifyDataSetChanged()
    }


}