package com.example.proyecto10mo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.proyecto10mo.databinding.ActivityVerBoletoBinding
import com.example.proyecto10mo.interfaces.APIService
import com.example.proyecto10mo.modelos.ResponseStatus
import com.example.proyecto10mo.objects.VariablesGlobales
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VerBoleto : AppCompatActivity() {

    lateinit var binding: ActivityVerBoletoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerBoletoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val codacc : String? = intent.getStringExtra("codigo")
        val id : String? = intent.getStringExtra("id")
        val cliente : String? = intent.getStringExtra("cliente")
        val evento : String? = intent.getStringExtra("evento")
        val restaurant : String? = intent.getStringExtra("restaurant")
        val domicilio : String? = intent.getStringExtra("domicilio")
        val fechayhora : String? = intent.getStringExtra("fechayhora")

        if (VariablesGlobales.rol == "1") {
            binding.nombre.text = "Ingresa el código de acceso"
            binding.code.visibility = View.INVISIBLE
        } else {
            binding.edtcode.visibility = View.INVISIBLE
            binding.veriicar.visibility = View.INVISIBLE
        }

        binding.nombreCli.text = cliente;
        binding.nombreEvento.text = evento;
        binding.restaurante.text = restaurant;
        binding.domicilio.text = domicilio;
        binding.fechayhora.text = fechayhora;

        binding.code.text = codacc

        binding.volver.setOnClickListener {
            finish()
        }

        binding.veriicar.setOnClickListener {
            if (id != null) {
                guardar(id, binding.edtcode.text.toString())
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(VariablesGlobales.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun guardar(id: String,codacc :String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).ingresar(id, codacc)
            val responseStatus: ResponseStatus? = call.body()
            println("$id $codacc $responseStatus")
            runOnUiThread {
                if (call.isSuccessful) {
                    if (responseStatus != null) {
                        Toast.makeText(baseContext, responseStatus.msg, Toast.LENGTH_SHORT).show()
                    }
                    finish()
                } else {
                    Toast.makeText(baseContext, "Algo salió mal, intente más tarde", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}