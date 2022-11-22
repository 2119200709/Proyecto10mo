package com.example.proyecto10mo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto10mo.databinding.ActivityVerRestaurantBinding
import com.example.proyecto10mo.interfaces.APIService
import com.example.proyecto10mo.modelos.Restaurantes
import com.example.proyecto10mo.objects.VariablesGlobales
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VerRestaurant : AppCompatActivity() {

    lateinit var binding: ActivityVerRestaurantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantNombre : String? = intent.getStringExtra("restaurantNombre")
        val restaurantSucursal : String? = intent.getStringExtra("restaurantSucursal")
        val restaurantDomicilio : String? = intent.getStringExtra("restaurantDomicilio")
        val restaurantID : String? = intent.getStringExtra("restaurantID")

        println(restaurantID)

        binding.nombre.text = "Nombre: $restaurantNombre"
        binding.sucursal.text = "Sucursal: $restaurantSucursal"
        binding.domicilio.text = "Domicilio: $restaurantDomicilio"

        binding.edtNombre.setText(restaurantNombre)
        binding.edtSucursal.setText(restaurantSucursal)
        binding.edtDomicilio.setText(restaurantDomicilio)

        binding.guardar.setOnClickListener {
            val nombre = binding.edtNombre.text.toString()
            val sucursal = binding.edtSucursal.text.toString()
            val domicilio = binding.edtDomicilio.text.toString()
            val idRestaurant = Integer.parseInt(restaurantID)

            guardar(nombre, sucursal, domicilio, idRestaurant)
            //onBackPressed()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(VariablesGlobales.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun guardar(nombre: String, sucursal: String, domicilio: String, idRestaurant: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).editarRestaurant(nombre, sucursal, domicilio, idRestaurant)
            val restaurant:Restaurantes? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(baseContext, "Guardado Correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                    //regresar()
                } else {
                    Toast.makeText(baseContext, "Algo salió mal, intente más tarde", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun regresar() {
        val intent = Intent(baseContext, RestaurantesActivity::class.java)
        setResult(RESULT_OK,intent)
    }
}