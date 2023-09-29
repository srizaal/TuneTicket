package com.example.ticketingta.model

import com.google.gson.annotations.SerializedName

data class MetodePembayaran(

    @SerializedName("id_metode_pembayaran") val idMetodePembayaran: Int?,
    @SerializedName("nama") val nama: String?,
    @SerializedName("gambar") val gambar: String?,
    @SerializedName("deskripsi") val deskripsi: String?,
    @SerializedName("no_rekening_transfer") val noRekeningTransfer: String?,
    @SerializedName("biaya_transfer") val biayaTransfer: Int?
)