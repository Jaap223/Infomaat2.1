package com.example.infomaat2

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        showCustomDialog()


    }


    private fun showCustomDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog.new_post)
        dialog.setCancelable(false)


        val titleEditText: EditText = dialog.findViewByid(R.id.editTextTitle)
        val contentEditText: EditText = dialog.findViewById(R.id.editTextContent)
        val submitButton: Button = dialog.findViewById(R.id.buttonSubmitPost)

        submitButton.setOnClickListener {
            // Retrieve the input values
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()

            // Call a function to handle the data (you can save it, update the UI, etc.)
            handlePostData(title, content)

            // Dismiss the dialog after processing the data
            dialog.dismiss()
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