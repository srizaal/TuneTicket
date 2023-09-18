package com.example.ticketingta.dummydata

import com.example.ticketingta.model.MetodePembayaran

val metodePembayaranList = listOf(
    MetodePembayaran(
        idMetodePembayaran = 1,
        nama = "BCA",
        gambar = "bca.png",
        deskripsi = "Pembayaran melalui transfer Bank BCA",
        noRekeningTransfer = "123-456-7890",
        biayaTransfer = 3500
    ),
    MetodePembayaran(
        idMetodePembayaran = 2,
        nama = "Mandiri",
        gambar = "mandiri.png",
        deskripsi = "Pembayaran melalui transfer Bank Mandiri",
        noRekeningTransfer = "987-654-3210",
        biayaTransfer = 2800
    ),
    MetodePembayaran(
        idMetodePembayaran = 3,
        nama = "BRI",
        gambar = "bri.png",
        deskripsi = "Pembayaran melalui transfer Bank BRI",
        noRekeningTransfer = "456-789-0123",
        biayaTransfer = 3000
    ),
    MetodePembayaran(
        idMetodePembayaran = 4,
        nama = "Dana",
        gambar = "dana.png",
        deskripsi = "Pembayaran melalui Dana",
        noRekeningTransfer = "0812-3456-7890",
        biayaTransfer = 2950
    ),
    MetodePembayaran(
        idMetodePembayaran = 5,
        nama = "Gopay",
        gambar = "gopay.png",
        deskripsi = "Pembayaran melalui Gopay",
        noRekeningTransfer = "0876-5432-1098",
        biayaTransfer = 3300
    ),
    MetodePembayaran(
        idMetodePembayaran = 6,
        nama = "Ovo",
        gambar = "ovo.png",
        deskripsi = "Pembayaran melalui Ovo",
        noRekeningTransfer = "0890-1234-5678",
        biayaTransfer = 4750
    ),
    MetodePembayaran(
        idMetodePembayaran = 7,
        nama = "Shopee Pay",
        gambar = "shopee.png",
        deskripsi = "Pembayaran melalui Shopee Pay",
        noRekeningTransfer = "0810-9876-5432",
        biayaTransfer = 3600
    )
)
