package com.example.infomaat2

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar // Import Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar) // Add this line

        setSupportActionBar(toolbar) // Add this line

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> showToast("Clicked Home")
                R.id.nav_favorite -> showToast("Clicked Favorite")
                R.id.nav_postplts -> showToast("Clicked Post plaatsen")
                R.id.nav_about -> showToast("Clicked About")
                R.id.nav_persoverzicht -> showToast("Clicked Personen overzicht")
                R.id.nav_inbox -> showToast("Clicked Inbox")
                R.id.nav_profile -> showToast("Clicked Profile")
                R.id.nav_login -> showToast("Clicked Login")
                R.id.nav_share -> showToast("Clicked Share")
            }
            true
        }

        val navigateButton: Button = findViewById(R.id.navigateButton)
        val loginButton: Button = findViewById(R.id.loginButton)
        val aboutUs: Button = findViewById(R.id.btnAboutUs)

        navigateButton.setOnClickListener {
            navigateToProfielPagina()
        }

        loginButton.setOnClickListener {
            goToLogin()
        }

        aboutUs.setOnClickListener {
            aboutUs()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToProfielPagina() {
        val intent = Intent(this, ProfielPaginaActivity::class.java)
        startActivity(intent)
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun aboutUs() {
        val intent = Intent(this, AboutUsActivity::class.java)
        startActivity(intent)
    }
}