package com.example.goodhealthfitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.kevalpatel2106.rulerpicker.RulerValuePicker
import com.kevalpatel2106.rulerpicker.RulerValuePickerListener
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.Shader.TileMode
import android.graphics.LinearGradient
import android.graphics.Shader
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.android.synthetic.main.activity_bmi.*

class BMIActivity : AppCompatActivity() {

    lateinit var weightPicker : RulerValuePicker
    lateinit var heightPicker : RulerValuePicker
    lateinit var calculate : Button
    lateinit var bmi_btn : Button
    lateinit var ffmi_btn : Button
    lateinit var weight_et : EditText
    lateinit var height_et : EditText
    lateinit var bmi_tv : TextView





    var weight : Double = 0.0
    var height : Double = 0.0
    var result : Double = 0.0
    var method : Int = 0


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)


        init()
        listeners()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun init(){
        bmi_tv =  findViewById(R.id.bmi_tv)

        bmi_btn= findViewById(R.id.bmi_btn)
        ffmi_btn= findViewById(R.id.ffmi_btn)

        weightPicker = findViewById(R.id.weight_picker)
        heightPicker = findViewById(R.id.height_picker)

        weight_et = findViewById(R.id.weight_et)
        height_et = findViewById(R.id.height_et)
        calculate = findViewById(R.id.calculate_btn)


        weightPicker.selectValue(60)
        heightPicker.selectValue(175)
        weight_et.setText(weightPicker.currentValue.toString())
        height_et.setText(heightPicker.currentValue.toString())

        val textShader = LinearGradient(
            0f, 0f, 0f, bmi_tv.textSize,
            intArrayOf(getColor(R.color.end_color), getColor(R.color.start_color)),
            floatArrayOf(0f, 1f), TileMode.CLAMP
        )
        bmi_tv.getPaint().setShader(textShader)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    fun listeners(){

        bmi_btn.setOnClickListener(View.OnClickListener {

            method = 0
            bmi_btn.setBackgroundResource(R.drawable.selected_round)
            bmi_btn.setTextColor(getColor(R.color.white))

            ffmi_btn.setBackgroundResource(R.drawable.unselected_round)
            ffmi_btn.setTextColor(getColor(R.color.colorAccent))

        })
        ffmi_btn.setOnClickListener(View.OnClickListener {
            method = 1
            bmi_btn.setBackgroundResource(R.drawable.unselected_round)
            bmi_btn.setTextColor(getColor(R.color.colorAccent))

            ffmi_btn.setBackgroundResource(R.drawable.selected_round)
            ffmi_btn.setTextColor(getColor(R.color.white))

        })

        weightPicker.setValuePickerListener(object : RulerValuePickerListener{
            override fun onValueChange(selectedValue: Int) {
                weight = selectedValue.toDouble()
                weight_et.setText(selectedValue.toString())
                if(selectedValue ==0){
                    error_tv.visibility = View.VISIBLE
                }
                else{
                    if(heightPicker.currentValue!=0){
                        error_tv.visibility = View.INVISIBLE}
                }
            }

            override fun onIntermediateValueChange(selectedValue: Int) {
                weight_et.setText(selectedValue.toString())

            }

        })

        heightPicker.setValuePickerListener(object : RulerValuePickerListener{
            override fun onValueChange(selectedValue: Int) {
                height = selectedValue.toDouble()
                height_et.setText(selectedValue.toString())

                if(selectedValue ==0){
                    error_tv.visibility = View.VISIBLE
                }
                else{
                    if(weightPicker.currentValue!=0){
                        error_tv.visibility = View.INVISIBLE}

                }

            }

            override fun onIntermediateValueChange(selectedValue: Int) {
                height_et.setText(selectedValue.toString())
            }

        })
        calculate.setOnClickListener(View.OnClickListener {

            if(height>0 && weight>0){
                if(method==0){
                    bmiCal()
                }
                else if(method == 1){
                    ffmiCal()
                }
                showBottomSheet()
            }

        } )
    }

    fun bmiCal(){
        result = ((weight/(height*height))*10000)
    }
    fun ffmiCal(){
        result = (weight / (( height * height ) / 10000)) + 6.1 * (1.8 - ( height / 100))
    }

    fun showBottomSheet(){
        val bottomSheetFragment = BMIFragment(result, method, this)

        bottomSheetFragment.setCancelable(true)
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }


}
