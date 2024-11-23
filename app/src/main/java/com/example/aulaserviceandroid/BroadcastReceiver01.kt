package com.example.aulaserviceandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BroadcastReceiver01 : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Thread.sleep(2000)


        var initialCode = resultCode
        var initialData = resultData
        val extras = getResultExtras(true)
        var dadosExtras = extras.getString("dadosExtras")

        val dadosParametro = " [B1] code: $initialCode, data: $initialData, extras: $dadosExtras"

        Log.i("BROADCAST_ANDROID", " Broadcast 01: $dadosParametro")

        //alterar dados
        initialCode++
        initialData += "-> Broadcast1"
        dadosExtras += " - ALTERADO B1"

        extras.putString("dadosExtra", dadosExtras)
        setResult(initialCode, initialData, extras)
    }
}