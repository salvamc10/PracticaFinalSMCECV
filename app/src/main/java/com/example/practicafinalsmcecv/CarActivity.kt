package com.example.practicafinalsmcecv

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class CarActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)
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
        return "Cartagena - Villarreal B\n" +
                "Fecha: 2024-01-14\n" +
                "Hora: 16:15\n" +
                "Estadio: Estadio Municipal Cartagonova\n" +
                "Competición: LaLiga Hypermotion - Jornada 22\n\n"
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
        val nombre = "Cartagena - Villarreal B"
        val fecha = "2024-01-14"
        val hora = "16:15"
        val estadio = "Estadio Municipal Cartagonova"
        val competicion = "LaLiga Hypermotion - Jornada 22"

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
