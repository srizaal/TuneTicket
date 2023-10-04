package com.example.ticketingta.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ticketingta.MainActivity
import com.example.ticketingta.databinding.ActivityPaymentBerhasilBinding
import com.example.ticketingta.model.response.HalamanPaymentResponse
import com.example.ticketingta.viewmodel.MyViewModel
import com.example.ticketingta.viewmodel.PaymentViewModel
import kotlin.random.Random

class PaymentBerhasil : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBerhasilBinding
    private lateinit var myViewModel: MyViewModel
    private lateinit var mPaymentViewModel: PaymentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBerhasilBinding.inflate(layoutInflater)

        //View Model untuk ambil data pembayaran
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        mPaymentViewModel =
            ViewModelProvider(this).get(PaymentViewModel::class.java)

        //Intent untuk ambil data
        val intent = intent
        val idPemesanan = intent.getIntExtra("idPemesananBaru", 0)
        val idPembayaran = intent.getIntExtra("idPembayaranBaru", 0)

        myViewModel.halamanPaymentResponse.observe(this, Observer {
            showDataHalaman(it)
            mPaymentViewModel.jumlahTiketPemesanan = it.pembayaran.jumlahPemesananTiket!!
            Log.d("Tes Jumlah Tiket Pemesaan", "Jumlah Tiket = ${mPaymentViewModel.jumlahTiketPemesanan}")
        })

        setContentView(binding.root)


        binding.btnPaymentBerhasil.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.putExtra("fragmentToDisplay", "Tiket")
            startActivity(intent)
        }

        binding.icClose.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

    }

    private fun createQRTiket(idPembayaran: Int) {
        val jumlahTiket = mPaymentViewModel.jumlahTiketPemesanan
        for (i in 1..jumlahTiket) {
            val idQR = generateRandomNumber()
            val gambarQR = "${idQR}.jpg"
            val statusPemakaian = "Belum Dipakai"
            myViewModel.insertQRTicket(
                idQR = idQR,
                gambarQR = gambarQR,
                idPembayaran = idPembayaran,
                statusPemakaian = statusPemakaian
            )
        }
    }

    private fun generateRandomNumber(): Int {
        val lowerBound = 10000
        val upperBound = 99999
        // Menghasilkan angka acak 5 digit
        return Random.nextInt(lowerBound, upperBound + 1)
    }


    private fun showDataHalaman(halamanPaymentResponse: HalamanPaymentResponse) {
        val event = halamanPaymentResponse.event

        binding.tvNamaEvent.text = event.nama
        binding.tvTanggalTiket.text = event.tanggal
        binding.tvLokasi.text = event.lokasi
        binding.tvJam.text = event.waktu
        event.bannerEvent?.let { getGambarResID(it) }?.let { binding.imgEvent.setImageResource(it) }

        val pembayaran = halamanPaymentResponse.pembayaran
        val metodePembayaran = pembayaran.metodePembayaran

        if (metodePembayaran != null) {
            binding.tvVAPembayaran.text = metodePembayaran.noRekeningTransfer
            binding.tvNamaPembayaran.text = metodePembayaran.nama?.let {
                getDetailMetodePembayaran(it)
            }
            metodePembayaran.gambar?.let { getGambarResID(it) }
                ?.let { binding.imgPembayaran.setImageResource(it) }
            binding.tvBiayaAdmin.text = metodePembayaran.biayaTransfer.toString()
        }

        binding.tvHargaTiketDetail.text = event.hargaTiket.toString()
        binding.tvQtyTiket.text = pembayaran.jumlahPemesananTiket.toString()
        binding.tvTotalHargaTiket.text =
            "${event.hargaTiket?.times(pembayaran.jumlahPemesananTiket!!)}"
        binding.tvTotalPembayaran.text = pembayaran.totalPembayaran.toString()
    }

    private fun getGambarResID(namaGambar: String): Int {
        val parts = namaGambar.split(".")
        val namaGambarTanpaEkstensi = parts.first()
        return resources.getIdentifier(namaGambarTanpaEkstensi, "drawable", packageName)
    }

    fun getDetailMetodePembayaran(namaMetodePembayaran: String = ""): String {
        val detailMetodePembayaran = when (namaMetodePembayaran) {
            "BCA" -> "BCA Virtual Account"
            "Mandiri" -> "Mandiri Virtual Account"
            "BRI" -> "BRI Virtual Account"
            "Dana" -> "Dana Instant Payment"
            "Gopay" -> "Gopay Instant Payment"
            "Ovo" -> "Ove Instant Payment"
            "Shopee Pay" -> "Shopee Instant Payment"
            else -> "VIRTUAL ACCOUNT"
        }
        return detailMetodePembayaran
    }
}