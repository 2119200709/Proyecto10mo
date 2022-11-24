package com.example.proyecto10mo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.ScrollCaptureCallback
import android.view.View
import android.widget.Toast
import com.example.proyecto10mo.databinding.ActivityVerRestaurantBinding
import com.example.proyecto10mo.interfaces.APIService
import com.example.proyecto10mo.modelos.ResponseStatus
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

        binding.editar.setOnClickListener{
            //menu.visibility = View.VISIBLE
            if (binding.editarFrame.visibility == View.VISIBLE) {
                binding.editarFrame.visibility = View.INVISIBLE
            } else {
                binding.editarFrame.visibility = View.VISIBLE
            }
        }

        binding.eliminar.setOnClickListener{
            binding.modalDelete.visibility = View.VISIBLE
        }

        binding.btnNo.setOnClickListener{
            binding.modalDelete.visibility = View.INVISIBLE
        }

        val restaurantNombre : String? = intent.getStringExtra("restaurantNombre")
        val restaurantSucursal : String? = intent.getStringExtra("restaurantSucursal")
        val restaurantDomicilio : String? = intent.getStringExtra("restaurantDomicilio")
        val restaurantID : String? = intent.getStringExtra("restaurantID")

        binding.nombre.text = "Nombre: $restaurantNombre"
        binding.sucursal.text = "Sucursal: $restaurantSucursal"
        binding.domicilio.text = "Domicilio: $restaurantDomicilio"

        binding.nombre.movementMethod = ScrollingMovementMethod()
        binding.sucursal.movementMethod = ScrollingMovementMethod()
        binding.domicilio.movementMethod = ScrollingMovementMethod()

        binding.edtNombre.setText(restaurantNombre)
        binding.edtSucursal.setText(restaurantSucursal)
        binding.edtDomicilio.setText(restaurantDomicilio)

        binding.txtEliminarInfo.text = restaurantNombre

        binding.guardar.setOnClickListener {
            val nombre = binding.edtNombre.text.toString()
            val sucursal = binding.edtSucursal.text.toString()
            val domicilio = binding.edtDomicilio.text.toString()
            val idRestaurant = Integer.parseInt(restaurantID)

            guardar(nombre, sucursal, domicilio, idRestaurant)
            //onBackPressed()
        }

        binding.btnSi.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(APIService::class.java).eliminarRestaurant("eliminarrestaurant/$restaurantID")
                val responseStatus: ResponseStatus? = call.body()

                runOnUiThread {
                    if (call.isSuccessful) {
                        binding.modalDelete.visibility = View.INVISIBLE
                        Toast.makeText(baseContext, "Restaurante Eliminado", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Algo salió mal, intente más tarde", Toast.LENGTH_SHORT).show()
                    }
                }
            }
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