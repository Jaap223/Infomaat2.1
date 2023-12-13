package com.example.infomaat2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateToProfielPagina()
    }


  private fun navigateToProfielPagina() {
        val intent = Intent(this, ProfielPaginaActivity::class.java)
        startActivity(intent)
        finish()
    }






}