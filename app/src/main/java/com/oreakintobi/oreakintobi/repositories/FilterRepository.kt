package com.oreakintobi.oreakintobi.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.oreakintobi.oreakintobi.Account
import com.oreakintobi.oreakintobi.Filter
import com.oreakintobi.oreakintobi.utils.DataUtils
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import timber.log.Timber
import java.io.IOException

// We need to interact with UserRepository from our AuthViewModel,
// LoginActivity will interact with AuthViewModel
class FilterRepository() {

    fun fetchFilters(): LiveData<List<Account>> {
        val mutableFilters = MutableLiveData<List<Account>>()
        val request = Request.Builder().url(DataUtils.BASE_URL).build()
        val okHttpClient = OkHttpClient()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Timber.e(e.message.toString())
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body?.string()
                Timber.d(body!!)

                val gson = GsonBuilder().create()
                val allFilters = gson.fromJson(body, Filter::class.java)
                mutableFilters.postValue(allFilters.accounts)
            }
        })
        return mutableFilters
    }
}