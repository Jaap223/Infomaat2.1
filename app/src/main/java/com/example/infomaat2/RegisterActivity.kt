package com.example.infomaat2

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister: Button = findViewById(R.id.btRegister)

        btnRegister.setOnClickListener {
            insertRegister()
        }
    }

    // Define the insertRegister function at the class level

    private fun insertRegister() {
        val helper = MyDBHelper(applicationContext)
        val db = helper.readableDatabase
        val cv = ContentValues()

        val editTextName: EditText = findViewById(R.id.etName)
        val editTextEmail: EditText = findViewById(R.id.etMail)
        val editTextPassword: EditText = findViewById(R.id.etPassword)

        cv.put("NAME", editTextName.text.toString())
        cv.put("EMAIL", editTextEmail.text.toString())
        cv.put("PWD", editTextPassword.text.toString())

        db.insert("USERS", null, cv)

        editTextName.setText("")
        editTextEmail.setText("")
        editTextPassword.setText("")

        editTextName.requestFocus()
    }
}
