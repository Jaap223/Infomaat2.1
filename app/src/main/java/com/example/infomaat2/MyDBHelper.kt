package com.example.infomaat2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log




class MyDBHelper(context: Context) : SQLiteOpenHelper(context, "USERDB", null, 2) {

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL("CREATE TABLE USERS (USERID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, EMAIL TEXT, PWD TEXT)")
            db?.execSQL("CREATE TABLE OPLEIDINGEN (OPID INTEGER PRIMARY KEY AUTOINCREMENT, naam TEXT, duur TEXT)")
            db?.execSQL("CREATE TABLE POSTS (POSTID INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, USERID INTEGER)")
            db?.execSQL("CREATE TABLE COMMENTS (COMID INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, POSTID INTEGER)")
        } catch (e: Exception) {
            Log.e("MyDBHelper", "Error creating tables", e)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        try {
            db.execSQL("DROP TABLE IF EXISTS USERS")
            db.execSQL("DROP TABLE IF EXISTS OPLEIDINGEN")
            db.execSQL("DROP TABLE IF EXISTS POSTS")
            db.execSQL("DROP TABLE IF EXISTS COMMENTS")

            onCreate(db)
        } catch (e: Exception) {
            Log.e("MyDBHelper", "Error upgrading database", e)
        }
    }

    fun onUpgradeNoDrop() {
        val db = this.writableDatabase
        try {
            db.execSQL("ALTER TABLE POSTS ADD USERID INTEGER")
            db.execSQL("CREATE TABLE COMMENTS (COMID INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, POSTID INTEGER)")
        } catch (e: Exception) {
            Log.e("MyDBHelper", "Error upgrading database", e)
        }
    }

    fun updateProfiel(userId: String, newUserName: String, newPassword: String , newEmail: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("NAME", newUserName)
        values.put("PWD", newPassword)
        values.put("EMAIL", newEmail)
        val rowsAffected = db.update("USERS", values, "USERID = ?", arrayOf(userId))
        db.close()
        Log.d("MyDBHelper", "Rows affected: $rowsAffected")
    }

    fun loginCheck (mail: String, password: String): Cursor {
        val db = this.readableDatabase
        val selectionArgs = arrayOf(mail, password)
        return db.rawQuery(
            "SELECT * FROM USERS WHERE EMAIL = '" + mail + "' AND PWD = '" + password + "'",
            null
        )
    }

    fun getAllUsers(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM USERS", null)
    }
    fun getOpleidingen(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM OPLEIDINGEN", null)
    }

    fun getPosts(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM POSTS", null)
    }
    fun deleteUser(userId: String?) {
        val db = this.writableDatabase
        db.delete("USERS", "USERID = ?", arrayOf(userId.toString()))
        db.close()
    }

    fun updateOpleiding(opId: String, newNaam: String, newDuur: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("naam", newNaam)
        values.put("duur", newDuur)
        val rowsAffected = db.update("OPLEIDINGEN", values, "OPID = ?", arrayOf(opId))
        db.close()
        Log.d("MyDBHelper", "Rows affected in OPLEIDINGEN: $rowsAffected")
    }
    fun deleteOpleiding(opId: String?) {
        val db = this.writableDatabase
        db.delete("OPLEIDINGEN", "OPID = ?", arrayOf(opId.toString()))
        db.close()
    }
    fun insertComment(title: String, content: String, postID: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("title", title)
        values.put("content", content)
        values.put("POSTID", postID)
        db.insert("COMMENTS", null, values)
        db.close()
    }

    fun insertOpleiding(naam: String, duur: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("naam", naam)
        values.put("duur", duur)
        db.insert("OPLEIDINGEN", null, values)
        db.close()
    }

    fun insertPost(title: String, content: String, userId: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("title", title)
        values.put("content", content)
        values.put("USERID", userId)
        db.insert("POSTS", null, values)
        db.close()
    }

    fun updatePost(postId: String, newTitle: String, newContent: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("title", newTitle)
        values.put("content", newContent)
        val rowsAffected = db.update("POSTS", values, "POSTID = " + postId,
            null)
        db.close()
        Log.d("MyDBHelper", "Rows affected in POSTS: $rowsAffected")
    }

    fun getPostById(postId: String): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM POSTS WHERE POSTID = ?", arrayOf(postId.toString()))
    }

    fun deletePost(postId: Int) {
        val db = writableDatabase
        db.delete("POSTS", "POSTID=?", arrayOf(postId.toString()))
        db.close()
    }

    @SuppressLint("Range")
    fun getPostsList(): List<Post> {
        val postsList = mutableListOf<Post>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM POSTS", null)

        while (cursor.moveToNext()) {
            val postId = cursor.getInt(cursor.getColumnIndex("POSTID"))
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val post = Post(postId, title)
            postsList.add(post)
        }

        cursor.close()
        db.close()
        return postsList
    }

    @SuppressLint("Range")
    fun getPostsByUserId(userId: String): List<PostDetails> {
        val postsList = mutableListOf<PostDetails>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM POSTS WHERE USERID = " + userId,
            null)

        while (cursor.moveToNext()) {
            val postId = cursor.getInt(cursor.getColumnIndex("POSTID"))
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val content = cursor.getString(cursor.getColumnIndex("content"))
            val postDetail = PostDetails(postId, title, content, userId.toInt())
            postsList.add(postDetail)
        }

        cursor.close()
        db.close()
        return postsList
    }

    @SuppressLint("Range")
    fun getCommentsByPostId(postId: String): List<CommentDetails> {
        val commentsList = mutableListOf<CommentDetails>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM COMMENTS WHERE POSTID = " + postId,
            null)

        while (cursor.moveToNext()) {
            val commentId = cursor.getInt(cursor.getColumnIndex("COMID"))
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val content = cursor.getString(cursor.getColumnIndex("content"))
            val commentDetail = CommentDetails(commentId, title, content, postId.toInt())
            commentsList.add(commentDetail)
        }

        cursor.close()
        db.close()
        return commentsList
    }
}
