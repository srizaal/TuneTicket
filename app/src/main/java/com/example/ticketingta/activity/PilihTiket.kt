package com.example.ticketingta.activity

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.MutableInt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ticketingta.R
import com.example.ticketingta.databinding.ActivityPilihTiketBinding
import com.example.ticketingta.model.Event
import com.example.ticketingta.urusandata.MyViewModel
import com.example.ticketingta.urusandata.PilihTiketViewModel

class PilihTiket : AppCompatActivity() {

    private lateinit var binding: ActivityPilihTiketBinding
    private lateinit var myViewModel: MyViewModel
    private lateinit var mPilihTiketViewModel: PilihTiketViewModel
    // Deklarasikan properti untuk menyimpan data event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPilihTiketBinding.inflate(layoutInflater)

        //View Model untuk ambil data Event
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        mPilihTiketViewModel = ViewModelProvider(this).get(PilihTiketViewModel::class.java)

        //Intent untuk ambil data Event dan data pemesanan terbaru
        val intent = intent
        val idEvent = intent.getIntExtra("idEvent", 0)
        val idPemesanan = intent.getIntExtra("idPemesananBaru", 0)


        Log.d("Event ID Tes", "ID Event: $idEvent")
        Log.d("Pemesanan ID Tes", "ID Pemesanan: $idPemesanan")


        // tampilkan data event
        myViewModel.event.observe(this, Observer {
            showEvent(it)
            mPilihTiketViewModel.getHargaTiket(it)
        })

//        var jumlahTiket = 1

        mPilihTiketViewModel.ticketCount.observe(this, Observer {
            binding.tvQtyTiket.text = it.toString()
        })
        mPilihTiketViewModel.hargaTotal.observe(this, Observer {
            binding.tvTotalHargaTiket.text = it.toString()
        })

        setContentView(binding.root)

        binding.qtyKurang.setOnClickListener {
//            if(jumlahTiket > 1){
//                jumlahTiket--
//                Log.d("Tes Jumlah Tiket", "Jumlah Tiket : $jumlahTiket")
//                binding.tvQtyTiket.text = jumlahTiket.toString()
//                binding.tvTotalHargaTiket.text = (jumlahTiket * eventData?.hargaTiket!!).toString()
//            }
            mPilihTiketViewModel.kurangiJumlahTiket()
            val jumlahTiket = mPilihTiketViewModel.ticketCount.value
            Log.d("Tes Jumlah Tiket", "Jumlah Tiket : $jumlahTiket")
        }

        binding.qtyTambah.setOnClickListener {
//            jumlahTiket++
//            Log.d("Tes Jumlah Tiket", "Jumlah Tiket : $jumlahTiket")
//            binding.tvQtyTiket.text = jumlahTiket.toString()
//            binding.tvTotalHargaTiket.text = (jumlahTiket * eventData?.hargaTiket!!).toString()
            mPilihTiketViewModel.tambahJumlahTiket()

            val jumlahTiket = mPilihTiketViewModel.ticketCount.value
            Log.d("Tes Jumlah Tiket", "Jumlah Tiket : $jumlahTiket")
        }

//        binding.tvQtyTiket.text = jumlahTiket.toString()
//        binding.tvTotalHargaTiket.text = (jumlahTiket * eventData?.hargaTiket!!).toString()


        //jika button back ditekan
        binding.icBack.setOnClickListener {
            //Menampilkan Dialog konfirmasi pembatalan pemesanan
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Apakah anda ingin membatalkan pemesanan?").setCancelable(false)
                .setPositiveButton("Ya") { dialog, id -> deletePemesanan(idPemesanan) }
                .setNegativeButton("Tidak") { dialog, id -> }
            val alerts = builder.create()
            alerts.show()
        }

    }

    private fun showEvent(event: Event) {
        binding.tvNamaEvent.text = event.nama
        binding.tvTanggalTiket.text = event.tanggal
        binding.tvHargaTiketDetail.text = event.hargaTiket.toString()
        binding.tvTotalHargaTiket.text = event.hargaTiket.toString()
    }

    private fun deletePemesanan(idPemesanan: Int) {
        myViewModel.deletePemesanan(idPemesanan)
        finish()
    }

    override fun onBackPressed() {
        val intent = intent
        val idPemesanan = intent.getIntExtra("idPemesananBaru", 0)
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Apakah anda ingin membatalkan pemesanan?").setCancelable(false)
            .setPositiveButton("Ya") { dialog, id -> deletePemesanan(idPemesanan) }
            .setNegativeButton("Tidak") { dialog, id -> }
        val alerts = builder.create()
        alerts.show()
    }

}