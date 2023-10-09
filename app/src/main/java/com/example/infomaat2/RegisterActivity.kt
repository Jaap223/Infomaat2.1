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

        var btnRegister: Button = findViewById(R.id.btRegister);

        btnRegister.setOnClickListener {
            insertRegister();
        }


        fun insertRegister() {
            var helper = MyDBHelper(applicationContext)
            var db = helper.readableDatabase
            var rs = db.rawQuery("SELECT * FROM USERS", null)
            var cv = ContentValues()

            var editTextName: EditText = findViewById(R.id.etName);
            var editTextEmail: EditText = findViewById(R.id.etMail);
            var editTextPassword: EditText = findViewById(R.id.etPassword);

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
}