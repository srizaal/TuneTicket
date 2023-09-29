package com.example.ticketingta.model.response

data class ListTiketResponseItem (
    val hargaTiket: String,
    val idCustomer: String,
    val idPemesanan: String,
    val jamEvent: String,
    val jumlahTiket: String,
    val listQRTiket: String,
    val lokasiEvent: String,
    val namaEvent: String,
    val tanggalEvent: String
    )
