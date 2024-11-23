package com.example.aulaserviceandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ComunicacaoBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "com.example.aulaserviceandroid.ENVIO_ARQUIVO_PDF" ){
            val localPdf = intent.getStringExtra("ARQUIVO")
            Log.i("broadcast_android", "abriu arquivo PDF: $localPdf")

        }
    }
}