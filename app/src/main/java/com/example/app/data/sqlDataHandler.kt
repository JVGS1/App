package com.example.app.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


const val DB_Name = "StudySmartDB"
const val DB_Version = 1




class sqlDatahandler(var context: Context): SQLiteOpenHelper(context, DB_Name, null, DB_Version) {

    private val ReminderDB = "Reminders"
    private val colID = "_id"
    private val colDate = "_date" //Date
    private val colTime = "_time" // Time
    private val colText = "_text" // Text



    override fun onCreate(db: SQLiteDatabase?) {
        val createScheduleTable: String = "CREATE TABLE $ReminderDB (" +
                    "$colID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$colDate VARCHAR(256)," +
                    "$colTime VARCHAR(256)," +
                    "$colText VARCHAR(256));"
        db?.execSQL(createScheduleTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


    fun insertReminder(reminders: Reminders) {
        val database = this.writableDatabase
        var content = ContentValues()
        content.put(colDate, reminders.date)
        content.put(colTime, reminders.time)
        content.put(colText, reminders.text)
        var index = database.insert(ReminderDB, null, content)

        if (index == (-1).toLong()) {
            Toast.makeText(context, "Failed to add data", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "SUCCESSFULLY CREATED", Toast.LENGTH_SHORT).show()
        }

        database.close()
    }


        fun deleteReminder(ID: String) {
        val database = this.writableDatabase
        database.delete(ReminderDB, "$colID=?", arrayOf(ID))
        database.close()
    }

    fun updateData(reminders: Reminders) {
        val database = this.writableDatabase
        var content = ContentValues()
        content.put(colDate, reminders.date)
        content.put(colTime, reminders.time)
        content.put(colText, reminders.text)
        database.update(ReminderDB, content, "$colID=?", arrayOf(reminders.ID.toString()))
        database.close()
    }

    fun readReminders(): MutableList<Reminders> {
        var list: MutableList<Reminders> = ArrayList()
        val database = this.writableDatabase
        val query: Cursor = database.rawQuery("Select * from $ReminderDB", null)
        if (query.moveToFirst()) {
            while (!query.isAfterLast) {
            val reminder = Reminders()
            reminder.ID = query.getString(query.getColumnIndex(colID)).toInt()
            reminder.date = query.getString(query.getColumnIndex(colDate))
            reminder.time = query.getString(query.getColumnIndex(colTime))
            reminder.text = query.getString(query.getColumnIndex(colText))
            list.add(reminder)
            query.moveToNext()
        }

    }
    database.close()
    query.close()
    return list
}

    fun getSize(): Int {
        return readReminders().size -1
    }

}