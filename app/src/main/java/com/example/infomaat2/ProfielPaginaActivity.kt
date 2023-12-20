package com.example.infomaat2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfielPaginaActivity : AppCompatActivity() {

    private lateinit var infomaatImageView: ImageView
    private lateinit var profielImageView: ImageView
    private lateinit var usernametextView: TextView
    private lateinit var passwordTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var veranderButton: Button
    private lateinit var dbHelper: MyDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiel_pagina)

        dbHelper = MyDBHelper(this)

        initializeViews()

        veranderButton.setOnClickListener {
            val userId = getUserIdForLoggedInUser()

            if (userId.isNotEmpty()) {
                val newUserName = usernametextView.text.toString()
                val newPassword = passwordTextView.text.toString()
                val newEmail = emailTextView.text.toString()

                if (isInputValid(newUserName, newPassword, newEmail)) {
                    updateProfileInDatabase(userId, newUserName, newPassword, newEmail)
                    Toast.makeText(this, "Profiel bijgewerkt", Toast.LENGTH_SHORT).show()
                    navigateToMainActivity()
                } else {
                    Toast.makeText(this, "Vul alle velden in", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Gebruiker niet gevonden", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun initializeViews() {
        infomaatImageView = findViewById(R.id.infomaat)
        profielImageView = findViewById(R.id.profileImage)
        usernametextView = findViewById(R.id.usernameTextView)
        emailTextView = findViewById(R.id.emailTextView)
        veranderButton = findViewById(R.id.veranderButton)
        passwordTextView = findViewById(R.id.passwordTextView)
    }

    fun updateProfileInDatabase(userId: String, newUserName: String, newPassword: String, newEmail: String) {
        try {
            dbHelper.updateProfiel(userId, newUserName, newPassword, newEmail)
            Log.d("ProfielPaginaActivity", "Profiel geüpdatet")
        } catch (e: Exception) {
            Log.e("ProfielPaginaActivity", "Fout bij updaten profiel: ${e.message}")
        }
    }

    fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun getUserIdForLoggedInUser(): String {

        val sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)
        return sharedPreferences.getString("userId", "") ?: ""
    }

    fun isInputValid(userName: String, password: String, email: String): Boolean {
        if (userName.isEmpty() || password.isEmpty() || email.isEmpty()) {
            return false
        }
        return true
    }
}