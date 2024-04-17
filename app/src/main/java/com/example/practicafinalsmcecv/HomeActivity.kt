package com.example.practicafinalsmcecv

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupBottomNavigationView()
        setupCardButtons()
    }

    override fun getCurrentNavItem(): Int {
        return R.id.navigation_home
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
}