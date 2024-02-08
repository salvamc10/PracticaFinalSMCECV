package com.example.practicafinalsmcecv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class RmuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rmu)

        // Obtener referencia del TextView
        val partidosTextView = findViewById<TextView>(R.id.partidosTextView)

        // Llamar a la función que inserta los partidos y obtener la información
        val partidosInfo = obtenerInformacionPartidos()

        // Configurar el texto en el TextView
        partidosTextView.text = partidosInfo

        // Obtener referencia del botón
        val addEventButton = findViewById<Button>(R.id.addEventButton)

        // Configurar el clic del botón para agregar el evento
        addEventButton.setOnClickListener {
            agregarEvento()
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.navigation_eventos -> {
                    // No es necesario iniciar la misma actividad
                    true
                }
                R.id.navigation_configuracion -> {
                    startActivity(Intent(this, ConfiguracionActivity::class.java))
                    true
                }
                R.id.navigation_perfil -> {
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                else -> false
            }
        }.also {
            // Obtener el menú y buscar el ítem correspondiente
            val menu = bottomNavigationView.menu
            val menuItem = menu.findItem(R.id.navigation_eventos)

            // Establecer el ítem actual como seleccionado
            menuItem?.isChecked = true
        }
    }

    private fun obtenerInformacionPartidos(): String {
        // Lógica para obtener la información de los partidos
        // Puedes llamar a la función que inserta los partidos o crear una cadena de texto con la información
        // Devuelve la cadena con la información de los partidos
        return "Real Murcia - Atlético Baleares\n" +
                "Fecha: 2024-01-21\n" +
                "Hora: 12:00\n" +
                "Estadio: Estadio Enrique Roca\n" +
                "Competición: Primera RFEF - Grupo 2 - Jornada 20\n\n"
        // ...
    }

    private fun agregarEvento() {
        // Obtener información del evento seleccionado o crear un modelo de evento según tu estructura
        val eventoSeleccionado = obtenerEventoSeleccionado()

        // Obtener la lista actual de eventos almacenados
        val sharedPreferences = getSharedPreferences("EventosSharedPreferences", MODE_PRIVATE)
        val eventosString = sharedPreferences.getString("eventos", "")
        val eventos = eventosString?.split(";")?.toMutableList() ?: mutableListOf()

        // Verificar si el evento ya existe en la lista
        val eventoExistente = "${eventoSeleccionado.nombre} - ${eventoSeleccionado.fecha}"
        if (!eventos.contains(eventoExistente)) {
            // Agregar el nuevo evento a la lista
            eventos.add(eventoExistente)

            // Guardar la lista actualizada en SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("eventos", eventos.joinToString(";"))
            editor.apply()

            // También puedes considerar abrir directamente EventosActivity después de agregar el evento
            abrirEventosActivity()
        } else {
            // Notificar al usuario que el evento ya está agregado
            // Puedes mostrar un mensaje Toast o usar otro mecanismo de notificación
            // Ejemplo de Toast:
            Toast.makeText(this, "El evento ya está agregado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun abrirEventosActivity() {
        val intent = Intent(this, EventosActivity::class.java)
        startActivity(intent)
    }

    private fun obtenerEventoSeleccionado(): PartidoRepository.Partido {
        // Lógica para obtener el evento seleccionado, puede ser a partir de la información mostrada en el TextView
        // o desde cualquier otra fuente

        // Valores de ejemplo, deberías obtener estos valores según la lógica de tu aplicación
        val id = 1
        val nombre = "Real Murcia - Atlético Baleares"
        val fecha = "2024-01-21"
        val hora = "12:00"
        val estadio = "Estadio Enrique Roca"
        val competicion = "Primera RFEF - Grupo 2 - Jornada 20"

        // Devuelve un objeto Partido con la información del evento seleccionado
        return PartidoRepository.Partido(id, nombre, fecha, hora, estadio, competicion)
    }
}
