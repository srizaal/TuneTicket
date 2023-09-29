package com.example.ticketingta.model.response

import com.google.gson.annotations.SerializedName

data class DeletePembayaranResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
)
