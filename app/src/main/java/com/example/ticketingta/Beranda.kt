package com.example.ticketingta

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ticketingta.adapter.EventAdapter
import com.example.ticketingta.databinding.FragmentBerandaBinding
import com.example.ticketingta.exception.MyCustomException
import com.example.ticketingta.model.Event
import com.example.ticketingta.network.RetrofitClient
import com.example.ticketingta.viewmodel.MyViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Beranda.newInstance] factory method to
 * create an instance of this fragment.
 */
class Beranda : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentBerandaBinding
    private lateinit var myViewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBerandaBinding.inflate(layoutInflater,container,false)

        //View Model untuk ambil data List Event
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        val adapter = EventAdapter(this, requireContext(), myViewModel)
        val rvListEvent = binding.rvEvent
        rvListEvent.adapter = adapter
        rvListEvent.layoutManager = LinearLayoutManager(requireContext())

        // ambil data List Event
//        myViewModel.getListEvent()
        myViewModel.eventList.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        //Make data dummy dulu, nanti di ganti


//        val listEvent = getData()

//        adapter.setData(dummyEventList)

//        if (listEvent != null) {
//            adapter.setData(listEvent)
//        }

        // Launch the coroutine to fetch albums
//        fetchEvents(adapter)

        return binding.root
    }

    private fun getData (): List<Event>? {
        var listEvent : List<Event>? = null
        val api = RetrofitClient().getInstance()
        api.getListEvent().enqueue(object : Callback<List<Event>>{
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                if (response.isSuccessful){
                    listEvent = response.body()
                    listEvent?.forEachIndexed { index, eventResponse ->
                        Log.d("Event ke-${index + 1}", "ID Event: ${eventResponse.idEvent}")
                        Log.d("Event ke-${index + 1}", "Nama Event: ${eventResponse.nama}")
                        Log.d("Event ke-${index + 1}", "Banner Event: ${eventResponse.bannerEvent}")
                        Log.d("Event ke-${index + 1}", "Deskripsi: ${eventResponse.deskripsi}")
                        Log.d("Event ke-${index + 1}", "Jenis Event: ${eventResponse.jenisEvent}")
                        Log.d("Event ke-${index + 1}", "Tanggal: ${eventResponse.tanggal}")
                        Log.d("Event ke-${index + 1}", "Waktu: ${eventResponse.waktu}")
                        Log.d("Event ke-${index + 1}", "Lokasi: ${eventResponse.lokasi}")
                        Log.d(
                            "Event ke-${index + 1}",
                            "Gambar Map Lokasi: ${eventResponse.gambarMapLokasi}"
                        )
                        Log.d("Event ke-${index + 1}", "Harga Tiket: ${eventResponse.hargaTiket}")
                        Log.d("Event ke-${index + 1}", "Stok Tiket: ${eventResponse.stokTiket}")
                        // Cetak properti lainnya sesuai kebutuhan
                    }
                }
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                // Handle the API error
                Log.e("API Error", "Error: ${t.message}")
            }
        })
        return listEvent

    }


    private fun fetchEvents(adapter : EventAdapter){
        lifecycleScope.launch{
            try {
                val events : List<Event> = getEvents()
                events.forEachIndexed { index, eventResponse ->
                    Log.d("Event ke-${index + 1}", "ID Event: ${eventResponse.idEvent}")
                    Log.d("Event ke-${index + 1}", "Nama Event: ${eventResponse.nama}")
                    Log.d("Event ke-${index + 1}", "Banner Event: ${eventResponse.bannerEvent}")
                    Log.d("Event ke-${index + 1}", "Deskripsi: ${eventResponse.deskripsi}")
                    Log.d("Event ke-${index + 1}", "Jenis Event: ${eventResponse.jenisEvent}")
                    Log.d("Event ke-${index + 1}", "Tanggal: ${eventResponse.tanggal}")
                    Log.d("Event ke-${index + 1}", "Waktu: ${eventResponse.waktu}")
                    Log.d("Event ke-${index + 1}", "Lokasi: ${eventResponse.lokasi}")
                    Log.d(
                        "Event ke-${index + 1}",
                        "Gambar Map Lokasi: ${eventResponse.gambarMapLokasi}"
                    )
                    Log.d("Event ke-${index + 1}", "Harga Tiket: ${eventResponse.hargaTiket}")
                    Log.d("Event ke-${index + 1}", "Stok Tiket: ${eventResponse.stokTiket}")
                    // Cetak properti lainnya sesuai kebutuhan
                }
                adapter.setData(events)


            }catch (e : Exception){
                e.message?.let { Log.e("Custom Exception", it) }
            }
        }

    }


    suspend fun getEvents(): List<Event> {
        return withContext(Dispatchers.IO) {
            val call = RetrofitClient().getInstance().getListEvent()
            val response = call.execute()

            if (response.isSuccessful) {
                return@withContext response.body() ?: throw MyCustomException("Response body is null")
            } else {
                throw MyCustomException("API call failed with code: ${response.code()}")
            }
        }
    }


}