package com.example.practicafinalsmcecv

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class RegisterActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userRepository = UserRepository(this)

        // En el RegisterActivity.kt para el botón de registro
        val registerButton: Button = findViewById(R.id.botonRegister)
        registerButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.email).text.toString()
            val username = findViewById<EditText>(R.id.usuario).text.toString()
            val password = findViewById<EditText>(R.id.contraseña).text.toString()

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                if (userRepository.insertUser(username, email, password)) {
                    Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()

                    // Crear un Intent para ir a la actividad de inicio de sesión
                    val intent = Intent(this, MainActivity::class.java)

                    // Limpiar la pila de actividades y comenzar la actividad de inicio de sesión
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                    // Cerrar la actividad de registro
                    finish()
                } else {
                    Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

    }
}