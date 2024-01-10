package com.example.infomaat2

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class DrawerHandler(private val activity: AppCompatActivity) {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    fun setupDrawer() {
        drawerLayout = activity.findViewById(R.id.drawerLayout)
        val navView: NavigationView = activity.findViewById(R.id.nav_view)

        toggle = object : ActionBarDrawerToggle(
            activity,
            drawerLayout,
            activity.findViewById(R.id.toolbar),
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
            handleNavigation(menuItem.itemId)
            drawerLayout.close()
            true
        }
    }

    private fun handleNavigation(itemId: Int) {
        when (itemId) {
            R.id.nav_home -> goToHome()
            R.id.nav_favorite -> goToFavorite()
            R.id.nav_postplts -> goToPosts()
            R.id.nav_about -> aboutUs()
            R.id.nav_persoverzicht -> goToPersOverzicht()
            R.id.nav_inbox -> goToInbox()
            R.id.nav_profile -> navigateToProfielPagina()
            R.id.nav_login -> goToLogin()
            R.id.nav_share -> goToShare()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(activity.applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToProfielPagina() {
        val intent = Intent(activity, ProfielPaginaActivity::class.java)
        activity.startActivity(intent)
    }

    private fun goToLogin() {
        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent)
    }

    private fun aboutUs() {
        val intent = Intent(activity, AboutActivity::class.java)
        activity.startActivity(intent)
    }

    private fun goToInbox() {
        val intent = Intent(activity, InboxActivity::class.java)
        activity.startActivity(intent)
    }

    private fun goToPosts() {
        val intent = Intent(activity, PostsActivity::class.java)
        activity.startActivity(intent)
    }

    private fun goToHome() {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
    }

    private fun goToFavorite() {
        val intent = Intent(activity, FavorieteActivity::class.java)
        activity.startActivity(intent)
    }

    private fun goToPersOverzicht() {
        val intent = Intent(activity, PersoonlijkeOverzichtActivity::class.java)
        activity.startActivity(intent)
    }

    private fun goToShare() {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
    }
}