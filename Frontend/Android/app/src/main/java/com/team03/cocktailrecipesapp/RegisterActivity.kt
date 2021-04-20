package com.team03.cocktailrecipesapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


    }

    fun login(): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return true
    }

    fun validateInput(): Boolean {
        val etUsername: EditText = findViewById(R.id.txt_username);
        val etPassword: EditText = findViewById(R.id.txt_password);
        val etPasswordRepeated: EditText = findViewById(R.id.txt_password_repeat);
        var ret_val: Boolean
        ret_val = true;
        if (etUsername.text.toString() == "") {
            etUsername.highlightColor = Color.RED;
            ret_val = false
        }
        if (etPassword.text.toString() == "") {
            etPassword.highlightColor = Color.RED;
            ret_val =  false
        }
        if (etPasswordRepeated.text.toString() == "") {
            etPasswordRepeated.highlightColor = Color.RED;
            ret_val = false
        }
        if (etPasswordRepeated.text.toString() != etPassword.text.toString()) {
            etPasswordRepeated.error = ""
            ret_val = false;
        }

        if (etPassword.text.toString() == "") {
            etPassword.error = "Please Enter Password"
            ret_val = false
        }


        return ret_val
    }

    fun registerOnClick(view: View){
        val btnRegister: Button = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener {
            if (validateInput()) {
                login();
            }
        }
    }


}