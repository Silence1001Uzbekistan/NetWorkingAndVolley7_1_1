package com.example.networkingpractise.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetWorkHelper constructor(private val context: Context) {

    fun isNetWorkConnected(): Boolean {

        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false

        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        result = when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

        return result

    }

}