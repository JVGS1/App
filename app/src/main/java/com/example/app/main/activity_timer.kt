package com.example.app.main

import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import java.util.*


class activity_timer : AppCompatActivity() {

    var StartTimeInMillis : Long = 0
    var TimeLeftInMillis : Long = 0
    var endTime : Long = 0
    var timerRunning : Boolean = false


    lateinit var textCountDown: TextView
    lateinit var timerInput: EditText

    lateinit var  setTimer: Button
    lateinit var  start_pause_Timer: ImageView
    lateinit var  buttonReset: ImageView


    lateinit var  clockDial: ImageView


    lateinit var countDownTimer: CountDownTimer
    lateinit var clockRotateAnimation: Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_alarm)


        //
        timerInput = findViewById(R.id.reminderTimer)

        start_pause_Timer = findViewById(R.id.start_pause_Timer)

        setTimer = findViewById(R.id.setTimer)

        textCountDown= findViewById(R.id.timerDisplay)

        buttonReset = findViewById(R.id.reset)

        clockDial = findViewById(R.id.clock_dial)

        //Loading animation
        clockRotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate)

        setTimer.setOnClickListener(View.OnClickListener {
            val input: String = timerInput.getText().toString()
            if (input.length == 0) {
                Toast.makeText(this, "Field is empty", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }
            val inputTime = input.toLong() * 60000
            if (inputTime == 0L) {
                Toast.makeText(
                    this,
                    "Enter number above 0",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            setTimer(inputTime)
            timerInput.setText("")
        })


        start_pause_Timer.setOnClickListener{
            if (timerRunning) {
                pauseTimer()
            } else {
                timerStart()
                clockDial.startAnimation(clockRotateAnimation)
            }
        }

        buttonReset.setOnClickListener{ resetTimer() }
    }





            fun setTimer(timer: Long) {
                StartTimeInMillis = timer
                resetTimer()
            }


            fun timerStart() {
                endTime = System.currentTimeMillis() + TimeLeftInMillis

                countDownTimer = object : CountDownTimer(TimeLeftInMillis, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        TimeLeftInMillis = millisUntilFinished
                        textCounter()
                    }
                    override fun onFinish() {
                        timerRunning = false

                    }
                }.start()
                timerRunning = true
            }


            fun resetTimer() {
                TimeLeftInMillis = StartTimeInMillis
                textCounter()

            }

            fun pauseTimer() {
                countDownTimer.cancel()
                timerRunning = false
            }




            fun textCounter() {


                val hours = (TimeLeftInMillis / 1000).toInt() / 3600
                val minutes = (TimeLeftInMillis / 1000 % 3600).toInt() / 60
                val seconds = (TimeLeftInMillis / 1000).toInt() % 60
                val formatTime: String
                if (hours > 0) {
                    formatTime = java.lang.String.format(
                        Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, seconds
                    )
                } else {
                    formatTime = java.lang.String.format(
                        Locale.getDefault(),
                        "%02d:%02d", minutes, seconds
                    )
                }
                textCountDown.setText(formatTime)

            }



            override fun onStart() {
                super.onStart()

                val prefs : SharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)


                StartTimeInMillis = prefs.getLong("startTimeInMillis", 600000)
                TimeLeftInMillis = prefs.getLong("millisLeft", StartTimeInMillis)
                timerRunning = prefs.getBoolean("timerRunning", false)

                textCounter()

                if (timerRunning) {
                    endTime = prefs.getLong("endTime", 0)
                    TimeLeftInMillis = endTime - System.currentTimeMillis()
                    if (TimeLeftInMillis < 0) {
                        TimeLeftInMillis = 0
                        timerRunning = false
                        textCounter()
                    } else {
                        timerStart()
                    }
                }

            }

            override fun onStop() {
                super.onStop()

               val prefs : SharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)

                val editor = prefs.edit()

                editor.putLong("startTimeInMillis", StartTimeInMillis)
                editor.putLong("TimeLeftInmillis", TimeLeftInMillis)
                editor.putBoolean("timerRunning", timerRunning)
                editor.putLong("endTime", endTime)
                editor.apply()

                countDownTimer.cancel()
            }


        }