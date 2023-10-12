package com.example.infomaat2

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister: Button = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            insertRegister()

            // Start the LoginActivity when registration is done
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


    private fun isInputValid(): Boolean {
        val editTextName: EditText = findViewById(R.id.etName)
        val editTextEmail: EditText = findViewById(R.id.etMail)
        val editTextPassword: EditText = findViewById(R.id.etPassword)


        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()

        if (name.isEmpty()) {
            showMessage("Please enter your name")
            return false
        }

        if (email.isEmpty()) {
            showMessage("Please enter your email")
            return false
        }

        if (password.isEmpty()) {
            showMessage("Please enter your password")
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

