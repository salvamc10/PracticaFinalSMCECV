package com.example.practicafinalsmcecv

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class RegisterActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository
    private lateinit var emailEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userRepository = UserRepository(this)
        emailEditText = findViewById(R.id.email)
        usernameEditText = findViewById(R.id.usuario)
        passwordEditText = findViewById(R.id.contraseña)

        findViewById<Button>(R.id.botonRegister).setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val email = emailEditText.text.toString().trim()
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (validateInput(email, username, password)) {
            if (userRepository.insertUser(username, email, password)) {
                Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(this)
                }
                finish()
            } else {
                Toast.makeText(this, "Error al registrar el usuario, puede que el nombre o email ya estén en uso", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Por favor, asegúrese de que todos los campos están llenos y son válidos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInput(email: String, username: String, password: String): Boolean {
        return username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}