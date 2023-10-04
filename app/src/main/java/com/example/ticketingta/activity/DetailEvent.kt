package com.example.ticketingta.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.ticketingta.databinding.ActivityDetailEventBinding
import com.example.ticketingta.model.Event
import com.example.ticketingta.network.RetrofitClient
import com.example.ticketingta.viewmodel.DetailEventViewModel
import com.example.ticketingta.viewmodel.MyViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailEvent : AppCompatActivity() {

    private lateinit var binding: ActivityDetailEventBinding
    private lateinit var myViewModel: MyViewModel
    private lateinit var mDetailEventViewModel: DetailEventViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)

        //View Model untuk ambil data Event
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        mDetailEventViewModel = ViewModelProvider(this).get(DetailEventViewModel::class.java)

        //Intent untuk ambil data Event
        var intent = intent
        val eventId = intent.getIntExtra("eventId", 0)

        //Ambil data id Customer buat bikin pesanan
        val profil = getSharedPreferences("login_session", MODE_PRIVATE)
        val idCustomer: Int = profil.getInt("id_customer", 0)

        mDetailEventViewModel.apply {
            getIdCustomer(idCustomer)
            getIdEvent(eventId)
        }

        Log.d("Event ID Tes", "(Shared Prefrences) ID Event: ${eventId}")
        Log.d("Event ID Tes", "(ViewModel) ID Event: ${mDetailEventViewModel.idEvent}")


        // ambil data Event
//        getData(eventId)
//        myViewModel.getOneEvent(eventId)
        myViewModel.event.observe(this, Observer {
            //tampilkan data event
            showEvent(it)
        })

        setContentView(binding.root)

        // Jika button bayar ditekan
        binding.btnBayarDetailEvent.setOnClickListener {

            //Ambil data id Event buat bikin pesanan
//            val idEvent : Int? = myViewModel.event.value?.idEvent
            val idEvent = mDetailEventViewModel.idEvent
            val idCustomer = mDetailEventViewModel.idCustomer

            //Jika ada kesalahan pengambilan data customer atatu event, tidak jadi membuat data pemesanan dan pindah halaman
            if (idCustomer == 0 || idEvent == 0) {
                Toast.makeText(
                    this,
                    "Terjadi Kesalahan Pengambilan Data Customer atau Event",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            //Insert Pemesanan
            myViewModel.insertPemesanan(idCustomer, idEvent)

        }


        myViewModel.insertPemesananResponse.observe(this, Observer {

            //Jika Response Tidak kosong, lakukan pengecekan idPemesanan baru, baru pindah activity
            if (it != null) {
                //Jika Response Berhasil Maka Pindah Activity
                if (it.status == 1) {
                    //Ganti Activity
                    Log.d("Tes ID Pemesanan Baru", "(myVieModel) Id Pemesanan Baru = ${it.idPemesanan}")
                    mDetailEventViewModel.getIdPemesananTerbaru(it.idPemesanan)
                    Log.d(
                        "Tes ID Pemesanan Baru",
                        "(DetailEventViewModel) Id Pemesanan Baru = ${mDetailEventViewModel.idPemesananTerbaru.value}"
                    )
                    val idPemesananBaru = it.idPemesanan
                    val intent = Intent(this, PilihTiket::class.java)
                    intent.putExtra("idPemesananBaru", idPemesananBaru)
                    intent.putExtra("idEvent", mDetailEventViewModel.idEvent)
                    //Delete response dulu sebelum pindah activity
                    myViewModel.deleteInsertPemesananResponse()
                    startActivity(intent)
                }else{
                    //Jika Response Gagal
                    Toast.makeText(
                        this,
                        "Terjadi Kesalahan Pembuatan Pemesanan baru",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }


        })

        binding.icBack.setOnClickListener {
            finish()
        }
    }

    private fun showEvent(event: Event) {
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


}