package com.example.app

import android.content.Intent
//import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //Variables for animation



    private lateinit var image:ImageView

    private lateinit var logo:TextView
    private lateinit var appName:TextView

    private lateinit var topAnim:Animation
    private lateinit var bottomAnim:Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Hiding status bar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)


        //Animation
         topAnim = AnimationUtils.loadAnimation(this, R.anim.top_splash_animation)
         bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_splash_animation)

        //hooks
        appName = findViewById(R.id.SplashText)


        appName.setAnimation(topAnim)

        Handler().postDelayed({
            val intent = Intent(this, reminders::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}

