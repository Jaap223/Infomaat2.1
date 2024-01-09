package com.example.infomaat2

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        showCustomDialog()
    }

    private fun showCustomDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_new_post)
        dialog.setCancelable(false)

        val titleEditText = dialog.findViewById<EditText>(R.id.editTextTitle)
        val contentEditText = dialog.findViewById<EditText>(R.id.editTextContent)
        val submitButton = dialog.findViewById<Button>(R.id.buttonSubmitPost)

        submitButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val content = contentEditText.text.toString().trim()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                handlePostData(title, content)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Title and content cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    private fun handlePostData(title: String, content: String) {
        setResult(RESULT_OK, Intent().apply {
            putExtra("title", title)
            putExtra("content", content)
        })
        finish()
    }
}
