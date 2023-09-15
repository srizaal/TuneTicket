package com.example.ticketingta.model

data class Tiket(
    val gambarQR: String,
    val kodeTiket: Int,
    val statusPemakaianTiket: String,
    val tanggalCheckin: String,
    val waktuCheckin: String
)