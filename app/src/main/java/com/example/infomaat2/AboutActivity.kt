
package com.example.infomaat2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class AboutActivity : AppCompatActivity() {

    private lateinit var drawerHandler: DrawerHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        drawerHandler = DrawerHandler(this)
        drawerHandler.setupDrawer()

        findViewById<View>(R.id.btnTerug).setOnClickListener { btnTerugClick() }

    }

    private fun btnTerugClick() {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
    }
}

