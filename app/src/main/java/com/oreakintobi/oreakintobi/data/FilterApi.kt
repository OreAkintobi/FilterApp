package com.oreakintobi.oreakintobi.data

import android.util.Log
import com.google.gson.GsonBuilder
import com.oreakintobi.oreakintobi.Filter
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.io.IOException

interface FilterApi {
    // this annotation is needed for POST in Retrofit
    @FormUrlEncoded
    // defines POST behaviour for login endpoint of RESTful API created
    @POST("login")
    // defines function userLogin for handling behavior from Login fields
    // We cannot directly call this function from our LoginActivity. It will be called from our AuthViewModel,
    // AuthViewModel will interact with our UserRepository
    // function is suspending because logging in the user is a network operation,
    // It might take time, and the function must complete without blocking the entire app
    suspend fun userLogin(
        // field annotation names must match with API calls
        @Field("email") email: String,
        @Field("password") password: String
        // Defined a Retrofit Call with a type of ResponseBody,
        // Now defines a Retrofit Response that takes in  data that is of type AuthResponse from our data class AuthResponse
    ): Response<AuthResponse>

    //
    companion object {
        // this operator function (function that overloads operators) allows us to call/"invoke" the Api when needed
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
            // returns MyApi in Retrofit's .create
        ): FilterApi {

            val url = "https://android-json-test-api.herokuapp.com/accounts"
            val request = Request.Builder().url(url).build()
            val okHttpClient = OkHttpClient()
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: okhttp3.Response) {
                    val body = response.body?.string()
                    println(body)
                    Log.d("POKEMON", body!!)

                    val gson = GsonBuilder().create()
                    val allPokemon = gson.fromJson(body, Filter::class.java)

                }

                override fun onFailure(call: Call, e: IOException) {
                    println("Failed to Execute ${e.printStackTrace()}")
                }
            })

            // Retrofit Builder return statement to call MyApi
            return Retrofit.Builder()
                // okHttpClient added to our Retrofit Builder
                .client(okHttpClient)
                .baseUrl("https://android-json-test-api.herokuapp.com/accounts")
                .addConverterFactory(GsonConverterFactory.create())
                // creates API instance for MyApi return
                .build().create(FilterApi::class.java)
        }
    }
}