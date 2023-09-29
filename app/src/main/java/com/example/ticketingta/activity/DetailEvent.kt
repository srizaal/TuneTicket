package com.example.ticketingta.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ticketingta.R
import com.example.ticketingta.databinding.ActivityDetailEventBinding
import com.example.ticketingta.model.Event
import com.example.ticketingta.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailEvent : AppCompatActivity() {

    private lateinit var binding : ActivityDetailEventBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = intent
        val eventId = intent.getIntExtra("eventId", 0)

        Log.d("Event ID Tes", "ID Event: ${eventId}")

        getData(eventId)


    }

    private fun getData(eventId : Int) {
        val api = RetrofitClient().getInstance()
        api.getOneEvent(eventId).enqueue(object : Callback<Event>{
            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                if (response.isSuccessful){
                    val event = response.body()
                    if (event != null) {
                        binding.tvNamaEvent.text = event.nama
                        binding.tvTanggalTiket.text = event.tanggal
                        binding.tvLokasi.text = event.lokasi
                        binding.tvKategori.text = event.jenisEvent
                        binding.tvJam.text = event.waktu
                        binding.tvSisaTiket.text = event.stokTiket.toString()
                        binding.tvHargaTiket.text = event.hargaTiket.toString()
                        binding.tvHargaTiketDetail.text = event.hargaTiket.toString()
                        binding.tvDescEvent.text = event.deskripsi

//                        val namaGambarEvent = event.bannerEvent
//                        val parts = namaGambarEvent?.split(".")
//                        val namaGambarTanpaEkstensi = parts?.first()
//                        val gambarResID = context.resources.getIdentifier(namaGambarTanpaEkstensi, "drawable", context.packageName)
//                        binding.imgEvent.setImageResource(gambarResID)

//                        val namaGambarLokasi = event.gambarMapLokasi
//                        val parts2 = namaGambarEvent?.split(".")
//                        val namaGambarTanpaEkstensi2 = parts2?.first()
//                        val gambarResID2 = context.resources.getIdentifier(namaGambarTanpaEkstensi2, "drawable", context.packageName)
//                        binding.imgLokasi.setImageResource(gambarResID2)

                    }
                }
            }

            override fun onFailure(call: Call<Event>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
            }
        })
    }
}