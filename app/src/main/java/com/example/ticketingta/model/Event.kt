package com.example.ticketingta.model

import com.google.gson.annotations.SerializedName

data class Event(
//    val bannerEvent: String,
//    val deskripsi: String,
//    val gambarMapLokasi: String,
//    val hargaTiket: Int,
//    val jenisEvent: String,
//    val idEvent: Int,
//    val listArtis: List<Artis>,
//    val lokasi: String,
//    val nama: String,
//    val stokTiket: Int,
//    val tanggal: String,
//    val waktu: String,
    @SerializedName("idEvent") val idEvent: Int?,
    @SerializedName("nama") val nama: String?,
    @SerializedName("bannerEvent") val bannerEvent: String?,
    @SerializedName("deskripsi") val deskripsi: String?,
    @SerializedName("jenisEvent") val jenisEvent: String?,
    @SerializedName("tanggal") val tanggal: String?,
    @SerializedName("waktu") val waktu: String?,
    @SerializedName("lokasi") val lokasi: String?,
    @SerializedName("gambarMapLokasi") val gambarMapLokasi: String?,
    @SerializedName("hargaTiket") val hargaTiket: Int?,
    @SerializedName("stokTiket") val stokTiket: Int?,
    @SerializedName("listArtis") val listArtis: List<Artis>?
)