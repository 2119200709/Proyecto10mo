package com.example.proyecto10mo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.proyecto10mo.adapters.BoletoAdapter
import com.example.proyecto10mo.databinding.ActivityBoletosBinding
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto10mo.adapters.RestaurantAdapter
import com.example.proyecto10mo.interfaces.APIService
import com.example.proyecto10mo.modelos.Invitaciones
import com.example.proyecto10mo.modelos.Restaurantes
import com.example.proyecto10mo.modelos.Usuarios
import com.example.proyecto10mo.objects.VariablesGlobales
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class BoletosActivity : AppCompatActivity(), OnQueryTextListener {

    // Binding
    lateinit var binding: ActivityBoletosBinding

    lateinit var adapter: BoletoAdapter
    lateinit var invitacionesList: ArrayList<Invitaciones>

    override fun onResume() {
        super.onResume()
        searchByName("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoletosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usuario : Usuarios = intent.getSerializableExtra("usuario") as Usuarios
        VariablesGlobales.idUsuario = usuario.id.toString()
        /**Cabecera**/
        binding.gUser.setText("Hola "+usuario.nombre.toString()+" "+usuario.apellido.toString())

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
            val intent = Intent(this, IndexActivity::class.java)
            intent.putExtra("usuario", usuario as Serializable)
            startActivity(intent)
        }
        /**Perfil**/
        binding.perfil.setOnClickListener{
            val intent = Intent(this, PerfilAdminActivity::class.java)
            intent.putExtra("usuario", usuario as Serializable)
            startActivity(intent)
        }
        /**Boletos**/
        binding.boletos.setOnClickListener{
            val intent = Intent(this, BoletosActivity::class.java)
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

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(VariablesGlobales.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query: String?) {

        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<ArrayList<Invitaciones>> = getRetrofit().create(APIService::class.java).getInvitaciones("invitaciones/${VariablesGlobales.idUsuario}/$query")
            val restaurants: ArrayList<Invitaciones>? = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    if (restaurants != null) {
                        invitacionesList = restaurants
                        binding.txtSize.text = invitacionesList.size.toString()
                    }
                    adapter = BoletoAdapter(invitacionesList)
                    binding.rv.layoutManager = LinearLayoutManager(baseContext)
                    binding.rv.adapter = adapter
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(baseContext, "Algo salió mal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()) {
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}