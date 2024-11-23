package com.example.aulaserviceandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aulaserviceandroid.databinding.ActivityComunicacaoBroadcastBinding

class ComunicacaoBroadcastActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityComunicacaoBroadcastBinding.inflate(layoutInflater)
    }

    private lateinit var comunicacaoBroadcastReceiver: ComunicacaoBroadcastReceiver

    private val broadcastActivity = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == Intent.ACTION_POWER_CONNECTED){
                Toast.makeText(
                    context,
                    "Cabo de energia Conectado",
                    Toast.LENGTH_LONG).show()

                binding.textInformacao.text = "Cabo de energia Conectado"
            }else{
                Toast.makeText(
                    context,
                    "Cabo de energia Desconectado",
                    Toast.LENGTH_LONG).show()

                binding.textInformacao.text = "Cabo de energia Desconectado"
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnImplicita.setOnClickListener {
           /* val link = "https://www.google.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intent)*/

            val intent =Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "Arquivo PDF")
            intent.type = "plain/text"

            val compartilhar = Intent.createChooser(intent, "Compartilhar")
            startActivity(compartilhar)
        }

        comunicacaoBroadcastReceiver = ComunicacaoBroadcastReceiver()
        IntentFilter(
                "com.example.aulaserviceandroid.ENVIO_ARQUIVO_PDF"
            ).apply {
                registerReceiver(comunicacaoBroadcastReceiver, this)
            }

        //Broadcast Activity
        IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }.also { intentFilter ->
            registerReceiver(broadcastActivity, intentFilter)
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(comunicacaoBroadcastReceiver)
        unregisterReceiver(broadcastActivity)
    }
}

