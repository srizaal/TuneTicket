package com.example.ticketingta.model

data class Pembayaran(
    val idPembayaran: Int,
    val jumlahPemesananTiket: Int,
    val metodePembayaran: MetodePembayaran,
    val totalPembayaran: Int,
    val waktuPembayaran: String,
    val tanggalPembayaran: String,
    val statusPembayaran: String,
    val listQRTiket: List<QRTiket>,
    val pemesanan: Pemesanan
)
