package com.example.ticketingta.model.response

data class ListTiketResponseItem (
    val hargaTiket: String,
    val idCustomer: Int,
    val idPemesanan: Int,
    val jamEvent: String,
    val jumlahTiket: String,
    val listQRTiket: String,
    val lokasiEvent: String,
    val namaEvent: String,
    val tanggalEvent: String
    )
