package com.example.ticketingta.model

data class Event(
    val bannerEvent: String,
    val deskripsi: String,
    val gambarMapLokasi: String,
    val hargaTiket: Int,
    val jenisEvent: String,
    val idEvent: Int,
    val listArtis: List<Artis>,
    val lokasi: String,
    val nama: String,
    val stokTiket: Int,
    val tanggal: String,
    val waktu: String
)