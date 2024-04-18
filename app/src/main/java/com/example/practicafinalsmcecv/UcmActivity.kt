package com.example.practicafinalsmcecv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class UcmActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ucm)
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
        return "UCAM CF - Marbella FC\n" +
                "Fecha: 2024-01-14\n" +
                "Hora: 17:00\n" +
                "Estadio: Estadio de La Condomina\n" +
                "Competición: Segunda RFEF - Grupo 4 - Jornada 18\n\n"
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
        val nombre = "UCAM CF - Marbella FC"
        val fecha = "2024-01-14"
        val hora = "17:00"
        val estadio = "Estadio de La Condomina"
        val competicion = "Segunda RFEF - Grupo 4 - Jornada 18"

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