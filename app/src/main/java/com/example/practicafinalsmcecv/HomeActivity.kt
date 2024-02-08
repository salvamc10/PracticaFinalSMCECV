package com.example.practicafinalsmcecv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
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
                    startActivity(Intent(this, PerfilActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Botón de la Tarjeta 1
        findViewById<Button>(R.id.buttonP1).setOnClickListener {
            startActivity(Intent(this, UcmActivity::class.java))
        }

        // Botón de la Tarjeta 2
        findViewById<Button>(R.id.buttonP2).setOnClickListener {
            startActivity(Intent(this, RmuActivity::class.java))
        }

        // Botón de la Tarjeta 3
        findViewById<Button>(R.id.buttonP3).setOnClickListener {
            startActivity(Intent(this, CarActivity::class.java))
        }

        // Botón de la Tarjeta 4
        findViewById<Button>(R.id.buttonP4).setOnClickListener {
            startActivity(Intent(this, ElxActivity::class.java))
        }
    }
}
