package com.example.infomaat2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> Toast.makeText(applicationContext,"Clicked Home",Toast.LENGTH_SHORT).show()
                R.id.nav_favorite -> Toast.makeText(applicationContext,"Clicked Favorite",Toast.LENGTH_SHORT).show()
                R.id.nav_postplts -> Toast.makeText(applicationContext,"Clicked Post plaasten",Toast.LENGTH_SHORT).show()
                R.id.nav_about -> Toast.makeText(applicationContext,"Clicked About",Toast.LENGTH_SHORT).show()
                R.id.nav_persoverzicht -> Toast.makeText(applicationContext,"Clicked Personen overzicht",Toast.LENGTH_SHORT).show()
                R.id.nav_inbox -> Toast.makeText(applicationContext,"Clicked Inbox",Toast.LENGTH_SHORT).show()
                R.id.nav_profile -> Toast.makeText(applicationContext,"Clicked Profile",Toast.LENGTH_SHORT).show()
                R.id.nav_login -> Toast.makeText(applicationContext,"Clicked Login",Toast.LENGTH_SHORT).show()
                R.id.nav_share -> Toast.makeText(applicationContext,"Clicked Share",Toast.LENGTH_SHORT).show()

            }

            true
        }



        val navigateButton: Button = findViewById(R.id.navigateButton)
        val loginButton: Button = findViewById(R.id.loginButton)
        val aboutus: Button = findViewById(R.id.btnAboutUs)

        navigateButton.setOnClickListener {
            navigateToProfielPagina()
        }


        fun onOptionsItemSelected(item: MenuItem): Boolean {

            if (toggle.onOptionsItemSelected(item)){
                return true
            }

            return super.onOptionsItemSelected(item)

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


