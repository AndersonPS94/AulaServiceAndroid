package com.example.aulaserviceandroid

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.aulaserviceandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ServiceConnection {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var serviceConnection: ServiceConnection
    private lateinit var servicoConectado: MinhaConexao
    /*private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            TODO("Not yet implemented")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            TODO("Not yet implemented")
        }

    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        solicitarPermissaoNotificacao()

        serviceConnection = this
        //val meuServico = Intent(this, MeuServico::class.java)
        val minhaConexaoServico = Intent(this, MinhaConexao::class.java)
        binding.btnIniciarService.setOnClickListener {
            //minhaConexaoServico.putExtra("tempoDuracao", 3000L)

            startForegroundService(minhaConexaoServico)

            //startService(minhaConexaoServico) //inicia um serviço
            bindService(
                minhaConexaoServico,
                serviceConnection,
                BIND_AUTO_CREATE

            ) //conecta um serviço
        }
        binding.btnPararService.setOnClickListener {
            stopService(minhaConexaoServico)
            unbindService(serviceConnection)
        }
        binding.btnPegarDados.setOnClickListener {
            Toast.makeText(this, "Contador: ${servicoConectado.contador}", Toast.LENGTH_SHORT).show()
        }

    }

    private fun solicitarPermissaoNotificacao() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permissaoNotificacao = ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.POST_NOTIFICATIONS)
            if (permissaoNotificacao == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    100
                )
            }
        }
    }

    override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
        Log.i("servico_android", "onServiceConnected")
            val customBinder = p1 as MinhaConexao.CustomBinder
            servicoConectado = customBinder.recuperarServico()

    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        Toast.makeText(this, "Desconectado do serviço", Toast.LENGTH_SHORT).show()
    }
}