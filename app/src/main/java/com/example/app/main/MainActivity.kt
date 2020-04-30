package com.example.app.main

import androidx.appcompat.app.AppCompatActivity
import com.example.app.base.MvpPresenter

class MainActivity : AppCompatActivity(), MainContract.view{


    internal lateinit var mPresenter: MainContract.Presenter


    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun setPresenter(presenter: MvpPresenter) {
        TODO("Not yet implemented")
    }



}

