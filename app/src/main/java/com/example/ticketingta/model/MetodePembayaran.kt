package com.example.ticketingta.model

data class MetodePembayaran(
    val biayaTransfer: Int,
    val deskripsi: String,
    val idMetodePembayaran: Int,
    val gambar : String,
    val nama: String,
    val noRekeningTransfer: String
)