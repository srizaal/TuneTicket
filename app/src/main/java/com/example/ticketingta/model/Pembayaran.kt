package com.example.ticketingta.model

import com.google.gson.annotations.SerializedName

data class Pembayaran(
    val idPembayaran: Int?,
    @SerializedName("jumlahTiket") val jumlahPemesananTiket: Int?,
    @SerializedName("metodePembayaran") val metodePembayaran: MetodePembayaran?,
    @SerializedName("totalPembayaran") val totalPembayaran: Int?,
    val waktuPembayaran: String?,
    val tanggalPembayaran: String?,
    val statusPembayaran: String?,
    val listQRTiket: List<QRTiket>?,
    val pemesanan: Pemesanan?
)
