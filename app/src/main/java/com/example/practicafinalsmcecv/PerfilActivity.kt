package com.example.practicafinalsmcecv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Button

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Obtener referencia al botón de "Cerrar sesión"
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)

        // Configurar el clic del botón de "Cerrar sesión"
        btnCerrarSesion.setOnClickListener {
            // Limpiar cualquier estado de sesión, si es necesario

            // Iniciar la actividad principal (main activity) para iniciar sesión
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.navigation_eventos -> {
                    startActivity(Intent(this, EventosActivity::class.java))
                    true
                }
                R.id.navigation_configuracion -> {
                    startActivity(Intent(this, ConfiguracionActivity::class.java))
                    true
                }
                R.id.navigation_perfil -> {
                    // No es necesario iniciar la misma actividad
                    true
                }
                else -> false
            }
        }.also {
            // Obtener el menú y buscar el ítem correspondiente
            val menu = bottomNavigationView.menu
            val menuItem = menu.findItem(R.id.navigation_perfil)

            // Establecer el ítem actual como seleccionado
            menuItem?.isChecked = true
        }
    }
}
