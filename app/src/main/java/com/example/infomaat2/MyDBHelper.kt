package com.example.infomaat2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper (context: Context) : SQLiteOpenHelper(context, "USERDB",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE USERS (USERID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, EMAIL TEXT, PWD TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

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

    fun deleteUser(userId: String?) {
        val db = this.writableDatabase
        db.delete("USERS", "USERID = ?", arrayOf(userId.toString()))
        db.close()
    }

    fun updateUser(userId: String?, newUserName: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("NAME", newUserName)
        db.update("USERS", values, "USERID = ?", arrayOf(userId.toString()))
        db.close()
    }

    constructor() : this(MyApplication.getContext())
}
