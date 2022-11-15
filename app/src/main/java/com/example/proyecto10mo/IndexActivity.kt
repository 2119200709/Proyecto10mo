package com.example.proyecto10mo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.proyecto10mo.modelos.Usuarios
import java.io.Serializable
import java.util.*

class IndexActivity : AppCompatActivity() {

    // Declaramos las variables de los campos
    /**Cabecera**/
    lateinit var gUser : TextView
    /**Componentes del Menu**/
    // ImageButton
    lateinit var btnMenu : ImageButton
    // Botones
    lateinit var btnInicio : Button
    lateinit var btnPerfil : Button
    lateinit var btnBoletos : Button
    lateinit var btnCerrarSesion : Button
    // FrameLayout
    lateinit var menu : FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)
        val usuario : Usuarios = intent.getSerializableExtra("usuario") as Usuarios
        /**Cabecera**/
        gUser = findViewById(R.id.gUser)

        gUser.setText("Hola "+usuario.nombre.toString()+" "+usuario.apellido.toString())

        /**IDs Menu**/
        btnMenu = findViewById(R.id.imgBtn)
        btnInicio = findViewById(R.id.inicio)
        btnPerfil = findViewById(R.id.perfil)
        btnBoletos = findViewById(R.id.boletos)
        menu = findViewById(R.id.menu)
        btnCerrarSesion = findViewById(R.id.cerrarSesion)

        /**Funciones del Menu**/
        btnMenu.setOnClickListener{
            //menu.visibility = View.VISIBLE
            if (menu.visibility == View.VISIBLE) {
                menu.visibility = View.INVISIBLE
            } else {
                menu.visibility = View.VISIBLE
            }
        }
        /**Inicio**/
        btnInicio.setOnClickListener{
            val intent = Intent(this, IndexActivity::class.java).apply {
                //putExtra(email, pass)
            }
            startActivity(intent)
        }
        /**Perfil**/
        btnPerfil.setOnClickListener{
            val intent = Intent(this, PerfilActivity::class.java).apply {
                //putExtra(email, pass)
            }
            startActivity(intent)
        }
        /**Boletos**/
        btnBoletos.setOnClickListener{
            val intent = Intent(this, BoletosActivity::class.java).apply {
                //putExtra(email, pass)
            }
            startActivity(intent)
        }
        /**Cerrar Sesión**/
        btnCerrarSesion.setOnClickListener{
            Toast.makeText(this, "Cerrando Sesión", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java).apply {
                //putExtra(email, pass)
            }
            startActivity(intent)
        }
    }
}