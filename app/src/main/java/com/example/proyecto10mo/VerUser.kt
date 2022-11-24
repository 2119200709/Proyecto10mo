package com.example.proyecto10mo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.proyecto10mo.databinding.ActivityVerUserBinding
import com.example.proyecto10mo.interfaces.APIService
import com.example.proyecto10mo.modelos.ResponseStatus
import com.example.proyecto10mo.objects.VariablesGlobales
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VerUser : AppCompatActivity() {

    // Binding
    lateinit var binding: ActivityVerUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.eliminar.setOnClickListener{
            binding.modalDelete.visibility = View.VISIBLE
        }

        binding.btnNo.setOnClickListener{
            binding.modalDelete.visibility = View.INVISIBLE
        }

        val nombre : String? = intent.getStringExtra("userNombre")
        val apellido : String? = intent.getStringExtra("userApellido")
        val email : String? = intent.getStringExtra("userEmail")
        val rol : String? = intent.getStringExtra("userRol")
        val idUser : String? = intent.getStringExtra("userID")

        binding.nombre.text = "$nombre $apellido"
        binding.email.text = email
        binding.admin.text = if(rol.toString() == "1") { "Administrador" } else { "Cliente" }

        binding.btnSi.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(APIService::class.java).eliminarUsuario("eliminarusuario/$idUser")
                val responseStatus: ResponseStatus? = call.body()

                runOnUiThread {
                    if (call.isSuccessful) {
                        binding.modalDelete.visibility = View.INVISIBLE
                        Toast.makeText(baseContext, "Usuario Eliminado", Toast.LENGTH_SHORT).show()
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

    fun regresar() {
        val intent = Intent(baseContext, UsersActivity::class.java)
        setResult(RESULT_OK,intent)
    }
}