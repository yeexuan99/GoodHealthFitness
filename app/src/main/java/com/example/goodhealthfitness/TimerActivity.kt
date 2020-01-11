package com.example.goodhealthfitness

import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import com.example.goodhealthfitness.utils.PrefUtil
import kotlinx.android.synthetic.main.activity_timer.*
import kotlinx.android.synthetic.main.content_menu.*
import kotlinx.android.synthetic.main.content_timer.*
import kotlinx.android.synthetic.main.fragment_home.*

class TimerActivity : AppCompatActivity() {

    enum class TimerState{
        Stopped, Paused, Running
    }

    private lateinit var timer:CountDownTimer
    private var timerLengthSeconds:Long = 0L
    private var timerState = TimerState.Stopped

    private var secondsRemaining = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        setSupportActionBar(toolBar)
        supportActionBar?.setIcon(R.drawable.ic_timer)
        supportActionBar?.title= "Timer"

        fab_start.setOnClickListener{ y ->
            startTimer()
            timerState = TimerState.Running
            updateButtons()
        }

        fab_pause.setOnClickListener{ y ->
            timer.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }
        fab_stop.setOnClickListener{ y ->
            timer.cancel()
            onTimerFinished()
        }

    }

    override fun onResume(){

        super.onResume()
        iniTimer()
    }

    override fun onPause(){
        super.onPause()

        if (timerState == TimerState.Running){
            timer.cancel()
        }
        else if(timerState == TimerState.Paused){

        }

        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds,this)
        PrefUtil.setSecondsRemaining(secondsRemaining, this)
        PrefUtil.setTimerState(timerState, this)

    }

    private fun iniTimer(){
        timerState = PrefUtil.getTimerState(this)

        if(timerState == TimerState.Stopped)
            setNewTimerLength()
        else
            setPreviousTimerLength()

        secondsRemaining = if(timerState == TimerState.Running || timerState == TimerState.Paused)
            PrefUtil.getSecondsRemaining(this)
        else
            timerLengthSeconds

        if (timerState == TimerState.Running)
            startTimer()

        updateButtons()
        updateCountdownUI()
    }

    private fun onTimerFinished(){
        timerState = TimerState.Stopped

        setNewTimerLength()

        progress_countDown.progress = 0

        PrefUtil.setSecondsRemaining(timerLengthSeconds, this)
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateCountdownUI()
    }

    private fun startTimer(){
        timerState = TimerState.Running
        timer = object : CountDownTimer(secondsRemaining*1000, 1000){

            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long){
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }
        }.start()
    }

    private fun setNewTimerLength(){
        val lengthInMinutes = PrefUtil.getTimerLength(this)
        timerLengthSeconds = (lengthInMinutes * 60L)
        progress_countDown.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength(){
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(this)
        progress_countDown.max = timerLengthSeconds.toInt()
    }

    private fun updateCountdownUI(){
        val minuteUntilFinished = secondsRemaining /60
        val secondsInMinuteUntilFinished = secondsRemaining - minuteUntilFinished *60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        textView_countdown.text = "$minuteUntilFinished:${if (secondsStr.length == 2)secondsStr else "0" + secondsStr}"
        progress_countDown.progress = (timerLengthSeconds - secondsRemaining).toInt()
    }

    private fun updateButtons(){
        when(timerState){
            TimerState.Running ->{
                fab_start.isEnabled = false
                fab_pause.isEnabled = true
                fab_stop.isEnabled = true
            }
            TimerState.Stopped ->{
                fab_start.isEnabled = true
                fab_pause.isEnabled = false
                fab_stop.isEnabled = false
            }
            TimerState.Paused ->{
                fab_start.isEnabled = true
                fab_pause.isEnabled = false
                fab_stop.isEnabled = true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_timer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}
