package com.example.practicafinalsmcecv

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import android.widget.TextView

class RmuActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rmu)
        setupBottomNavigationView()
        val partidosTextView = findViewById<TextView>(R.id.partidosTextView)
        val partidosInfo = obtenerInformacionPartidos()
        val addEventButton = findViewById<Button>(R.id.addEventButton)
        val shareEventButton = findViewById<Button>(R.id.shareEventButton)

        partidosTextView.text = partidosInfo

        addEventButton.setOnClickListener {
            agregarEvento()
        }

        shareEventButton.setOnClickListener {
            compartirEvento(partidosInfo)
        }
    }

    override fun getCurrentNavItem(): Int {
        return R.id.navigation_home
    }

    private fun obtenerInformacionPartidos(): String {
        return "Real Murcia - Atlético Baleares\n" +
                "Fecha: 2024-01-21\n" +
                "Hora: 12:00\n" +
                "Estadio: Estadio Enrique Roca\n" +
                "Competición: Primera RFEF - Grupo 2 - Jornada 20\n\n"
    }

    private fun agregarEvento() {
        val eventoSeleccionado = obtenerEventoSeleccionado()
        val username = getSharedPreferences("UserLoginPrefs", MODE_PRIVATE).getString("username", "defaultUser")
        val sharedPreferences = getSharedPreferences("EventosSharedPreferences", MODE_PRIVATE)
        val eventosKey = "eventos_$username"
        val eventosString = sharedPreferences.getString(eventosKey, "")
        val eventos = eventosString?.split(";")?.toMutableList() ?: mutableListOf()
        val eventoExistente = "${eventoSeleccionado.nombre} - ${eventoSeleccionado.fecha}"
        if (!eventos.contains(eventoExistente)) {
            eventos.add(eventoExistente)
            val editor = sharedPreferences.edit()
            editor.putString(eventosKey, eventos.joinToString(";"))
            editor.apply()
            abrirEventosActivity()
        } else {
            Toast.makeText(this, "El evento ya está agregado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun abrirEventosActivity() {
        val intent = Intent(this, EventosActivity::class.java)
        startActivity(intent)
    }

    private fun obtenerEventoSeleccionado(): PartidoRepository.Partido {
        val id = 1
        val nombre = "Real Murcia - Atlético Baleares"
        val fecha = "2024-01-21"
        val hora = "12:00"
        val estadio = "Estadio Enrique Roca"
        val competicion = "Primera RFEF - Grupo 2 - Jornada 20"

        return PartidoRepository.Partido(id, nombre, fecha, hora, estadio, competicion)
    }

    private fun compartirEvento(infoEvento: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, infoEvento)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}
