package com.example.aulaserviceandroid

import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat

class MusicaService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()
        inicializarMediaPlayer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val idCanal = "notificacaoLembrete"
        val dataAtual = System.currentTimeMillis()
        val notificacao = NotificationCompat
            .Builder(this, idCanal).apply {
                setSmallIcon(R.drawable.ic_musica_24)
                //setWhen()
                setShowWhen(true)
                //setChannelId()
                setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.perfil))
                setContentTitle("Gavin DeGraw")
                setContentText("I Dont Want to Be")
            }

        mediaPlayer?.start()
        startForeground(1, notificacao.build())


        Toast.makeText(this, "Reproduzindo música", Toast.LENGTH_LONG).show()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun inicializarMediaPlayer() {
        mediaPlayer = MediaPlayer.create(
            this,
            R.raw.teste
        )
    }

    override fun onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }

        Toast.makeText(this, "Música parada", Toast.LENGTH_LONG).show()
        super.onDestroy()
    }
}