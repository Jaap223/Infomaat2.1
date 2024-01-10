
package com.example.infomaat2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class InboxActivity : AppCompatActivity() {

    private lateinit var drawerHandler: DrawerHandler
    private lateinit var dbHelper: MyDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inbox)

        drawerHandler = DrawerHandler(this)
        drawerHandler.setupDrawer()

        dbHelper = MyDBHelper(this)

        // onUpgradeNoDrop() eenmalig uitgevoerd om kolommen toe te voegen
        // dbHelper.onUpgradeNoDrop()

        findViewById<View>(R.id.button3).setOnClickListener { btnTerugClick() }

        val sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.all
        //showToast(editor["userId"].toString())
        val userId = editor["userId"].toString()

        // inserts eenmalig uitgevoerd om testdata in de database te krijgen
        // dbHelper.insertPost("post_1", "Inhoud van test post_1", userId)
        // dbHelper.insertPost("post_2", "Inhoud van test post_2", userId)
        // dbHelper.updatePost("6", "post_3", "Inhoud van test post_3")

        val postCursor = dbHelper.getPostsByUserId(userId)
        for (post: PostDetails in postCursor) {
            //showToast(post.postId.toString() + " " + post.title + " " + post.content + " " + post.userId)
            var tekst = post.postId.toString() + " " + post.title + " " + post.content + " " + post.userId

            // TO do toon post details van post op scherm

            // inserts eenmalig uitgevoerd om testdata in de database te krijgen
            // if (post.postId == 5) {
            //     dbHelper.insertComment("comment_1_bij_post_" + post.postId.toString(), "Inhoud van test comment_1", post.postId.toString()                )
            //     dbHelper.insertComment("comment_2_bij_post_" + post.postId.toString(), "Inhoud van test comment_2", post.postId.toString()                )
            // }
            val commentCursor = dbHelper.getCommentsByPostId(post.postId.toString())
            for (comment: CommentDetails in commentCursor) {
                tekst = tekst + " - " + comment.commentId.toString() + " " + comment.title + " " + comment.content + " " + comment.postId
                //showToast(comment.commentId.toString() + " " + comment.title + " " + comment.content + " " + comment.postId)
                // TO do toon comments details van post op scherm
            }
            showToast(tekst)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun btnTerugClick() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

