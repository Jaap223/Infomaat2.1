package com.example.infomaat2
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDBHelper(context: Context) : SQLiteOpenHelper(context, "USERDB", null, 2) {

    override fun onCreate(db: SQLiteDatabase?) {
        // Create the tables
        db?.execSQL("CREATE TABLE USERS (USERID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, EMAIL TEXT, PWD TEXT)")
        db?.execSQL("CREATE TABLE OPLEIDINGEN (OPID INTEGER PRIMARY KEY AUTOINCREMENT, naam TEXT, duur TEXT)")
        db?.execSQL("CREATE TABLE POSTS (POSTID INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS USERS")
        db.execSQL("DROP TABLE IF EXISTS OPLEIDINGEN")
        db.execSQL("DROP TABLE IF EXISTS POSTS")

        onCreate(db)
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
        return db.rawQuery("SELECT * FROM OPLEIDINGEN ", null)
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

    fun insertPost(title: String, content: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("title", title)
        values.put("content", content)
        db.insert("POSTS", null, values)
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




}
