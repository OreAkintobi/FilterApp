package com.oreakintobi.oreakintobi.data

import com.oreakintobi.oreakintobi.models.Filter
import retrofit2.Call
import retrofit2.http.GET

interface FilterAPI {


    @GET("accounts")
    fun getFilterList(): Call<Filter>
}