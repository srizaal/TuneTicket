package com.example.ticketingta.urusandata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ticketingta.model.Event
import com.example.ticketingta.model.MetodePembayaran
import com.example.ticketingta.model.response.HalamanListTiketResponse
import com.example.ticketingta.model.response.HalamanListTiketSpesifikResponse
import com.example.ticketingta.model.response.HalamanPaymentResponse
import com.example.ticketingta.model.response.InsertPembayaranResponse
import com.example.ticketingta.model.response.InsertPemesananResponse
import com.example.ticketingta.model.response.PembayaranResponse
import com.example.ticketingta.model.response.PemesananResponse
import com.example.ticketingta.network.ApiClient
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception


class Repository(private val apiClient: ApiClient) {


    // LiveData untuk satu event
    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event> = _event

    // Fungsi untuk mendapatkan satu event berdasarkan kode
    suspend fun getOneEvent(kode: Int) {
        try {
            val call = apiClient.getOneEvent(kode)
            val response = call.execute()
            if (response.isSuccessful) {
                _event.postValue(response.body())
                val tempEvent = response.body()
                if (tempEvent != null) {
                    printOneEvent(tempEvent)
                }
            } else {
                // Tangani kegagalan respons di sini
                handleApiError(response)

            }
        } catch (e: Exception) {
            handleNetworkError(e)
        }
    }

    // LiveData untuk daftar event
    private val _eventList = MutableLiveData<List<Event>>()
    val eventList: LiveData<List<Event>> = _eventList

    // Fungsi untuk mendapatkan daftar event
    suspend fun getListEvent() {
        try {
            val call = apiClient.getListEvent()
            val response = call.execute()
            if (response.isSuccessful) {
                _eventList.postValue(response.body())
                //print di log
                val listEvent = response.body()
                if (listEvent != null) {
                    printEventList(listEvent)
                }
            } else {
                // Tangani kegagalan respons di sini
                handleApiError(response)
            }
        } catch (e: Exception) {
            // Tangani kesalahan jaringan atau kesalahan lainnya
            handleNetworkError(e)
        }
    }

    // LiveData untuk menampung hasil getMetodePembayaran
    private val _metodePembayaranList = MutableLiveData<List<MetodePembayaran>>()
    val metodePembayaranList: LiveData<List<MetodePembayaran>> = _metodePembayaranList

    // Fungsi untuk mendapatkan metode pembayaran
    suspend fun getMetodePembayaran(id: Int? = null) {
        try {
            val call = apiClient.getMetodePembayaran(id)
            val response = call.execute()
            if (response.isSuccessful) {
                _metodePembayaranList.postValue(response.body())
            } else {
                // Tangani kegagalan respons
                handleApiError(response)
            }
        } catch (e: Exception) {
            // Tangani kesalahan jaringan atau kesalahan lainnya
            handleNetworkError(e)
        }
    }

    // LiveData untuk menampung hasil insertPemesananResponse
    private val _insertPemesananResponse = MutableLiveData<InsertPemesananResponse>()
    val insertPemesananResponse: LiveData<InsertPemesananResponse> = _insertPemesananResponse

    // Fungsi untuk melakukan insert pemesanan
    suspend fun insertPemesanan(idCustomer: Int, idEvent: Int) {
        try {
            val call = apiClient.insertPemesanan(idCustomer, idEvent)
            val response = call.execute()
            if (response.isSuccessful) {
                _insertPemesananResponse.postValue(response.body())
                response.body()?.let {
                    Log.d("Insert Pemesanan Response", "status: ${it.status}")
                    Log.d("Insert Pemesanan Response", "message: ${it.message}")
                    Log.d("Insert Pemesanan Response", "Id Pemesanan Baru =  ${it.idPemesanan}")
                }
            } else {
                // Tangani kegagalan respons di sini
                handleApiError(response)
            }
        } catch (e: Exception) {
            handleNetworkError(e)
        }
    }

    // Fungsi untuk mendapatkan pemesanan terbaru
    suspend fun getPemesananTerbaru(idCustomer: Int): Call<PemesananResponse> {
        return apiClient.getPemesananTerbaru(idCustomer)
    }


    // Fungsi untuk menghapus pemesanan
    suspend fun deletePemesanan(idPemesanan: Int) {
        try {
            val call = apiClient.deletePemesanan(idPemesanan)
            val response = call.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("Delete Pemesanan Response", "status: ${it.status}")
                    Log.d("Delete Pemesanan Response", "message: ${it.message}")
                }
            }
        } catch (e: Exception) {
            handleNetworkError(e)
        }
    }

    // LiveData untuk menampung hasil insertPemesananResponse
    private val _insertPembayaranResponse = MutableLiveData<InsertPembayaranResponse>()
    val insertPembayaranResponse: LiveData<InsertPembayaranResponse> = _insertPembayaranResponse

    // Fungsi untuk melakukan insert pembayaran
    suspend fun insertPembayaran(
        jumlahTiket: Int,
        totalPembayaran: Int,
        statusPembayaran: String,
        idPemesanan: Int,
        idMetodePembayaran: Int
    ) {

        try {
            val call = apiClient.insertPembayaran(
                jumlahTiket,
                totalPembayaran,
                statusPembayaran,
                idPemesanan,
                idMetodePembayaran
            )
            val response = call.execute()
            if (response.isSuccessful) {
                _insertPembayaranResponse.postValue(response.body())
                response.body()?.let {
                    Log.d("Insert Pembayaran Response", "status: ${it.status}")
                    Log.d("Insert Pembayaran Response", "message: ${it.message}")
                    Log.d("Insert Pemesanan Response", "Id Pemesanan Baru =  ${it.idPembayaran}")
                }
            } else {
                handleApiError(response)
            }

        } catch (e: Exception) {
            handleNetworkError(e)
        }
    }


    // Fungsi untuk mengupdate pembayaran
    suspend fun updatePembayaran(idPembayaran: Int) {
        // Panggil fungsi updatePembayaran dari apiClient
        try {
            val call = apiClient.updatePembayaran(idPembayaran)
            val response = call.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("Update Pembayaran Response", "status: ${it.status}")
                    Log.d("Update Pembayaran Response", "message: ${it.message}")
                }
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            handleNetworkError(e)
        }
    }


    // Fungsi untuk menghapus pembayaran
    suspend fun deletePembayaran(idPembayaran: Int) {
        // Panggil fungsi deletePembayaran dari apiClient
        try {
            val call = apiClient.deletePembayaran(idPembayaran)
            val response = call.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("Delete Pembayaran Response", "status: ${it.status}")
                    Log.d("Delete Pembayaran Response", "message: ${it.message}")
                }
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            handleNetworkError(e)
        }
    }

    // Fungsi untuk mendapatkan pembayaran terbaru
    suspend fun getPembayaranTerbaru(
        idCustomer: Int?,
        statusPembayaran: String?
    ): Call<PembayaranResponse> {
        // Panggil fungsi getPembayaranTerbaru dari apiClient
        return apiClient.getPembayaranTerbaru(idCustomer, statusPembayaran)
    }


    // LiveData untuk menampung hasil getHalamanPayment
    private val _halamanPaymentResponse = MutableLiveData<HalamanPaymentResponse>()
    val halamanPaymentResponse: LiveData<HalamanPaymentResponse> = _halamanPaymentResponse

    // Fungsi untuk mendapatkan halaman payment
    suspend fun getHalamanPayment(idPemesanan: Int) {
        // Panggil fungsi getHalamanPayment dari apiClient

        try {
            val call = apiClient.getHalamanPayment(idPemesanan)
            val response = call.execute()
            if (response.isSuccessful) {
                _halamanPaymentResponse.postValue(response.body())
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            handleNetworkError(e)
        }
    }


    // Fungsi untuk insert QR Ticket
    suspend fun insertQRTicket(
        idQR: Int,
        gambarQR: String,
        idPembayaran: Int,
        statusPemakaian: String
    ){
        // Panggil fungsi insertQRTicket dari apiClient
        try {
            val call = apiClient.insertQRTicket(idQR, gambarQR, idPembayaran, statusPemakaian)
            val response = call.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("Insert QRTiket Response", "status: ${it.status}")
                    Log.d("Insert QRTiket Response", "message: ${it.message}")
                    Log.d("Insert QRTiket Response", "idPembayaran: ${it.idQR}")
                }
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            handleNetworkError(e)
        }
    }


    // LiveData untuk menampung hasil getHalamanListTiket
    private val _halamanListTiketResponse = MutableLiveData<HalamanListTiketResponse>()
    val halamanListTiketResponse: LiveData<HalamanListTiketResponse> = _halamanListTiketResponse

    // Fungsi untuk mendapatkan halaman list tiket
    suspend fun getHalamanListTiket(idCustomer: Int){
        // Panggil fungsi getHalamanListTiket dari apiClient
        try {
            val call = apiClient.getHalamanListTiket(idCustomer)
            val response = call.execute()
            if (response.isSuccessful) {
                _halamanListTiketResponse.postValue(response.body())
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            handleNetworkError(e)
        }
    }

    // LiveData untuk menampung hasil getHalamanListTiket
    private val _halamanListTiketSpesifikResponse = MutableLiveData<HalamanListTiketSpesifikResponse>()
    val halamanListTiketSpesifikResponse: LiveData<HalamanListTiketSpesifikResponse> = _halamanListTiketSpesifikResponse

    // Fungsi untuk mendapatkan halaman list tiket spesifik
    suspend fun getHalamanListTiketSpesifik(
        idCustomer: Int,
        idPemesanan: Int
    ) {
        // Panggil fungsi getHalamanListTiketSpesifik dari apiClient
        try {
            val call = apiClient.getHalamanListTiketSpesifik(idCustomer, idPemesanan)
            val response = call.execute()
            if (response.isSuccessful) {
                _halamanListTiketSpesifikResponse.postValue(response.body())
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            handleNetworkError(e)
        }
    }


    fun printEventList(eventList: List<Event>) {
        eventList.forEachIndexed { index, eventResponse ->
            Log.d("Event ke-${index + 1}", "ID Event: ${eventResponse.idEvent}")
            Log.d("Event ke-${index + 1}", "Nama Event: ${eventResponse.nama}")
            Log.d("Event ke-${index + 1}", "Banner Event: ${eventResponse.bannerEvent}")
            Log.d("Event ke-${index + 1}", "Deskripsi: ${eventResponse.deskripsi}")
            Log.d("Event ke-${index + 1}", "Jenis Event: ${eventResponse.jenisEvent}")
            Log.d("Event ke-${index + 1}", "Tanggal: ${eventResponse.tanggal}")
            Log.d("Event ke-${index + 1}", "Waktu: ${eventResponse.waktu}")
            Log.d("Event ke-${index + 1}", "Lokasi: ${eventResponse.lokasi}")
            Log.d("Event ke-${index + 1}", "Gambar Map Lokasi: ${eventResponse.gambarMapLokasi}")
            Log.d("Event ke-${index + 1}", "Harga Tiket: ${eventResponse.hargaTiket}")
            Log.d("Event ke-${index + 1}", "Stok Tiket: ${eventResponse.stokTiket}")
            // Cetak properti lainnya sesuai kebutuhan
        }
    }

    fun printOneEvent(event: Event) {
        event.let { event ->
            Log.d("Event Detail", "idEvent: ${event.idEvent}")
            Log.d("Event Detail", "nama: ${event.nama}")
            Log.d("Event Detail", "bannerEvent: ${event.bannerEvent}")
            Log.d("Event Detail", "deskripsi: ${event.deskripsi}")
            Log.d("Event Detail", "jenisEvent: ${event.jenisEvent}")
            Log.d("Event Detail", "tanggal: ${event.tanggal}")
            Log.d("Event Detail", "waktu: ${event.waktu}")
            Log.d("Event Detail", "lokasi: ${event.lokasi}")
            Log.d("Event Detail", "gambarMapLokasi: ${event.gambarMapLokasi}")
            Log.d("Event Detail", "hargaTiket: ${event.hargaTiket}")
            Log.d("Event Detail", "stokTiket: ${event.stokTiket}")
            // Cetak properti lainnya sesuai kebutuhan
            event.listArtis?.forEachIndexed { index, artis ->
                Log.d("Event Detail", "Artis ke-${index + 1}:")
                Log.d("Event Detail", "  Kode Artis: ${artis.idArtis}")
                Log.d("Event Detail", "  Nama Artis: ${artis.nama}")
                Log.d("Event Detail", "  Foto Artis: ${artis.foto}")
                Log.d("Event Detail", "  Deskripsi Artis: ${artis.deskripsi}")
            }

        }
    }


    private fun handleApiError(response: Response<*>) {
        val errorMessage = "Error: ${response.code()} - ${response.message()}"
        Log.e("API Error", errorMessage)
        throw Exception("API call failed with code: ${response.code()}")
    }

    private fun handleNetworkError(e: Exception) {
        val errorMessage = "Network Error: ${e.message}"
        Log.e("API Error", errorMessage)
        throw Exception("Network error: ${e.message}")
    }
}