package com.example.ticketingta.model

data class QRTiket(
    val gambarQR: String,
    val idQRTiket: Int,
    val statusPemakaianTiket: String,
    val tanggalCheckin: String,
    val waktuCheckin: String
)