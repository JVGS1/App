
package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.TextView


class reminders : AppCompatActivity() {


    lateinit var slider: SeekBar
    lateinit var value: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_reminders)

//        slider = findViewById<SeekBar>(R.id.timeSlider)
//
//        value = findViewById<TextView>(R.id.studyTime)
//
//
//        slider.max = 24
//
//        slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
//
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                value.text = progress.toString()
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                if (seekBar != null) {
//                    value.text = seekBar.progress.toString()
//                }
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                if (seekBar != null) {
//                    value.text = seekBar.progress.toString()
//                }
//            }
//
//        })


    }

}