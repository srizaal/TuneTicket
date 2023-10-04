package com.example.ticketingta.model.response

import com.google.gson.annotations.SerializedName

data class InsertPembayaranResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("id_pembayaran") val idPembayaran: Int
)
