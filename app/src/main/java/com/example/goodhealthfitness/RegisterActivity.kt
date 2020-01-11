package com.example.goodhealthfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.goodhealthfitness.utils.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import java.io.FileReader


class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)




        mAuth = FirebaseAuth.getInstance()


        btn_sign_up.setOnClickListener {
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()

            if(email.isEmpty()){
                et_email.error = "Email Required"
                et_email.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                et_email.error = "Valid Email Required"
                et_email.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                et_email.error = "Email Required"
                et_email.requestFocus()
                return@setOnClickListener
            }
            registerUser(email, password)

        }
    }
    private fun registerUser(email:String, password:String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    saveUser()
                    Toast.makeText(this, "Proceed login", Toast.LENGTH_SHORT).show()
                }else{
                    task.exception?.message?.let{
                        toast(it)
                    }

                }

            }
    }

    private fun saveUser() {

        val ref = FirebaseDatabase.getInstance().getReference("Users")

        val userId = ref.push().key

        val user = User(userId.toString(), et_name.text.toString(), et_height.text.toString(), et_password.text.toString(), et_email.text.toString())
        ref.child(userId.toString()).setValue(user).addOnCompleteListener{
            Toast.makeText(this, "DB", Toast.LENGTH_SHORT).show()
        }

    }
}
