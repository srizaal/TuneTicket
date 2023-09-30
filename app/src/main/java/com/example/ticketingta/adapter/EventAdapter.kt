package com.example.ticketingta.adapter

import android.content.Context
import android.content.Intent
import android.telecom.Call.Details
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ticketingta.MainActivity
import com.example.ticketingta.R
import com.example.ticketingta.activity.DetailEvent
import com.example.ticketingta.model.Event
import com.example.ticketingta.urusandata.MyViewModel


class EventAdapter(
    private val fragment: Fragment,
    private val context: Context,
    private val myViewModel: MyViewModel
) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {


    private var eventList = emptyList<Event>()


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
        holder.txtTanggalTiket.text = event.tanggal
        holder.txtLokasi.text = event.lokasi
        holder.txtHargaTiket.text = event.hargaTiket.toString()
        val namaGambar = event.bannerEvent
        val parts = namaGambar?.split(".")
        val namaGambarTanpaEkstensi = parts?.first()
        val gambarResID = context.resources.getIdentifier(
            namaGambarTanpaEkstensi,
            "drawable",
            context.packageName
        )
        holder.imgEvent.setImageResource(gambarResID)


        holder.cardEvent.setOnClickListener {

            event.idEvent?.let { it1 -> myViewModel.getOneEvent(it1.toInt()) }

            //Ganti Activity
            val intent = Intent(context, DetailEvent::class.java)
            intent.putExtra("eventId", event.idEvent)
            context.startActivity(intent)

        }


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNamaEvent: TextView = view.findViewById(R.id.tvNamaEvent)
        val txtTanggalTiket: TextView = view.findViewById(R.id.tvTanggalTiket)
        val txtLokasi: TextView = view.findViewById(R.id.tvLokasi)
        val txtHargaTiket: TextView = view.findViewById(R.id.tvHargaTiket)
        val imgEvent: ImageView = view.findViewById(R.id.imgEvent)
        val cardEvent: CardView = view.findViewById(R.id.cardViewEvent)
    }

    fun setData(daftarEvent: List<Event>) {
        this.eventList = daftarEvent
        Log.d("EventList", daftarEvent.toString())
        notifyDataSetChanged()
    }
}
