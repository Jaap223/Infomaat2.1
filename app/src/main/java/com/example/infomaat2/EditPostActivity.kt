package com.example.infomaat2

import android.annotation.SuppressLint

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditPostActivity : AppCompatActivity() {
    private lateinit var drawerHandler: DrawerHandler
    private lateinit var dbHelper: MyDBHelper
    private var postId: Int = -1




    @SuppressLint("Range")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_post)

        drawerHandler = DrawerHandler(this)
        drawerHandler.setupDrawer()

        dbHelper = MyDBHelper(this)

        val titleEditText: EditText = findViewById(R.id.editTextEditTitle)
        val contentEditText: EditText = findViewById(R.id.editTextEditContent)
        val saveButton: Button = findViewById(R.id.buttonSaveEdit)

        postId = intent.getIntExtra("postId", -1)

        if (postId != -1) {
            val postCursor = dbHelper.getPostById(postId.toString())

            if (postCursor.moveToFirst()) {
                val titleIndex = postCursor.getColumnIndex("title")
                val contentIndex = postCursor.getColumnIndex("content")

                if (titleIndex != -1 && contentIndex != -1) {
                    val postTitle = postCursor.getString(titleIndex)
                    val postContent = postCursor.getString(contentIndex)

                    titleEditText.setText(postTitle)
                    contentEditText.setText(postContent)
                } else {

                }
            }
            postCursor.close()
        }

        saveButton.setOnClickListener {
            val newTitle = titleEditText.text.toString()
            val newContent = contentEditText.text.toString()

            dbHelper.updatePost(postId.toString(), newTitle, newContent)

            val resultIntent = Intent()
            setResult(RESULT_OK, resultIntent)

            finish()
        }
    }
}

