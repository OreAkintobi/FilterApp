package com.oreakintobi.oreakintobi.data

import android.content.Context
import android.net.ConnectivityManager

object NetworkChecker {
    fun isNetworkAvailable(context: Context): Boolean? {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}