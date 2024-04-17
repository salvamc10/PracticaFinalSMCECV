package com.example.practicafinalsmcecv

import android.os.Bundle
import android.widget.TextView

class EventosActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos)
        setupBottomNavigationView()
        val username = getSharedPreferences("UserLoginPrefs", MODE_PRIVATE).getString("username", "defaultUser")
        val eventosKey = "eventos_$username"
        val sharedPreferences = getSharedPreferences("EventosSharedPreferences", MODE_PRIVATE)
        val eventosString = sharedPreferences.getString(eventosKey, "")
        val eventos = eventosString?.split(";") ?: emptyList()
        val eventosTextView = findViewById<TextView>(R.id.eventosTextView)
        eventosTextView.text = eventos.joinToString("\n")
    }

    override fun getCurrentNavItem(): Int {
        return R.id.navigation_eventos
    }
}
