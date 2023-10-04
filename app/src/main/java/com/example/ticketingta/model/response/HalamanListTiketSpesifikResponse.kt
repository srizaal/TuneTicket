package com.example.ticketingta.model.response

import com.example.ticketingta.model.QRTiket

data class HalamanListTiketSpesifikResponse(
    val hargaTiket: String,
    val idCustomer: String,
    val idPemesanan: String,
    val jamEvent: String,
    val jumlahTiket: String,
    val listQRTiket: List<QRTiket>,
    val lokasiEvent: String,
    val namaEvent: String,
    val tanggalEvent: String
)
