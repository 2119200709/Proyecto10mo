package com.example.proyecto10mo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.proyecto10mo.interfaces.APIService
import com.example.proyecto10mo.modelos.Usuarios
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    val url = "http://172.16.100.79:8080/";

    // Declaramos las variables de los campos
    lateinit var edtEmail : EditText
    lateinit var edtPass : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Se llama cuando el usuario presiona el botón enviar **/
    fun iniciarSesión(view: View) {
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPass = findViewById<EditText>(R.id.edtPass)

        val email = edtEmail.text.toString()
        val password = edtPass.text.toString()

        //Validación de Email y Contraseña
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Email y Contraseña son obligatorios", Toast.LENGTH_SHORT).show()
        } else {
            login(email, password);
        }
    }

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
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
                    println("error")
                }
            }
        }
    }

    fun irAIndex(usuario: Usuarios) {

        Toast.makeText(this, "Logueado Correctamente", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, IndexActivity::class.java)
            intent.putExtra("usuario", usuario as Serializable)
        startActivity(intent)
    }
}