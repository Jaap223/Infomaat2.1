package com.example.infomaat2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigateButton: Button = findViewById(R.id.navigateButton)
        val loginButton: Button = findViewById(R.id.loginButton)
        val aboutus: Button = findViewById(R.id.btnAboutUs)

        navigateButton.setOnClickListener {
            navigateToProfielPagina()
        }

        loginButton.setOnClickListener {
            goToLogin()
        }
    }

    fun navigateToProfielPagina() {
        val intent = Intent(this, ProfielPaginaActivity::class.java)
        startActivity(intent)
    }

    fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun AboutUs () {
        val intent = Intent(this, AboutUsActivity::class.java)
        startActivity(intent)
    }
}
