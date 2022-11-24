package com.example.proyecto10mo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import com.example.proyecto10mo.databinding.ActivityVerEventoBinding
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto10mo.adapters.BoletoAdapter
import com.example.proyecto10mo.interfaces.APIService
import com.example.proyecto10mo.modelos.Invitaciones
import com.example.proyecto10mo.objects.VariablesGlobales
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VerEvento : AppCompatActivity(), OnQueryTextListener {

    // Binding
    lateinit var binding: ActivityVerEventoBinding
    lateinit var adapter: BoletoAdapter
    lateinit var invitacionesList: ArrayList<Invitaciones>

    override fun onResume() {
        super.onResume()
        searchByName("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sv.setOnQueryTextListener(this)
        searchByName("")

        val eventoID : String? = intent.getStringExtra("id")
        val eventoNombre : String? = intent.getStringExtra("nombre")
        val eventoCategoria : String? = intent.getStringExtra("categoria")
        val eventoDia : String? = intent.getStringExtra("dia")
        val eventoHora : String? = intent.getStringExtra("hora")
        val eventoDescripcion : String? = intent.getStringExtra("descripcion")
        val eventoDisponibles : String? = intent.getStringExtra("disponibles")
        val eventoRestaurante : String? = intent.getStringExtra("restaurante")

        binding.nombre.text = "Nombre: $eventoNombre"
        binding.categoria.text = "Categoria: $eventoCategoria"
        binding.dia.text = "Dia: $eventoDia"
        binding.hora.text = "Hora: $eventoHora"
        binding.descripcion.text = "Descripción del evento: $eventoDescripcion"
        binding.disponibles.text = "Cupo disponible: $eventoDisponibles"
        binding.restaurante.text = "Restaurant: $eventoRestaurante"
        VariablesGlobales.idEvento = eventoID.toString()
        binding.nombre.movementMethod = ScrollingMovementMethod()
        binding.descripcion.movementMethod = ScrollingMovementMethod()
        binding.restaurante.movementMethod = ScrollingMovementMethod()

        binding.volver.setOnClickListener {
            finish()
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
            val call: Response<ArrayList<Invitaciones>> = getRetrofit().create(APIService::class.java).getInvitaciones("invitacionesadmin/${VariablesGlobales.idEvento}/$query")
            val restaurants: ArrayList<Invitaciones>? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    if (restaurants != null) {
                        invitacionesList = restaurants
                        //binding.txtSize.text = invitacionesList.size.toString()
                    }
                    adapter = BoletoAdapter(invitacionesList)
                    binding.rv.layoutManager = LinearLayoutManager(baseContext)
                    binding.rv.adapter = adapter
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(baseContext, "Sin resultados", Toast.LENGTH_SHORT).show()
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