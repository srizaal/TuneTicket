package com.example.ticketingta.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.ticketingta.R
import com.example.ticketingta.databinding.ActivityPaymentKonfirmasiBinding
import com.example.ticketingta.model.response.HalamanPaymentResponse
import com.example.ticketingta.viewmodel.MyViewModel

class PaymentKonfirmasi : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentKonfirmasiBinding
    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentKonfirmasiBinding.inflate(layoutInflater)

        //View Model untuk ambil data Event
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        //Intent untuk ambil data
        val intent = intent
        val idPemesanan = intent.getIntExtra("idPemesananBaru", 0)
        val idPembayaran = intent.getIntExtra("idPembayaranBaru", 0)

        myViewModel.halamanPaymentResponse.observe(this, Observer {
            showDataHalaman(it)
        })

        setContentView(binding.root)


        binding.icBack.setOnClickListener {
            //Menampilkan Dialog konfirmasi pembatalan pemesanan
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Apakah anda ingin membatalkan pembayaran?").setCancelable(false)
                .setPositiveButton("Ya") { dialog, id -> deletePembayaran(idPembayaran) }
                .setNegativeButton("Tidak") { dialog, id -> }
            val alerts = builder.create()
            alerts.show()
        }

        binding.btnPembayaranKonfirmasi.setOnClickListener {
            myViewModel.updatePembayaran(idPembayaran)
            val intent = Intent(this, PaymentBerhasil::class.java)
            intent.putExtra("idPembayaranBaru", idPembayaran)
            intent.putExtra("idPemesananBaru", idPemesanan)
            startActivity(intent)
        }

    }

    private fun showDataHalaman(halamanPaymentResponse: HalamanPaymentResponse){
        val event = halamanPaymentResponse.event

        binding.tvNamaEvent.text = event.nama
        binding.tvTanggalTiket.text = event.tanggal
        binding.tvLokasi.text = event.lokasi
        binding.tvJam.text = event.waktu
        event.bannerEvent?.let { getGambarResID(it) }?.let { binding.imgEvent.setImageResource(it) }

        val pembayaran = halamanPaymentResponse.pembayaran
        val metodePembayaran = pembayaran.metodePembayaran

        if (metodePembayaran != null) {
            binding.tvMetodePembayaran.text = metodePembayaran.nama
            binding.tvMetodePembayaranDetail.text = metodePembayaran.nama?.let {
                getDetailMetodePembayaran(it)
            }
            binding.tvBiayaAdmin.text = metodePembayaran.biayaTransfer.toString()
        }

        binding.tvHargaTiket.text = event.hargaTiket.toString()
        binding.tvQtyTiket.text = pembayaran.jumlahPemesananTiket.toString()
        binding.tvTotalHargaTiket.text = "${event.hargaTiket?.times(pembayaran.jumlahPemesananTiket!!)}"
        binding.tvTotalPembayaran.text = pembayaran.totalPembayaran.toString()
    }

    private fun getGambarResID(namaGambar: String): Int {
        val parts = namaGambar.split(".")
        val namaGambarTanpaEkstensi = parts.first()
        return resources.getIdentifier(namaGambarTanpaEkstensi, "drawable", packageName)
    }

    fun getDetailMetodePembayaran(namaMetodePembayaran: String = ""):String{
        val detailMetodePembayaran = when(namaMetodePembayaran){
            "BCA" -> "BCA VIRTUAL ACCOUNT"
            "Mandiri" -> "MANDIRI VIRTUAL ACCOUNT"
            "BRI" -> "BRI VIRTUAL ACCOUNT"
            "Dana" -> "DANA INSTANT PAYMENT"
            "Gopay" -> "GOPAY INSTANT PAYMENT"
            "Ovo" -> "OVO INSTANT PAYMENT"
            "Shopee Pay" -> "SHOPEE PAY INSTANT PAYMENT"
            else -> "VIRTUAL ACCOUNT"
        }
        return detailMetodePembayaran
    }

    private fun deletePembayaran(idPembayaran: Int) {
        myViewModel.deletePembayaran(idPembayaran)
        finish()
    }

    override fun onBackPressed() {
        val intent = intent
        val idPembayaran = intent.getIntExtra("idPembayaranBaru", 0)
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Apakah anda ingin membatalkan pembayaran?").setCancelable(false)
            .setPositiveButton("Ya") { dialog, id -> deletePembayaran(idPembayaran) }
            .setNegativeButton("Tidak") { dialog, id -> }
        val alerts = builder.create()
        alerts.show()
    }



}