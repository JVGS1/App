package com.example.app.base


import android.view.View
import com.example.app.data.Reminders

interface MvpPresenter {

    interface reminder{
        fun updateReminders(reminders: Reminders)

        fun refreshList()

        fun createReminder(view: View)

        fun notifReminder(string: String, setTime: Long)

        fun deleteReminder(reminderID: String)
    }

    interface Stats {
            fun setGoals()

            fun getGoals()

            fun chartView()

            fun currentProgression()
        }
    }


