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

        setupBottomNavigationView()
        setupCardButtons()
    }

    private fun setupBottomNavigationView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> true
                R.id.navigation_eventos -> navigateTo(EventosActivity::class.java)
                R.id.navigation_configuracion -> navigateTo(ConfiguracionActivity::class.java)
                R.id.navigation_perfil -> navigateTo(PerfilActivity::class.java)
                else -> false
            }
        }
    }

    private fun setupCardButtons() {
        setCardButton(R.id.buttonP1, UcmActivity::class.java)
        setCardButton(R.id.buttonP2, RmuActivity::class.java)
        setCardButton(R.id.buttonP3, CarActivity::class.java)
        setCardButton(R.id.buttonP4, ElxActivity::class.java)
    }

    private fun setCardButton(buttonId: Int, activityClass: Class<*>) {
        findViewById<Button>(buttonId).setOnClickListener {
            startActivity(Intent(this, activityClass))
        }
    }

    private fun navigateTo(activityClass: Class<*>): Boolean {
        startActivity(Intent(this, activityClass))
        return true
    }
}