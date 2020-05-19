package com.example.app.main

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import com.example.app.base.MvpPresenter
import com.example.app.base.MvpView
import com.example.app.data.Reminders

class MainStatsImpl : AppCompatActivity(), MvpPresenter.Stats {


    internal lateinit var mPresenter: MainContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_stats)


        }

    override fun setGoals() {
        TODO("Not yet implemented")
    }

    override fun getGoals() {
        TODO("Not yet implemented")
    }

    override fun chartView() {
        TODO("Not yet implemented")
    }

    override fun currentProgression() {
        TODO("Not yet implemented")
    }


}




