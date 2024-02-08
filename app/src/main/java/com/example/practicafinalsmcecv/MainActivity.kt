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

        // En el MainActivity.kt para el botón de inicio de sesión
        val loginButton: Button = findViewById(R.id.botonLogin)
        loginButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.usuario).text.toString()
            val password = findViewById<EditText>(R.id.contraseña).text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                if (userRepository.authenticateUser(username, password)) {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                    // Agregar lógica para ir a la siguiente pantalla (ActivityEventos)
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        val registerButton: Button = findViewById(R.id.botonRegister)
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
