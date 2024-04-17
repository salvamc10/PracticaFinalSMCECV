package com.example.practicafinalsmcecv

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        setupBottomNavigationView()
    }

    protected fun setupBottomNavigationView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> navigateTo(HomeActivity::class.java)
                R.id.navigation_eventos -> navigateTo(EventosActivity::class.java)
                R.id.navigation_configuracion -> navigateTo(ConfiguracionActivity::class.java)
                R.id.navigation_perfil -> navigateTo(PerfilActivity::class.java)
                else -> false
            }
        }
        highlightCurrentItem()
    }

    private fun highlightCurrentItem() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val menu = bottomNavigationView.menu
        val menuItem = menu.findItem(getCurrentNavItem())
        menuItem?.isChecked = true
    }

    abstract fun getCurrentNavItem(): Int

    private fun navigateTo(activityClass: Class<*>): Boolean {
        if (this::class.java != activityClass) {
            startActivity(Intent(this, activityClass).apply {
                flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            })
        }
        return true
    }
}