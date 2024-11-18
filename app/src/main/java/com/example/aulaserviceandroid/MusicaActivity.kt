package com.example.aulaserviceandroid

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aulaserviceandroid.databinding.ActivityMusicaBinding

class MusicaActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMusicaBinding.inflate(layoutInflater)
    }

    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
            inicializarMediaPlayer()
            inicializarControles()
        }

    private fun inicializarControles() {
        binding.btnTocar.setOnClickListener { play() }
        binding.btnPausar.setOnClickListener { pause() }
        binding.btnParar.setOnClickListener { stop() }
            inicializarControleVolume()
            executarServicoMusica()
    }

    private fun executarServicoMusica() {

        val musicaService = Intent(this, MusicaService::class.java)
        binding.btnIniciarServicoMusica.setOnClickListener {
            //startService(musicaService)

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                startForegroundService(musicaService)
            }else{
                startService(musicaService)
            }

        }
        binding.btnPararServicoMusica.setOnClickListener {
            stopService(musicaService)
        }
    }

    private fun inicializarControleVolume() {

        val audioManager =  getSystemService(AudioManager::class.java)


        binding.seekVolume.max = audioManager
            .getStreamMaxVolume(AudioManager.STREAM_MUSIC)


        binding.seekVolume.progress = audioManager
            .getStreamVolume(AudioManager.STREAM_MUSIC)

        binding.seekVolume.setOnSeekBarChangeListener(object: OnSeekBarChangeListener{
            override fun onProgressChanged(
                p0: SeekBar?,
                p1: Int,
                p2: Boolean
            ) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, p1, 0)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
    }

    private fun stop() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            Toast.makeText(this, "Música parada", Toast.LENGTH_LONG).show()
        }
    }

    private fun pause() {
        if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            Toast.makeText(this, "Música pausada", Toast.LENGTH_LONG).show()
        }
    }

    private fun play() {
        if (mediaPlayer == null) {
            inicializarMediaPlayer()
        }
        mediaPlayer?.start()
        Toast.makeText(this, "Reproduzindo música", Toast.LENGTH_LONG).show()
    }


    private fun inicializarMediaPlayer() {

        mediaPlayer = MediaPlayer.create(
            this,
            R.raw.teste
        )
    }

    override fun onDestroy() {
        if (mediaPlayer != null){
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = null
        }
        super.onDestroy()
    }
}
