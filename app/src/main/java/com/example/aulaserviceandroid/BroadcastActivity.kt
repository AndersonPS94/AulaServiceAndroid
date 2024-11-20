package com.example.aulaserviceandroid

import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aulaserviceandroid.databinding.ActivityBroadcastBinding

class BroadcastActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityBroadcastBinding.inflate(layoutInflater)
    }

    private lateinit var meuBroadcastReceiver: MeuBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        meuBroadcastReceiver = MeuBroadcastReceiver()
        //val intentFilter = IntentFilter()
        IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_CHANGED)
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)

        }.also { intentFilter ->

            registerReceiver(meuBroadcastReceiver, intentFilter)
        }

        //val wifiManager = getSystemService(WIFI_SERVICE) as WifiManager
        /*val wifiManager = getSystemService(WifiManager::class.java)
        if (!wifiManager.isWifiEnabled) {
            binding.textResultado.text = "Wifi Habilitado"
        }else{
            binding.textResultado.text = "Wifi Desabilitado"
        }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(meuBroadcastReceiver)
    }

}