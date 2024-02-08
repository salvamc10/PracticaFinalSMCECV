package com.example.practicafinalsmcecv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class EventosActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos)

        // Obtener información de la lista de eventos almacenados
        val sharedPreferences = getSharedPreferences("EventosSharedPreferences", MODE_PRIVATE)
        val eventosString = sharedPreferences.getString("eventos", "")
        val eventos = eventosString?.split(";") ?: emptyList()

        // Mostrar la lista de eventos en el TextView
        val eventosTextView = findViewById<TextView>(R.id.eventosTextView)
        eventosTextView.text = eventos.joinToString("\n")

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
}