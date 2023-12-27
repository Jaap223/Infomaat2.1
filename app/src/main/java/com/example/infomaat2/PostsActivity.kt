package com.example.infomaat2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import kotlinx.coroutines.newFixedThreadPoolContext

class PostsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)




        findViewById<CardView>(R.id.cardView1).setOnClickListener { showPopupInfo1(it) }
        findViewById<CardView>(R.id.cardView2).setOnClickListener { showPopupInfo2(it) }
        findViewById<CardView>(R.id.cardView3).setOnClickListener { showPopupInfo3(it) }
        findViewById<View>(R.id.button5).setOnClickListener { showPostInputForm() }
        findViewById<View>(R.id.button6).setOnClickListener { showPopupInfo6(it) }

    }
    companion object {
        private const val REQUEST_CODE_NEW_POST = 1
    }
    fun showPopupInfo1(view: View) {
        showToast("Posts informatie")

    }

    fun showPopupInfo2(view: View) {
        showToast("Info 2 Popup")

    }

    fun showPopupInfo3(view: View) {
        showToast("Info 3 Popup")

    }

    fun showPopupInfo4(view: View) {
        showToast("Info 4 Popup")

    }

    fun showPopupInfo5(view: View) {
        showToast("Post popup")

    }


    fun showPopupInfo6(view: View) {
        showToast("Bewerken Popup")

    }

    private fun showPostInputForm(){

        val intent = Intent(this, NewPostActivity::class.java)
        startActivity(intent)
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
