package com.example.ticketingta.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ticketingta.databinding.ActivityDetailEventBinding
import com.example.ticketingta.model.Event
import com.example.ticketingta.network.RetrofitClient
import com.example.ticketingta.urusandata.MyViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailEvent : AppCompatActivity() {

    private lateinit var binding: ActivityDetailEventBinding
    private lateinit var mViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)


        //View Model untuk ambil data Event
        val myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        //Intent untuk ambil data Event
        var intent = intent
        val eventId = intent.getIntExtra("eventId", 0)

        Log.d("Event ID Tes", "ID Event: ${eventId}")

        // ambil data Event
//        myViewModel.getOneEvent(eventId)
        myViewModel.event.observe(this, Observer {
            //tampilkan data event
            showEvent(it)
        })

        setContentView(binding.root)

//        getData(eventId)

        binding.btnBayarDetailEvent.setOnClickListener {
            //Ganti Activity
            val intent = Intent(this, PilihTiket::class.java)
//            intent.putExtra("eventId", event.idEvent)
            startActivity(intent)
        }


    }

    private fun getGambarResID(namaGambar: String): Int {
        val parts = namaGambar.split(".")
        val namaGambarTanpaEkstensi = parts.first()
        return resources.getIdentifier(namaGambarTanpaEkstensi, "drawable", packageName)
    }


    private fun getData(eventId: Int) {
        val api = RetrofitClient().getInstance()
        api.getOneEvent(eventId).enqueue(object : Callback<Event> {
            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                if (response.isSuccessful) {
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
                        event.bannerEvent?.let { getGambarResID(it) }
                            ?.let { binding.imgEvent.setImageResource(it) }
                        event.gambarMapLokasi?.let {
                            getGambarResID(it)
                        }?.let { binding.imgLokasi.setImageResource(it) }

                        val artis1 = event.listArtis?.get(0)
                        if (artis1 != null) {
                            binding.namaArtist1.text = artis1.nama
                            binding.imageArtist1.setImageResource(getGambarResID(artis1.foto))
                        }
                        val artis2 = event.listArtis?.get(1)
                        if (artis2 != null) {
                            binding.namaArtist2.text = artis2.nama
                            binding.imageArtist2.setImageResource(getGambarResID(artis2.foto))
                        }
                        val artis3 = event.listArtis?.get(2)
                        if (artis3 != null) {
                            binding.namaArtist3.text = artis3.nama
                            binding.imageArtist3.setImageResource(getGambarResID(artis3.foto))
                        }

                        binding.namaArtist4.text = ""
//                        binding.imageArtist4.isVisible = false
                        binding.cardViewArtis4.isVisible = false



                    }
                }
            }

            override fun onFailure(call: Call<Event>, t: Throwable) {
                Log.e("API Error", "Error: ${t.message}")
            }
        })
    }

    private fun showEvent(event: Event){
        binding.tvNamaEvent.text = event.nama
        binding.tvTanggalTiket.text = event.tanggal
        binding.tvLokasi.text = event.lokasi
        binding.tvKategori.text = event.jenisEvent
        binding.tvJam.text = event.waktu
        binding.tvSisaTiket.text = event.stokTiket.toString()
        binding.tvHargaTiket.text = event.hargaTiket.toString()
        binding.tvHargaTiketDetail.text = event.hargaTiket.toString()
        binding.tvDescEvent.text = event.deskripsi
        event.bannerEvent?.let { getGambarResID(it) }
            ?.let { binding.imgEvent.setImageResource(it) }
        event.gambarMapLokasi?.let {
            getGambarResID(it)
        }?.let { binding.imgLokasi.setImageResource(it) }

        val artis1 = event.listArtis?.get(0)
        if (artis1 != null) {
            binding.namaArtist1.text = artis1.nama
            binding.imageArtist1.setImageResource(getGambarResID(artis1.foto))
        }
        val artis2 = event.listArtis?.get(1)
        if (artis2 != null) {
            binding.namaArtist2.text = artis2.nama
            binding.imageArtist2.setImageResource(getGambarResID(artis2.foto))
        }
        val artis3 = event.listArtis?.get(2)
        if (artis3 != null) {
            binding.namaArtist3.text = artis3.nama
            binding.imageArtist3.setImageResource(getGambarResID(artis3.foto))
        }

        binding.namaArtist4.text = ""
//                        binding.imageArtist4.isVisible = false
        binding.cardViewArtis4.isVisible = false
    }

}