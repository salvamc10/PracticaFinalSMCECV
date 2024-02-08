package com.example.practicafinalsmcecv

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.bottomnavigation.BottomNavigationView

class ConfiguracionActivity : AppCompatActivity() {

    private val PREFS_NAME = "MyPrefsFile"
    private val DARK_MODE = "dark_mode"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDarkMode = sharedPref.getBoolean(DARK_MODE, true)

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setContentView(R.layout.activity_configuracion)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

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
            val menuItem = menu.findItem(R.id.navigation_configuracion)

            // Establecer el ítem actual como seleccionado
            menuItem?.isChecked = true
        }
    }
    fun toggleDarkMode(view: View) {
        val sharedPref: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDarkMode = sharedPref.getBoolean(DARK_MODE, true)
        val editor = sharedPref.edit()

        if (isDarkMode) {
            editor.putBoolean(DARK_MODE, false)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            editor.putBoolean(DARK_MODE, true)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        editor.apply()

        // Actualizar la interfaz de usuario
        recreate()
    }
}