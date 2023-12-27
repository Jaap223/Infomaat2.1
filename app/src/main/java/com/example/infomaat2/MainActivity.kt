// MainActivity.kt
package com.example.infomaat2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var drawerHandler: DrawerHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerHandler = DrawerHandler(this)
        drawerHandler.setupDrawer()

        // Your other onCreate logic...
    }

    // Your other methods...
}
