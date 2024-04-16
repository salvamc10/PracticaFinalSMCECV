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

        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener {
            clearEventosPreferences()

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
                    true
                }
                else -> false
            }
        }.also {
            val menu = bottomNavigationView.menu
            val menuItem = menu.findItem(R.id.navigation_perfil)
            menuItem?.isChecked = true
        }
    }

    private fun clearEventosPreferences() {
        val sharedPreferences = getSharedPreferences("EventosSharedPreferences", MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}