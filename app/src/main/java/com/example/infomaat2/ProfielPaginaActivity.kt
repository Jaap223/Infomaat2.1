package com.example.infomaat2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ProfielPaginaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiel_pagina)

        val infomaatImageView = findViewById<ImageView>(R.id.infomaat)
        val profielImageView = findViewById<ImageView>(R.id.profileImage)
        val usernametextView = findViewById<TextView>(R.id.usernameTextView)
        val emailTextView = findViewById<TextView>(R.id.emailTextView)
        val veranderButton = findViewById<Button>(R.id.veranderButton)



        veranderButton.setOnClickListener()
        {

        }





    }
}