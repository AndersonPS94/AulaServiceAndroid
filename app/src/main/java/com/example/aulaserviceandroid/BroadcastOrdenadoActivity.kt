package com.example.aulaserviceandroid

import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aulaserviceandroid.databinding.ActivityBroadcastOrdenadoBinding

class BroadcastOrdenadoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityBroadcastOrdenadoBinding.inflate(layoutInflater)
    }

    private val broadcastReceiver01 = BroadcastReceiver01()
    private val broadcastReceiver02 = BroadcastReceiver02()
    private val broadcastReceiver03 = BroadcastReceiver03()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        IntentFilter().apply {
            addAction("com.example.aulaserviceandroid.ACAO_ORDENADO")
            priority = 3
        }.also { intentFilter ->
            registerReceiver(broadcastReceiver01, intentFilter)
        }

        IntentFilter().apply {
            addAction("com.example.aulaserviceandroid.ACAO_ORDENADO")
            priority = 2
        }.also { intentFilter ->
            registerReceiver(broadcastReceiver02, intentFilter)

        }

        IntentFilter().apply {
            addAction("com.example.aulaserviceandroid.ACAO_ORDENADO")
            priority = 1
            registerReceiver(broadcastReceiver03, this)

            }
        }

        override fun onDestroy() {
            super.onDestroy()
            unregisterReceiver(broadcastReceiver01)
            unregisterReceiver(broadcastReceiver02)
            unregisterReceiver(broadcastReceiver03)
        }

    }