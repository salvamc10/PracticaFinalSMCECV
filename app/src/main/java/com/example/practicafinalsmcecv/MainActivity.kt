package com.example.practicafinalsmcecv

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userRepository = UserRepository(this)

        val loginButton: Button = findViewById(R.id.botonLogin)
        val registerButton: Button = findViewById(R.id.botonRegister)

        loginButton.setOnClickListener {
            performLogin()
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun performLogin() {
        val username = findViewById<EditText>(R.id.usuario).text.toString().trim()
        val password = findViewById<EditText>(R.id.contraseña).text.toString().trim()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            if (userRepository.authenticateUser(username, password)) {
                val user = userRepository.getUserDetails(username)
                user?.let {
                    val prefs = getSharedPreferences("UserLoginPrefs", MODE_PRIVATE)
                    with(prefs.edit()) {
                        putString("username", user.username)
                        putString("email", user.email)
                        apply()
                    }
                }
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
}