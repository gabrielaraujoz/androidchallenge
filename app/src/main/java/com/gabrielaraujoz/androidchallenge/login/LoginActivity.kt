package com.gabrielaraujoz.androidchallenge.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gabrielaraujoz.androidchallenge.restaurants.RestaurantListActivity
import com.gabrielaraujoz.androidchallenge.R
import com.gabrielaraujoz.androidchallenge.register.RegisterActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var buttonRegister: MaterialButton
    private lateinit var buttonLogin: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonRegister = findViewById<MaterialButton>(R.id.btnRegister)
        buttonLogin = findViewById<MaterialButton>(R.id.btnLogin)
        email = findViewById<TextInputEditText>(R.id.txtInputLoginEmail)
        password = findViewById<TextInputEditText>(R.id.txtInputLoginPassword)

        setupButtons()

    }


    private fun setupButtons() {
        buttonRegister.setOnClickListener(){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener(){
            verificaCamposLogin(email, password)
        }

    }

    private fun verificaCamposLogin(email: TextInputEditText, password: TextInputEditText) {
        when {
            email.text.toString().isEmpty() -> {
                email.error = "Campo vazio"
            }
            password.text.toString().isEmpty() -> {
                password.error = "Campo vazio"
            }
            password.text.toString().trim().length < 6 -> {
                password.error = "A senha deve ter pelo menos 6 caracteres"
            }
            else -> {
                val intent = Intent(this, RestaurantListActivity::class.java)
                startActivity(intent)
                finish()

            }
        }

    }
}