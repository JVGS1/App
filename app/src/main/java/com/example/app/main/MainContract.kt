package com.example.app.main

import com.example.app.base.MvpPresenter
import com.example.app.base.MvpView

interface MainContract {

    interface view : MvpView<MvpPresenter>

    fun updateView()


    interface Presenter : MvpPresenter


    interface reminderPresenter : MvpPresenter.reminder

}