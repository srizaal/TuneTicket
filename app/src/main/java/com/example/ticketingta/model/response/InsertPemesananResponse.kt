package com.example.ticketingta.model.response

import com.google.gson.annotations.SerializedName

data class InsertPemesananResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("id_pemesanan") val idPemesanan: Int,
    @SerializedName("id_customer") val idCustomer: Int?,
    @SerializedName("id_event") val idEvent: Int?
)
