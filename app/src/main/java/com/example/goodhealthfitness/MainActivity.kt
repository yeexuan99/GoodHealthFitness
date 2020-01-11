package com.example.goodhealthfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.goodhealthfitness.utils.logout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.content_menu.view.*

class MainActivity : AppCompatActivity() {

    private var doubleBackPressed = false
    private var toast : Toast ?= null

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private val auth = FirebaseAuth.getInstance()
    private lateinit var headerName:TextView
   private val user = FirebaseAuth.getInstance().currentUser



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        toolbar = findViewById(R.id.toolBar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.navigationView)
        headerName = findViewById(R.id.currentUserName)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)





        toggle.syncState()

        if (auth != null){
            navView.menu.findItem(R.id.nav_logout)?.isVisible = true
            navView.menu.findItem(R.id.nav_login)?.isVisible = false
           // headerName.currentUserName.text = user!!.displayName

        }




        //getSelectedItem(navView)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.nav_home -> { getNavTag("nav_home")}
                R.id.nav_profile -> { getNavTag("nav_profile")}
                R.id.nav_bmi -> { getNavTag("nav_bmi")}
                R.id.nav_about -> { getNavTag("nav_about")}
                R.id.nav_logout -> { getNavTag("nav_logout")}
                R.id.nav_setting -> { getNavTag("nav_setting")}
                R.id.nav_workout -> { getNavTag("nav_workout")}
                R.id.nav_login -> { getNavTag("nav_login")}
                R.id.nav_timer -> { getNavTag("nav_timer")}

            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun FragmentManager.getCurrentNavigationFragment(): Fragment? =
        primaryNavigationFragment?.childFragmentManager?.fragments?.first()

    private fun getNavTag(tag:String) {
        var navController = findNavController(R.id.nav_host_fragment)
        var currentFragment =
            supportFragmentManager.getCurrentNavigationFragment().toString().substringBefore('{')
        Toast.makeText(applicationContext, currentFragment, Toast.LENGTH_SHORT).show()

        if (tag == "nav_home") {                                     //home
            when (currentFragment) {
                "HomeFragment" -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                "ProfileFragment" -> {//profile to home
                    navController.navigate(R.id.homeFragment)
                    //startActivity(Intent(this, HomeFragment::class.java))
                }
                "BMIActivity" -> {//bmi to home
                    navController.navigate(R.id.homeFragment)
                }
                "AboutFragment" -> {//About Us to home
                    navController.navigate(R.id.homeFragment)
                }
                "SettingsFragment" -> {//setting to home
                     navController.navigate(R.id.homeFragment)
                }
                "WorkoutFragment" -> {//Product Detail to home
                     navController.navigate(R.id.homeFragment)
                }
                "activity_login" -> {//Login to home
                    navController.navigate(R.id.homeFragment)
                }

            }
        }else if (tag == "nav_timer") {                      //timer
            when (currentFragment) {
                "TimerFragment" -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                "HomeFragment" -> {
                    startActivity(Intent(this, TimerActivity::class.java))
                }
                "BMIActivity" -> {//profile ot bmi
                    startActivity(Intent(this, TimerActivity::class.java))
                }
                "AboutFragment" -> {//profile to about
                    startActivity(Intent(this, TimerActivity::class.java))
                }
                "SettingsFragment" -> {//profile to seeting
                    startActivity(Intent(this, TimerActivity::class.java))
                }
                "WorkoutFragment" -> {//profile to workout
                    startActivity(Intent(this, TimerActivity::class.java))
                }
                "LoginFragment" -> {//profile to login
                    startActivity(Intent(this, TimerActivity::class.java))
                }
                "ProfileFragment" -> {//profile to login
                    startActivity(Intent(this, TimerActivity::class.java))
                }

            }
        } else if (tag == "nav_profile") {                      //profile
            when (currentFragment) {
                "ProfileFragment" -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                "HomeFragment" -> {
                    navController.navigate(R.id.profileActivity)
                }
                "BMIActivity" -> {//profile ot bmi
                    navController.navigate(R.id.profileActivity)
                }
                "AboutFragment" -> {//profile to about
                    navController.navigate(R.id.profileActivity)
                }
                "SettingsFragment" -> {//profile to seeting
                    navController.navigate(R.id.profileActivity)
                }
                "WorkoutFragment" -> {//profile to workout
                    navController.navigate(R.id.profileActivity)
                }
                "LoginFragment" -> {//profile to login
                    navController.navigate(R.id.profileActivity)
                }

            }
        } else if (tag == "nav_bmi") {                              //my bmi
            when (currentFragment) {
                "BMIActivity" -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                "HomeFragment" -> {
                    navController.navigate(R.id.BMIActivity)
                }
                "ProfileFragment" -> {//bmi to profile
                    startActivity(Intent(this, BMIActivity::class.java))
                }
                "AboutFragment" -> {//bmi to about
                    navController.navigate(R.id.BMIActivity)
                }
                "SettingsFragment" -> {//bmi to setting
                    navController.navigate(R.id.BMIActivity)
                }
                "WorkoutFragment" -> {//bmi to workout
                    navController.navigate(R.id.BMIActivity)
                }
                "LoginFragment" -> {//bmi to login
                    startActivity(Intent(this, BMIActivity::class.java))
                }

            }
        } else if (tag == "nav_about") {                             //about
            when (currentFragment) {
                "AboutFragment" -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                "HomeFragment" -> {
                    navController.navigate(R.id.aboutFragment)
                }
                "ProfileFragment" -> {//about to profile
                    navController.navigate(R.id.aboutFragment)
                }
                "BMIActivity" -> {//about to bmi
                    navController.navigate(R.id.aboutFragment)
                }
                "SettingsFragment" -> {//about to setting
                    navController.navigate(R.id.aboutFragment)
                }
                "WorkoutFragment" -> {//about to workout
                     navController.navigate(R.id.aboutFragment)
                }
                "LoginFragment" -> {//about ot login
                    navController.navigate(R.id.aboutFragment)
                }

            }
        } else if ((tag == "nav_logout")) {                         //logout
            if (auth != null) {
                auth.signOut()
                logout()
            } else {
                Toast.makeText(this, "logout error", Toast.LENGTH_SHORT).show()
            }

        } else if (tag == "nav_setting") {                             //setting
            when (currentFragment) {
                "SettingsFragment" -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                "HomeFragment" -> {
                    navController.navigate(R.id.settingsFragment)
                }
                "ProfileFragment" -> {//setting to profile
                    navController.navigate(R.id.settingsFragment)
                }
                "BMIActivity" -> {//setting to bmi
                    navController.navigate(R.id.settingsFragment)
                }
                "AboutFragment" -> {//setting to about
                    navController.navigate(R.id.settingsFragment)
                }
                "WorkoutFragment" -> {//setting to workout
                    navController.navigate(R.id.settingsFragment)
                }
                "LoginFragment" -> {//seting to login
                    navController.navigate(R.id.settingsFragment)
                }

            }
        }
        else if (tag == "nav_login") {                             //login
            when (currentFragment) {
                "LoginFragment" -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                "HomeFragment" -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                "ProfileFragment" -> {//setting to profile
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                "BMIActivity" -> {//setting to bmi
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                "AboutFragment" -> {//setting to about
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                "WorkoutFragment" -> {//setting to workout
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                "SettingsFragment" -> {//seting to login
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                "activity_register" -> {//setting to register
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }else if (tag == "nav_workout") {                             //workout
            when (currentFragment) {
                "WorkoutFragment" -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                "HomeFragment" -> {
                    startActivity(Intent(this, WorkoutActivity::class.java))
                }
                "ProfileFragment" -> {//workout to profule
                    startActivity(Intent(this, WorkoutActivity::class.java))
                }
                "BMIActivity" -> {//workout to bmi
                    startActivity(Intent(this, WorkoutActivity::class.java))
                }
                "SettingsFragment" -> {//workout to setting
                    startActivity(Intent(this, WorkoutActivity::class.java))
                }
                "AboutFragment" -> {//workout to about
                    startActivity(Intent(this, WorkoutActivity::class.java))
                }
                "LoginFragment" -> {//Login to home
                    startActivity(Intent(this, WorkoutActivity::class.java))
                }
                "activity_register" -> {//Register to home
                    startActivity(Intent(this, WorkoutActivity::class.java))
                }
            }
        }


    }


}
