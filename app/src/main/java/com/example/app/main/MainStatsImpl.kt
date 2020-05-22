package com.example.app.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import com.example.app.base.MvpPresenter
import com.example.app.data.sqlDatahandlerAlarm
import com.github.mikephil.charting.charts.BarChart
import kotlinx.android.synthetic.main.activity_stats.*


class MainStatsImpl : AppCompatActivity(), MvpPresenter.Stats {




    internal lateinit var mPresenter: MainContract.Presenter

    lateinit var databaseAlarm: sqlDatahandlerAlarm

    lateinit var barchart : BarChart
    lateinit var seekBar : SeekBar
    lateinit var value : TextView

    lateinit var TST : TextView

    lateinit var text : String
    private var spText : String = "TEXT"


//    lateinit var barEntryArrayList: ArrayList<BarEntry>
//
//    var monthstudyHours: ArrayList<studyHours> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_stats)


        barchart = findViewById<BarChart>(R.id.barGraph)
        seekBar = findViewById<SeekBar>(R.id.appCompatSeekBar)
        value = findViewById<TextView>(R.id.TST)
        TST = findViewById<TextView>(R.id.TST)

        seekBar.max = 12



        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                value.text = "Selecting : $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    value.text = seekBar.progress.toString()
                }
            }

        } )

        getGoals()
    }



    override fun setGoals(view: View) {

        TST.text = sliderText.text.toString()

            val sharedPreferences =
            getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString(spText, TST.text.toString())
        editor.apply()
    }

    override fun getGoals() {
        val sharedPreferences =
            getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
            text = sharedPreferences.getString(spText, "").toString()
            TST.text = text

    }

    override fun chartView() {


    }

    override fun currentProgression() {
        TODO("Not yet implemented")
    }



    class studyHours(s: String, hours: Int) {


    }




}




