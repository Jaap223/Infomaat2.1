package com.example.infomaat2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        toggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open,
            R.string.close
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                // You can perform any additional actions when the drawer is opened
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                // You can perform any additional actions when the drawer is closed
            }
        }

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> showToast("Clicked Home")
                R.id.nav_favorite -> showToast("Clicked Favorite")
                R.id.nav_postplts -> goToPosts()
                R.id.nav_about -> aboutUs()
                R.id.nav_persoverzicht -> showToast("Clicked Persoonlijk overzicht")
                R.id.nav_inbox -> showToast("Clicked Inbox")
                R.id.nav_profile -> navigateToProfielPagina()
                R.id.nav_login -> goToLogin()
                R.id.nav_share -> showToast("Clicked Share")
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        val navigateButton: Button = findViewById(R.id.navigateButton)
        val loginButton: Button = findViewById(R.id.loginButton)
        val aboutUs: Button = findViewById(R.id.btnAboutUs)
        val goToPosts: Button = findViewById(R.id.PostsButton)

        navigateButton.setOnClickListener {
            navigateToProfielPagina()
        }

        loginButton.setOnClickListener {
            goToLogin()
        }

        aboutUs.setOnClickListener {
            aboutUs()
        }

        goToPosts.setOnClickListener {
            goToPosts()
        }
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

    private fun goToPosts() {
        val intent = Intent(this, PostsActivity::class.java)
        startActivity(intent)
    }}
