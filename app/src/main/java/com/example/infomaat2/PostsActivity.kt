package com.example.infomaat2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PostsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
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


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
