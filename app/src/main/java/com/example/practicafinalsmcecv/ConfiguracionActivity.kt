package com.example.practicafinalsmcecv

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.bottomnavigation.BottomNavigationView

class ConfiguracionActivity : AppCompatActivity() {

    private val PREFS_NAME = "MyPrefsFile"
    private val DARK_MODE = "dark_mode"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDarkMode = sharedPref.getBoolean(DARK_MODE, true) // por defecto es modo oscuro
        setAppTheme(isDarkMode)
        setContentView(R.layout.activity_configuracion)
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> navigateTo(HomeActivity::class.java)
                R.id.navigation_eventos -> navigateTo(EventosActivity::class.java)
                R.id.navigation_perfil -> navigateTo(PerfilActivity::class.java)
                R.id.navigation_configuracion -> true // Actual página, no acción
                else -> false
            }
        }
        bottomNavigationView.selectedItemId = R.id.navigation_configuracion // Establecer selección actual
    }

    fun toggleDarkMode(view: View) {
        val sharedPref: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDarkMode = sharedPref.getBoolean(DARK_MODE, true)
        with(sharedPref.edit()) {
            putBoolean(DARK_MODE, !isDarkMode)
            apply()
        }
        setAppTheme(!isDarkMode)
        recreate()
    }

    private fun setAppTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun navigateTo(activityClass: Class<*>): Boolean {
        startActivity(Intent(this, activityClass))
        return true
    }

}