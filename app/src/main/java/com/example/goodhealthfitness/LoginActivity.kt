package com.example.goodhealthfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_login.*
import com.example.goodhealthfitness.utils.toast
import com.example.goodhealthfitness.utils.login
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        btn_sign_in.setOnClickListener {
            val email = et_sign_in_email.text.toString().trim()
            val password = et_sign_in_password.text.toString().trim()

            if (email.isEmpty()) {
                et_sign_in_email.error = "Email Required"
                et_sign_in_email.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                et_sign_in_email.error = "Valid Email Required"
                et_sign_in_email.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 5) {
                et_sign_in_password.error = "5 char password required"
                et_sign_in_password.requestFocus()
                return@setOnClickListener
            }

            loginUser(email, password)
        }

        btn_toSign_up.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }


    }

    private fun loginUser(email: String, password: String) {

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    val currentUserEmail = et_sign_in_email.text.toString()
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Welcome.", Toast.LENGTH_SHORT).show()

                } else {
                    task.exception?.message?.let {
                        toast(it)
                    }
                }

            }

    }

    //override fun onStart() {
        //super.onStart()
        //mAuth.currentUser?.let {
            //startActivity((Intent(this, MainActivity::class.java)))
        //}
    //}
}