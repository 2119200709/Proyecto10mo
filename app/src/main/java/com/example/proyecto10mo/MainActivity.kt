package com.example.proyecto10mo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

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
        val pass = edtPass.text.toString()

        //Validación de Email y Contraseña
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Usuario y Contraseña son obligatorios", Toast.LENGTH_SHORT).show()
        } else {
            login();
        }
    }

    fun login() {
        Toast.makeText(this, "Logueado Correctamente", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, IndexActivity::class.java).apply {
            //putExtra(email, pass)
        }
        startActivity(intent)
    }
}