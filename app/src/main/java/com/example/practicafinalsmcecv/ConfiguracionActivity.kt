package com.example.practicafinalsmcecv

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate

class ConfiguracionActivity : BaseActivity() {

    private val PREFS_NAME = "MyPrefsFile"
    private val DARK_MODE = "dark_mode"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDarkMode = sharedPref.getBoolean(DARK_MODE, false)
        setAppTheme(isDarkMode)
        setContentView(R.layout.activity_configuracion)
        setupBottomNavigationView()
    }

    override fun getCurrentNavItem(): Int {
        return R.id.navigation_configuracion
    }

    fun toggleDarkMode(view: View) {
        val sharedPref: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDarkMode = sharedPref.getBoolean(DARK_MODE, false)
        with(sharedPref.edit()) {
            putBoolean(DARK_MODE, !isDarkMode)
            apply()
        }
        setAppTheme(!isDarkMode)
    }

    private fun setAppTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
