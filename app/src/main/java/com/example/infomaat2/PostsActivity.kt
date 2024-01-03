package com.example.infomaat2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PostsActivity : AppCompatActivity() {

    private lateinit var drawerHandler: DrawerHandler
    private lateinit var containerLayout: RelativeLayout
    private lateinit var postAdapter: PostAdapter
    private lateinit var recyclerView: RecyclerView

    companion object {
        private const val REQUEST_CODE_NEW_POST = 1
        private const val REQUEST_CODE_EDIT_POST = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        drawerHandler = DrawerHandler(this)
        drawerHandler.setupDrawer()

        recyclerView = findViewById(R.id.recyclerView)

        val posts: List<Post> = mutableListOf()

        postAdapter = PostAdapter(posts)

        recyclerView.adapter = postAdapter

        recyclerView.layoutManager = LinearLayoutManager(this)
        setupUI()
    }

    private fun setupUI() {
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        findViewById<CardView>(R.id.cardView1).setOnClickListener { showPopupInfo1(it) }
        findViewById<View>(R.id.buttonPost).setOnClickListener { showPostInputForm() }

        updateUIWithPosts()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_NEW_POST -> handleNewPostResult(resultCode, data)
            REQUEST_CODE_EDIT_POST -> handleEditPostResult(resultCode)
        }
    }


    private fun handleNewPostResult(resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            val title = data?.getStringExtra("title")
            val content = data?.getStringExtra("content")

            if (!title.isNullOrBlank() && !content.isNullOrBlank()) {
                handlePostDataInCardView(title, content)
            }
        }
    }

    private fun handleEditPostResult(resultCode: Int) {
        if (resultCode == RESULT_OK) {
            updateUIWithPosts()
        }
    }

    private fun showPopupInfo1(view: View) {
        showToast("Posts informatie")
    }

    fun showPopupInfo2(view: View) {
        // Your implementation for showPopupInfo2
    }

    fun showPopupInfo5(view: View) {
        // Your implementation
    }

    fun showPopupInfo6(view: View) {
        showToast("Bewerken Popup")

        val postId = 1

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

        val posts = mutableListOf<Post>()
        if (postsCursor.moveToFirst()) {
            val postIdColumnIndex = postsCursor.getColumnIndex("POSTID")
            val titleColumnIndex = postsCursor.getColumnIndex("title")
            val contentColumnIndex = postsCursor.getColumnIndex("content")

            do {
                val postId = if (postIdColumnIndex >= 0) postsCursor.getInt(postIdColumnIndex) else -1
                val postTitle = if (titleColumnIndex >= 0) postsCursor.getString(titleColumnIndex) else ""
                val postContent = if (contentColumnIndex >= 0) postsCursor.getString(contentColumnIndex) else ""
                Log.d("PostsActivity", "Post ID: $postId, Title: $postTitle, Content: $postContent")

                posts.add(Post(postId, postTitle, postContent))
            } while (postsCursor.moveToNext())
        }

        postsCursor.close()
        dbHelper.close()

        postAdapter = PostAdapter(posts)
        recyclerView.adapter = postAdapter
    }

    private fun createPostTextView(postTitle: String, postContent: String): TextView {
        val postTextView = TextView(this)
        postTextView.text = "Title: $postTitle\nContent: $postContent"
        postTextView.setTextColor(ContextCompat.getColor(this, R.color.black))
        postTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)

        return postTextView
    }

    fun initiatePostAction(view: View) {
        // Your logic to handle the "Post" action
        // This could involve navigating to a new post screen, showing a popup, etc.
        showToast("Post action initiated")
    }

}
