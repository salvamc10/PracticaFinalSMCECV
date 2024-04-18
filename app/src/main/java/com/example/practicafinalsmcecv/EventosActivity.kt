package com.example.practicafinalsmcecv

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView

class EventosActivity : BaseActivity() {
    private lateinit var eventsContainer: LinearLayout
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var eventosKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos)
        setupBottomNavigationView()

        val username = getSharedPreferences("UserLoginPrefs", MODE_PRIVATE).getString("username", "defaultUser")
        eventosKey = "eventos_$username"
        sharedPreferences = getSharedPreferences("EventosSharedPreferences", MODE_PRIVATE)

        eventsContainer = findViewById<LinearLayout>(R.id.eventsContainer)

        updateUI()
    }

    private fun updateUI() {
        val eventos = sharedPreferences.getString(eventosKey, "")?.split(";")?.filterNot { it.isBlank() }?.toMutableList() ?: mutableListOf()
        eventsContainer.removeAllViews()

        eventos.forEachIndexed { index, evento ->
            val cardView = CardView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = resources.getDimensionPixelSize(R.dimen.card_margin_top)
                }
                radius = resources.getDimensionPixelSize(R.dimen.card_corner_radius).toFloat()
                cardElevation = resources.getDimensionPixelSize(R.dimen.card_elevation).toFloat()
                setContentPadding(32, 32, 32, 32)

                val cardLinearLayout = LinearLayout(this@EventosActivity).apply {
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER_HORIZONTAL

                    val eventTextView = TextView(context).apply {
                        text = evento
                        textSize = 16f
                        gravity = Gravity.CENTER
                    }

                    val button = Button(context, null, 0, R.style.ButtonStyle).apply {
                        text = "Eliminar"
                        setOnClickListener {
                            eventos.removeAt(index)
                            val editor = sharedPreferences.edit()
                            editor.putString(eventosKey, eventos.joinToString(";"))
                            editor.apply()
                            updateUI()
                        }
                    }

                    addView(eventTextView)
                    addView(button)
                }

                addView(cardLinearLayout)
            }
            eventsContainer.addView(cardView)
        }
    }

    override fun getCurrentNavItem(): Int {
        return R.id.navigation_eventos
    }
}