package com.example.ticketingta.model

data class Transaksi(
    val caraPembayaran: String,
    val event: Event,
    val jumlahPemesananTiket: Int,
    val kodeTransaksi: Int,
    val listTiket: List<Tiket>,
    val totalPembayaran: Int,
    val waktuTransaksi: String,
    val waktuBatasAkhirPembayaran: String,
    val waktuPembayaran: String,
    val statusPembayaran: String
)