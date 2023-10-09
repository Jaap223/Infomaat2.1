package com.example.infomaat2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        var inf : ImageView = findViewById(R.id.content)
        inf.alpha = 0f

        inf.animate().setDuration(6500).alpha(1f).withEndAction {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }





    }
}