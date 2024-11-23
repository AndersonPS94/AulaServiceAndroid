package com.example.aulaserviceandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BroadcastReceiver03 : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Thread.sleep(2000)
        var initialCode = resultCode
        var initialData = resultData
        val extras = getResultExtras(true)
        var dadosExtras = extras.getString("dadosExtras")

        val dadosParametro = " [B3] code: $initialCode, data: $initialData, extras: $dadosExtras"

        Log.i("BROADCAST_ANDROID", " Broadcast 03: $dadosParametro")

        //alterar dados
        initialCode++
        initialData += "-> Broadcast3"
        dadosExtras += " - ALTERADO B3"

        extras.putString("dadosExtra", dadosExtras)
        setResult(initialCode, initialData, extras)
    }
}