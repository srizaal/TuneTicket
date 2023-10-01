package com.example.ticketingta.model

import com.google.gson.annotations.SerializedName

data class MetodePembayaran(

    @SerializedName("idMetodePembayaran") val idMetodePembayaran: Int?,
    @SerializedName("nama") val nama: String?,
    @SerializedName("gambar") val gambar: String?,
    @SerializedName("deskripsi") val deskripsi: String?,
    @SerializedName("noRekeningTransfer") val noRekeningTransfer: String?,
    @SerializedName("biayaTransfer") val biayaTransfer: Int?
)