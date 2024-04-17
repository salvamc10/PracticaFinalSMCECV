package com.example.practicafinalsmcecv

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class PerfilActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        setupBottomNavigationView()

        // Obtener las vistas
        val tvNombreUsuario = findViewById<TextView>(R.id.tvNombreUsuario)
        val tvEmailUsuario = findViewById<TextView>(R.id.tvEmailUsuario)

        // Cargar las preferencias
        val prefs = getSharedPreferences("UserLoginPrefs", MODE_PRIVATE)
        val username = prefs.getString("username", "No encontrado")
        val email = prefs.getString("email", "No encontrado")

        // Establecer los valores en los TextViews
        tvNombreUsuario.text = username
        tvEmailUsuario.text = email

        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener {
            clearPreferences()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }

    override fun getCurrentNavItem(): Int {
        return R.id.navigation_perfil
    }

    private fun clearPreferences() {
        val prefs = getSharedPreferences("UserLoginPrefs", MODE_PRIVATE)
        prefs.edit().clear().apply()
        val themePrefs = getSharedPreferences("MyPrefsFile", MODE_PRIVATE)
        themePrefs.edit().putBoolean("dark_mode", false).apply()
    }
}