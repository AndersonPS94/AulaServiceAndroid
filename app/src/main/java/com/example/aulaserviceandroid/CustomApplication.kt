package com.example.aulaserviceandroid

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        criarCanais()

    }

    fun criarCanais(){

        val idCanal = "notificacaoLembrete"
        val canal = NotificationChannel(
            idCanal,
            "Lembrete",
            NotificationManager.IMPORTANCE_HIGH
        )

        getSystemService(NotificationManager::class.java).createNotificationChannel(canal)
    }
}