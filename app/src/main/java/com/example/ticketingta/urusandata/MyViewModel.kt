package com.example.ticketingta.urusandata

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.ticketingta.model.Event
import com.example.ticketingta.model.MetodePembayaran
import com.example.ticketingta.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : Repository

    init {
        val api = RetrofitClient().getInstance()
//        repository = Repository(api)
        repository = MyRepositoryProvider.provideRepository(api)
        getListEvent() //Cobain pas init langsing dikerjain
    }

    // LiveData untuk daftar event
    val eventList: LiveData<List<Event>> = repository.eventList

    // Fungsi untuk mendapatkan daftar event
    fun getListEvent() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getListEvent()
        }
    }

    // LiveData untuk satu event
    val event: LiveData<Event> = repository.event

    // Fungsi untuk mendapatkan satu event
    fun getOneEvent(kode: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getOneEvent(kode)
        }
    }


    // LiveData untuk menampung hasil getMetodePembayaran
    val metodePembayaranList: LiveData<List<MetodePembayaran>> = repository.metodePembayaranList

    // Fungsi untuk mendapatkan metode pembayaran
    fun getMetodePembayaran(id: Int? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMetodePembayaran(id)
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



}