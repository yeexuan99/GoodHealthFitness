package com.example.goodhealthfitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.profiles.*

class ProfileActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var profileList: MutableList<Profiles>
    lateinit var listView: ListView
    private val auth = FirebaseAuth.getInstance()
    private val user = FirebaseAuth.getInstance().currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        ref = FirebaseDatabase.getInstance().getReference("Users")
        profileList = mutableListOf()
        listView = findViewById(R.id.profileListView)



        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    for (h in p0.children) {
                        val profiles = h.getValue(Profiles::class.java)
                        profileList.add(profiles!!)


                    }
                    val adapter =
                        ProfileAdapter(this@ProfileActivity, R.layout.profiles, profileList)
                    //  Toast.makeText(this@ProfileActivity, user!!.email.toString(), Toast.LENGTH_SHORT).show()


                    listView.adapter = adapter

                }


            }
        })
    }
}
