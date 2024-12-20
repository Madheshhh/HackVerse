package com.example.hackverse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class SignUpActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val uname = findViewById<TextInputEditText>(R.id.UID)
        val eID = findViewById<TextInputEditText>(R.id.EID)
        val pass = findViewById<TextInputEditText>(R.id.PwD)
        val signUpButton = findViewById<Button>(R.id.signupBtn)
        val text = findViewById<TextView>(R.id.textView2)
        firebaseAuth = FirebaseAuth.getInstance()
        text.setOnClickListener(){
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        signUpButton.setOnClickListener {
            val userName = uname.text.toString()
            val email = eID.text.toString()
            val password = pass.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && userName.isNotEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener() {
                if (it.isSuccessful){
                    Toast.makeText(this, "User registered!", Toast.LENGTH_SHORT).show()
                    intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            }else{
                    Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }



        }
    }
}
