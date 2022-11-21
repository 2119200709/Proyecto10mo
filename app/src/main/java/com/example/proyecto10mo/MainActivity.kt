package com.example.proyecto10mo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.proyecto10mo.databinding.ActivityMainBinding
import com.example.proyecto10mo.interfaces.APIService
import com.example.proyecto10mo.modelos.Usuarios
import com.example.proyecto10mo.objects.VariablesGlobales
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    // Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /** Se llama cuando el usuario presiona el botón enviar **/
    fun iniciarSesión(view: View) {

        val email = binding.edtEmail.text.toString()
        val password = binding.edtPass.text.toString()

        //Validación de Email y Contraseña
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Email y Contraseña son obligatorios", Toast.LENGTH_SHORT).show()
        } else {
            login(email, password);
        }
    }

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(VariablesGlobales.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun login(email: String, password : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).login(email,password)
            val usuario:Usuarios? = call.body()
            runOnUiThread {
                if(call.isSuccessful) {
                    if (usuario != null) {
                        if (usuario.nombre != null) {
                            Toast.makeText(baseContext, "Logueado Correctamente", Toast.LENGTH_SHORT).show()
                            irAIndex(usuario)
                        } else {
                            Toast.makeText(baseContext, usuario.msg, Toast.LENGTH_SHORT).show()
                        }
                    }

                } else {
                    Toast.makeText(baseContext, "Sin Conexión a la base de datos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun irAIndex(usuario: Usuarios) {

        if (usuario.admin ==1) {
            Toast.makeText(this, "Logueado Correctamente", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, IndexAdminActivity::class.java)
            intent.putExtra("usuario", usuario as Serializable)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Logueado Correctamente", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, IndexActivity::class.java)
            intent.putExtra("usuario", usuario as Serializable)
            startActivity(intent)
        }

    }
}