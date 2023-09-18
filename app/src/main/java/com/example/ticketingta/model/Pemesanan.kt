package com.example.ticketingta.model

data class Pemesanan(
    val idPemesanan: Int,
    val event: Event,
    val waktuPemesanan: String,
    val tanggalPemesanan: String,
    val pembayaran: Pembayaran,
)