package com.example.goodhealthfitness

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

//edit import
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_layout.view.*
import kotlinx.android.synthetic.main.list_layout.view.*


private val user = FirebaseAuth.getInstance().currentUser
private lateinit var listView: ListView


//edit
class WorkoutAdapter(val workouts : List<Workout>) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        return WorkoutViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_layout, parent, false)
        )
    }

    override fun getItemCount() = workouts.size

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val workout = workouts[position]
        holder.view.textViewTitle.text = workout.name
        holder.view.textViewRank.text = workout.rank
        holder.view.textViewDescription.text = workout.description



        Glide.with(holder.view.context)
            .load(workout.image)
            .into(holder.view.imageView)
    }


    class WorkoutViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}