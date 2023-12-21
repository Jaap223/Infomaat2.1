
package com.example.infomaat2


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.infomaat2.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

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
                R.id.nav_persoverzicht -> showToast("Clicked Persoonlijk overzicht")
                R.id.nav_inbox -> showToast("Clicked Inbox")
                R.id.nav_profile -> showToast("Clicked Profile")
                R.id.nav_login -> showToast("Clicked Login")
                R.id.nav_share -> showToast("Clicked Share")
                // ... handle other menu items
            }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        when (item.itemId) {
            // Handle other action bar items
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToProfielPagina() {
        // Implement your navigation logic
    }

    private fun goToLogin() {
        // Implement your login navigation logic
    }

    private fun aboutUs() {
        // Implement your about us navigation logic
    }

    private fun goToPosts() {

    }

}