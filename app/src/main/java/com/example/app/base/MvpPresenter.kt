package com.example.app.base


import com.example.app.data.Reminders

interface MvpPresenter {

    interface reminder{
        fun updateReminders(reminders: Reminders)

        fun refresh()

        fun notification(reminders: Reminders)
    }


}