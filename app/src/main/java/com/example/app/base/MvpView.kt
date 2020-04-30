package com.example.app.base

interface MvpView <T : MvpPresenter> {

    fun setPresenter(presenter: MvpPresenter)

    fun showProgress()



}
