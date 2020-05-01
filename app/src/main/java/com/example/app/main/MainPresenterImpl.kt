package com.example.app.main

import androidx.appcompat.app.AppCompatActivity
import com.example.app.data.sqlDatahandler

class MainPresenterImpl : MainContract.Presenter, AppCompatActivity() {

    lateinit var database: sqlDatahandler


    internal lateinit var mView : MainContract.view
    override fun updateReminders() {
        TODO("Not yet implemented")
    }

    override fun notification() {
        TODO("Not yet implemented")
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }


}