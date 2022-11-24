package com.example.proyecto10mo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.proyecto10mo.databinding.ActivityPerfilAdminBinding
import com.example.proyecto10mo.modelos.Usuarios
import java.io.Serializable

class PerfilAdminActivity : AppCompatActivity() {

    // Bindig
    lateinit var binding: ActivityPerfilAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usuario : Usuarios = intent.getSerializableExtra("usuario") as Usuarios
        /**Cabecera**/
        binding.gUser.setText("Hola "+usuario.nombre.toString()+" "+usuario.apellido.toString())
        /**Perfil**/
        binding.gInfo.setText(usuario.nombre.toString()+" "+usuario.apellido.toString())
        binding.gInfo2.setText("Email: "+usuario.email.toString())

        /**Funciones del Menu**/
        binding.imgBtn.setOnClickListener{
            //menu.visibility = View.VISIBLE
            if (binding.menu.visibility == View.VISIBLE) {
                binding.menu.visibility = View.INVISIBLE
            } else {
                binding.menu.visibility = View.VISIBLE
            }
        }
        /**Inicio**/
        binding.inicio.setOnClickListener{
            val intent = Intent(this, IndexAdminActivity::class.java)
            intent.putExtra("usuario", usuario as Serializable)
            startActivity(intent)
        }
        /**Perfil**/
        binding.perfil.setOnClickListener{
            val intent = Intent(this, PerfilAdminActivity::class.java)
            intent.putExtra("usuario", usuario as Serializable)
            startActivity(intent)
        }
        /**Eventos**/
        binding.eventos.setOnClickListener{
            val intent = Intent(this, EventosActivity::class.java)
            intent.putExtra("usuario", usuario as Serializable)
            startActivity(intent)
        }
        /**Restaurantes**/
        binding.restaurantes.setOnClickListener{
            val intent = Intent(this, RestaurantesActivity::class.java)
            intent.putExtra("usuario", usuario as Serializable)
            startActivity(intent)
        }
        /**Cerrar Sesión**/
        binding.cerrarSesion.setOnClickListener{
            Toast.makeText(this, "Cerrando Sesión", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}