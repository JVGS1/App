package com.example.app.main

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.CalendarView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.data.Reminders
import com.example.app.data.sqlDatahandler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*


import kotlinx.android.synthetic.main.activity_reminders.*

class MainPresenterImpl : MainContract.reminderPresenter, AppCompatActivity() {

    lateinit var database: sqlDatahandler

    internal lateinit var mView: MainContract.view
    internal lateinit var calendar: CalendarView
    internal lateinit var cal: Calendar
    internal lateinit var context: Context


    lateinit var adapter: RecycleAdapter
    lateinit var dialog: AlertDialog
    lateinit var aManager: AlarmManager


    private val reminder = Reminders()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_reminders)
        database = sqlDatahandler(this)
        context = this

        cal = GregorianCalendar()
        val rView = findViewById<RecyclerView>(R.id.list)

        rView.layoutManager = LinearLayoutManager(this)
        adapter = RecycleAdapter(this, database.readReminders())
        rView.adapter = adapter


        aManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

//        val view = layoutInflater.inflate(R.layout.add_reminder_activity, null)
        val view = View.inflate(context, R.layout.add_reminder_activity,  null)

        floatingActionButton.setOnClickListener {
            createReminder(view)

        }
    }




     override fun createReminder(view: View) {

        if (view.parent != null) {
            (view.parent as? ViewGroup)?.removeView(view)
        }


        dialog = AlertDialog.Builder(this).create()
        dialog.setTitle("Enter Reminder Details")
        dialog.setView(view)
        dialog.show()
        dialog.setCancelable(true)


         dialog.setInverseBackgroundForced(true)


         calendarView(view)
         timeView(view)



        val date = view.findViewById<TextView>(R.id.dateText)
        val time = view.findViewById<TextView>(R.id.timeText)
        val text = view.findViewById<TextView>(R.id.reminderTitle)


        var submitReminder = view.findViewById<FloatingActionButton>(R.id.submitReminder)


        submitReminder.setOnClickListener {
            if ( date.text.isNotEmpty() && time.text.isNotEmpty() && text.text.isNotEmpty()) {

                reminder.date = date.text.toString()
                reminder.time = time.text.toString()
                reminder.text = text.text.toString()
                database.insertReminder(reminder)
                adapter.notifyItemChanged(database.getSize() - 1)
                refreshList()
                dialog.dismiss()

                notifReminder(reminder.text, cal.timeInMillis)

            }
        }
    }




     override fun notifReminder(string: String, setTime: Long) {


        val intent = Intent(context, BC::class.java)
        intent.putExtra("Reminder", string)
        val pIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        aManager.set(AlarmManager.RTC_WAKEUP, setTime, pIntent)
    }


    override fun refreshList() {
        list.adapter = RecycleAdapter(this, database.readReminders())
    }



    override fun updateReminders(reminder: Reminders) {
        val alert = AlertDialog.Builder(this).create()

        alert.setTitle("Update Reminder")
        val view = View.inflate(context, R.layout.add_reminder_activity,  null)


        if (view.parent != null) {
            (view.parent as? ViewGroup)?.removeView(view)
        }
        val date = view.findViewById<TextView>(R.id.dateText)
        val time = view.findViewById<TextView>(R.id.timeText)
        val text = view.findViewById<TextView>(R.id.reminderTitle)

        alert.setView(view)

        calendarView(view)
        timeView(view)

        val submitReminder = view.findViewById<FloatingActionButton>(R.id.submitReminder)

        submitReminder.setOnClickListener {
            if (date.text.isNotEmpty() && time.text.isNotEmpty() && text.text.isNotEmpty()) {
                reminder.date = date.text.toString()
                reminder.time = time.text.toString()
                reminder.text = text.text.toString()
                database.updateData(reminder)
                adapter.notifyItemChanged(database.getSize() - 1)
                refreshList()
                notifReminder(reminder.text, cal.timeInMillis)
                alert.dismiss()

            }
        }
        alert.show()
    }





    override fun deleteReminder(reminderID: String) {
        database.deleteReminder(reminderID)
    }


    private fun calendarView(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val date = view.findViewById<RelativeLayout>(R.id.date)
        val dateText = view.findViewById<TextView>(R.id.dateText)

        //Image to set date
        date.setOnClickListener {
            val dpd =
                DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, mYear, month, mDay ->
                    //set to textview

                    val mMonth = month + 1

                    val date = "$mYear-$mMonth-$mDay"

                    dateText.text = date

                    cal.set(mYear, month, day);


//                    cal[Calendar.YEAR] = mYear
//                    cal[Calendar.MONTH] = mMonth
//                    cal[Calendar.DAY_OF_WEEK] = mDay

                }, year, month, day)
            dpd.show()
        }
    }





    fun timeView(view: View) {
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.UK)

        val c = Calendar.getInstance()

        val time = view.findViewById<RelativeLayout>(R.id.pickTime)

        val timeText = view.findViewById<TextView>(R.id.timeText)


        time.setOnClickListener {
            val tp = TimePickerDialog(
                this, TimePickerDialog.OnTimeSetListener { viewer, hourOfDay, minute ->
                    val selectedTime = Calendar.getInstance()
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    selectedTime.set(Calendar.MINUTE, minute)

                    val time = timeFormat.format(selectedTime.time)

                    timeText.text = time



                    cal[Calendar.MINUTE] = minute
                    cal[Calendar.HOUR_OF_DAY] = hourOfDay
                    cal[Calendar.SECOND] =  0

                },
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false
            )
            tp.show()
        }
    }



}