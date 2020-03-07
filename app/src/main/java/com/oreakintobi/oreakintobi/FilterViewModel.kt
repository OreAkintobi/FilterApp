package com.oreakintobi.oreakintobi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.oreakintobi.oreakintobi.repositories.FilterRepository
import kotlinx.coroutines.launch

class FilterViewModel(
    application: Application
) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: FilterRepository

    // LiveData gives us updated contacts when they change.
//    val filters: MutableList<FilterElement>

    init {
        repository = FilterRepository()
//        filters = fetchFilters()
    }

    fun insert(filter: Filter) = viewModelScope.launch {
    }

    fun clear() = viewModelScope.launch {
    }

//    fun fetchFilters(): MutableList<FilterElement> {
//        val url = "https://android-json-test-api.herokuapp.com/accounts"
//        val request = Request.Builder().url(url).build()
//        val okHttpClient = OkHttpClient()
//        okHttpClient.newCall(request).enqueue(object : Callback {
//            override fun onResponse(call: Call, response: okhttp3.Response) {
//                val body = response.body?.string()
//                println(body)
//                Log.d("FILTERS", body!!)
//
//                val gson = GsonBuilder().create()
//                val allFilters = gson.fromJson(body, FilterElement::class.java)
////
////                for (filter in Filter) {
////                    filters.add(filter)
////                }
//            }
//
//            override fun onFailure(call: Call, e: IOException) {
//                println("Failed to Execute ${e.printStackTrace()}")
//            }
//        })
//        return
//    }

//    suspend fun loadAllPokemon(): ArrayList<FilterElement> {
//        return GlobalScope.async(Dispatchers.IO) {
//            val filterList = fetchFilters() as ArrayList<FilterElement>
//            filterList
//        }.await()
//    }
}