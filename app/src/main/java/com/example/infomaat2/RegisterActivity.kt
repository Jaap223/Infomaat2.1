package com.example.infomaat2

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    private lateinit var dbHelper: MyDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        dbHelper = MyDBHelper(applicationContext)

        val btnRegister: Button = findViewById(R.id.btnRegister)

        val btnLogin: Button = findViewById(R.id.btnLogin)

        btnRegister.setOnClickListener {
            if (isInputValid()) {
                insertRegister()
                insert()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

            btnLogin.setOnClickListener {

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

    }

}
    private fun insert() {
        dbHelper.insertComment("Comment Title", "Comment Content")
        dbHelper.insertOpleiding("Opleiding Name", "Opleiding Duration")
        dbHelper.insertPost("Post Title", "Post Content")
    }


    private fun isInputValid(): Boolean {

        val editTextName: EditText = findViewById(R.id.etName)
        val editTextEmail: EditText = findViewById(R.id.etMail)
        val editTextPassword: EditText = findViewById(R.id.etPassword)

        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        if (name.isEmpty()) {
            showMessage("Voer je naam in")
            return false
        }

        if (email.isEmpty()) {
            showMessage("Voer je emil in")
            return false
        }

        if (password.isEmpty()) {
            showMessage("Voer je wachtwoord in")
            return false
        }

        return true

    }


    private fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun insertRegister() {
        val helper = MyDBHelper(applicationContext)
        val db = helper.writableDatabase
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

