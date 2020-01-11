package com.example.goodhealthfitness

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat.recreate
import androidx.core.app.ActivityCompat.startActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.firebase.ui.database.paging.FirebaseDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

private val user = FirebaseAuth.getInstance().currentUser
private lateinit var listView: ListView

class ProfileAdapter(val mCtx: Context, val layoutResId: Int, val profileList: List<Profiles>) :
    ArrayAdapter<Profiles>(mCtx, layoutResId, profileList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)
        val profileName = view.findViewById<TextView>(R.id.tw_profile_name)
        val profileHeight = view.findViewById<TextView>(R.id.tw_profile_height)
        val profileEmail = view.findViewById<TextView>(R.id.tw_profile_email)
        val btn_update = view.findViewById<Button>(R.id.btn_update)

        val profile = profileList[position]

        btn_update.setOnClickListener {
            showUpdateDialog(profile)

        }


        if (profile.email != user!!.email) {

            view.visibility = GONE
            view.systemUiVisibility = GONE


        } else
            profileName.text = "Current User Name:    " + profile.name
        profileHeight.text = "Current User Height:    " + profile.height
        profileEmail.text = "Current User Email:    " + profile.email

        return view
    }

    fun showUpdateDialog(profile: Profiles) {
        val builder = AlertDialog.Builder(mCtx)
        builder.setTitle("Update Profile")

        val inflater = LayoutInflater.from(mCtx)
        val view = inflater.inflate(R.layout.profile_update, null)
        val et_edit_name = view.findViewById<EditText>(R.id.et_profile_name)
        val et_edit_height = view.findViewById<EditText>(R.id.et_profile_height)


        builder.setView(view)
        et_edit_name.setText(profile.name)
        et_edit_height.setText(profile.height)

        builder.setPositiveButton("Update") { p0, p1 ->
            val dbProfile = FirebaseDatabase.getInstance().getReference("Users")
            val name = et_edit_name.text.toString().trim()
            val height = et_edit_height.text.toString().trim()

            if (name.isEmpty()) {
                et_edit_name.error = "Please enter a name"
                et_edit_name.requestFocus()
                return@setPositiveButton
            }
            if (height.isEmpty()) {
                et_edit_height.error = "Please enter a Height"
                et_edit_height.requestFocus()
                return@setPositiveButton
            }

            val profile = Profiles(profile.id, profile.email, height, name, profile.password)
            dbProfile.child(profile.id).setValue(profile)


            Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()
            return@setPositiveButton
        }

        builder.setNegativeButton("No") { p0, p1 ->


        }

        val alert = builder.create()
        alert.show()


    }


}