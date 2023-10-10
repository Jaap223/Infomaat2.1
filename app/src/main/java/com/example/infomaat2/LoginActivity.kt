package com.example.infomaat2

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog

class LoginActivity : AppCompatActivity() {
    @SuppressLint("Range")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btnRegister: Button = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        var btnLogin : Button = findViewById(R.id.btLogin);


        btnLogin.setOnClickListener() {
          var helper = MyDBHelper(applicationContext)
            var cr: Cursor;

            var editTextEmail: EditText = findViewById(R.id.etEmail);
            var editTextPassword: EditText = findViewById(R.id.etPassword);

            var email = editTextEmail.text.toString()
            var password = editTextPassword.text.toString()

            if (isInputValid(email, password)) {
                cr = helper.loginCheck(
                    editTextEmail.text.toString(),
                    editTextPassword.text.toString()
                );

                if(cr.moveToFirst()) {
                    val username = cr.getString(cr.getColumnIndex("NAME"))
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("username", username)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "Logingegevens zijn niet correct", Toast.LENGTH_LONG).show()
                }

                cr.close()
            }
        }
    }

    fun isInputValid(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            Toast.makeText(applicationContext, "E-mail is vereist", Toast.LENGTH_LONG).show()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(applicationContext, "Ongeldig email", Toast.LENGTH_LONG).show()
            return false
        }

        if (password.isEmpty()) {
            Toast.makeText(applicationContext, "Voer je wachtwoord in", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }


    }
