
package com.example.infomaat2

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

class PostsActivity : AppCompatActivity() {

    private lateinit var drawerHandler: DrawerHandler
    companion object {
        private const val REQUEST_CODE_NEW_POST = 1
        private const val REQUEST_CODE_EDIT_POST = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        findViewById<CardView>(R.id.cardView1).setOnClickListener { showPopupInfo1(it) }
        findViewById<CardView>(R.id.cardView2).setOnClickListener { showPopupInfo2(it) }
        findViewById<CardView>(R.id.cardView3).setOnClickListener { showPopupInfo3(it) }
        findViewById<View>(R.id.button5).setOnClickListener { showPostInputForm() }
        findViewById<View>(R.id.button6).setOnClickListener { showPopupInfo6(it) }

        drawerHandler = DrawerHandler(this)
        drawerHandler.setupDrawer()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_NEW_POST -> {
                // Handle new post result
                if (resultCode == RESULT_OK) {
                    val title = data?.getStringExtra("title")
                    val content = data?.getStringExtra("content")

                    if (!title.isNullOrBlank() && !content.isNullOrBlank()) {
                        handlePostDataInCardView(title, content)
                    }
                }
            }

            REQUEST_CODE_EDIT_POST -> {

                if (resultCode == RESULT_OK) {

                    updateUIWithPosts()
                }
            }
        }
    }


    private fun showPopupInfo1(view: View) {
        showToast("Posts informatie")
    }

    private fun showPopupInfo2(view: View) {
        showToast("Info 2 Popup")
    }

    private fun showPopupInfo3(view: View) {
        showToast("Info 3 Popup")
    }

    private fun showPopupInfo4(view: View) {
        showToast("Info 4 Popup")
    }

    private fun showPopupInfo5(view: View) {
        showToast("Post popup")
    }

    private fun showPopupInfo6(view: View) {
        showToast("Bewerken Popup")


        val postId = 1 // Replace with the actual postId

        val intent = Intent(this, EditPostActivity::class.java)
        intent.putExtra("postId", postId)
        startActivityForResult(intent, REQUEST_CODE_EDIT_POST)
    }

    private fun showPostInputForm() {
        val intent = Intent(this, NewPostActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_NEW_POST)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun handlePostDataInCardView(title: String, content: String) {
        val dbHelper = MyDBHelper(this)
        dbHelper.insertPost(title, content)
        updateUIWithPosts()
    }

    private fun updateUIWithPosts() {
        val dbHelper = MyDBHelper(this)
        val postsCursor = dbHelper.getPosts()
        val postContainer = findViewById<LinearLayout>(R.id.postContainer)

        // Clear existing views in the postContainer before adding new ones
        postContainer.removeAllViews()

        if (postsCursor.moveToFirst()) {
            do {
                val postId = postsCursor.getInt(postsCursor.getColumnIndex("POSTID"))
                val postTitle = postsCursor.getString(postsCursor.getColumnIndex("title"))
                val postContent = postsCursor.getString(postsCursor.getColumnIndex("content"))


                val postTextView = TextView(this)
                postTextView.text = "Title: $postTitle\nContent: $postContent"

                
                postTextView.setTextColor(ContextCompat.getColor(this, R.color.black))
                postTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)

                // Add the TextView to the postContainer
                postContainer.addView(postTextView)

            } while (postsCursor.moveToNext())
        }

        // Close the cursor when done
        postsCursor.close()
    }
}

