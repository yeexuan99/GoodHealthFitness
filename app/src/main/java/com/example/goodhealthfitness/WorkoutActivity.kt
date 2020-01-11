package com.example.goodhealthfitness

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.*
import androidx.annotation.LayoutRes
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_workout.*
import kotlinx.android.synthetic.main.content_menu.*
import java.text.FieldPosition

//edit import
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkoutActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        val workouts = listOf(
            Workout(1, "Basic Squats", "https://firebasestorage.googleapis.com/v0/b/goodhealthfitness-95cf6.appspot.com/o/Basic%20Squats.jpg?alt=media&token=811ef6be-a0ce-4cd5-8843-c9ef043ac246", "The basic squat is an extremely effective lower body move that strengthens all leg muscles including glutes, quads, hamstrings and calves.", "Beginner"),
            Workout(2, "Jump Squats", "https://firebasestorage.googleapis.com/v0/b/goodhealthfitness-95cf6.appspot.com/o/Jump%20Squats.jpg?alt=media&token=17b68dc6-99b3-4298-92ff-85bd3d1869b7", "Squat Jumps are a powerful, plyometric exercise that strengthens your entire lower body and increases your heart rate for a significant calorie burn.", "Moderate"),
            Workout(3, "Plie Squats", "https://firebasestorage.googleapis.com/v0/b/goodhealthfitness-95cf6.appspot.com/o/Plie%20Squat.jpg?alt=media&token=d4219aa2-6cfe-477a-9957-b09cd0ba17b2", "A Plié Squat is an exercise that strengthens the legs, glutes and calves and increases the range of motion in your hips. ", "Beginner"),
            Workout(4, "Roundabout Squats", "https://firebasestorage.googleapis.com/v0/b/goodhealthfitness-95cf6.appspot.com/o/Roundabout%20Squats.jpg?alt=media&token=0276ddd5-19e0-488c-8916-ffb372b64122", "Perform in circular motion going clockwise & counter-clockwise each rep.", "Beginner"),
            Workout(5, "Jump Squats", "https://firebasestorage.googleapis.com/v0/b/goodhealthfitness-95cf6.appspot.com/o/Side%20Squats.jpg?alt=media&token=2e79d011-c33a-4ff3-a489-b55802039286", "Start standing with your feet together. Bend your elbows so your hands are in front of your chest, optionally holding weights. ", "Moderate"),
            Workout(6, "Squats and Leg Raises", "https://firebasestorage.googleapis.com/v0/b/goodhealthfitness-95cf6.appspot.com/o/Squats%20and%20Leg%20Raises.jpg?alt=media&token=010581d2-d8ad-409a-bc2d-c3714423721f", "Move in a controlled manner to maintain your balance as you transition between squats and leg raises", "Hardcore"),
            Workout(7, "Squats and Reverse Lifts", "https://firebasestorage.googleapis.com/v0/b/goodhealthfitness-95cf6.appspot.com/o/Squats%20and%20Reverse%20Lift.jpg?alt=media&token=6f9344e7-20ef-4e12-8407-202c1cdcdf50", "Reverse leg lifts work not only on your glutes but also on the lower back, core, legs and arms. With every lifting, you are toning all 3 gluteal muscles.", "Moderate"),
            Workout(8, "Sumo Squats and High Kicks", "https://firebasestorage.googleapis.com/v0/b/goodhealthfitness-95cf6.appspot.com/o/Sumo%20Squats%20and%20High%20Kick.jpg?alt=media&token=2dbef87e-84cd-47b2-80cc-83debbfb649a", "The Sumo Squat is a lower-body strength exercise that’s a variation of a standard squat.", "Hardcore"),
            Workout(9, "Triple Dip Squats", "https://firebasestorage.googleapis.com/v0/b/goodhealthfitness-95cf6.appspot.com/o/ic_tripledipsquats.jpg?alt=media&token=5045e50b-5455-4ce3-b5cb-9f7881df2696", "A basic bodyweight squat is a great exercise for toning up the lower body - easily one of the best.", "Hardcore")

            )

        recycleerViewWorkout.layoutManager = LinearLayoutManager(this)
        recycleerViewWorkout.adapter = WorkoutAdapter(workouts)


    }
}




