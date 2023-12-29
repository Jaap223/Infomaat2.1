package com.example.infomaat2

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewPostActivity : AppCompatActivity() {

    private lateinit var drawerHandler: DrawerHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        showCustomDialog()

        drawerHandler = DrawerHandler(this)
        drawerHandler.setupDrawer()
    }

    private fun showCustomDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_new_post)
        dialog.setCancelable(false)

        val titleEditText: EditText = dialog.findViewById(R.id.editTextTitle)
        val contentEditText: EditText = dialog.findViewById(R.id.editTextContent)
        val submitButton: Button = dialog.findViewById(R.id.buttonSubmitPost)

        submitButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val content = contentEditText.text.toString().trim()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                handlePostData(title, content)
                dialog.dismiss()
            } else {
                // Show an error message or prevent submission if fields are empty
                Toast.makeText(this, "Title and content cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    private fun handlePostData(title: String, content: String) {
        val intent = Intent()
        intent.putExtra("title", title)
        intent.putExtra("content", content)
        setResult(RESULT_OK, intent)
        finish()
    }
}