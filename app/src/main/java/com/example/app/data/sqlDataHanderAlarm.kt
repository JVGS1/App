package com.example.app.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast






class sqlDatahandlerAlarm(var context: Context): SQLiteOpenHelper(context, DB_Name, null, DB_Version) {


    private val alarmDB = "alarmDB"
    private val colID = "_id"
    private val colDate = "_date" //Date
    private val colTime = "_time" // Time



    override fun onCreate(db: SQLiteDatabase?) {
        val createScheduleTable: String = "CREATE TABLE $alarmDB (" +
                "$colID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$colDate VARCHAR(256)," +
                "$colTime VARCHAR(256));"
        db?.execSQL(createScheduleTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


    fun insertAlarm(alarm: alarm) {
        val database = this.writableDatabase
        var content = ContentValues()
        content.put(colDate, alarm.dateTime)
        content.put(colTime, alarm.studyTime)
        var index = database.insert(alarmDB, null, content)

        if (index == (-1).toLong()) {
            Log.d("Failed", "Unable to add")
        } else {
            Log.d("Success", "Successfully added")
        }


        database.close()
    }


    fun deleteAlarm(ID: String) {
        val database = this.writableDatabase
        database.delete(alarmDB, "$colID=?", arrayOf(ID))
        database.close()
    }

    fun updateData(alarm: alarm) {
        val database = this.writableDatabase
        var content = ContentValues()
        content.put(colDate, alarm.dateTime)
        content.put(colTime, alarm.studyTime)

        database.update(alarmDB, content, "$colID=?", arrayOf(alarm.ID.toString()))
        database.close()
    }

    fun readAlarms(): MutableList<alarm> {
        var list: MutableList<alarm> = ArrayList()
        val database = this.writableDatabase
        val query: Cursor = database.rawQuery("Select * from $alarmDB", null)
        if (query.moveToFirst()) {
            while (!query.isAfterLast) {
                val colID = query.getString(query.getColumnIndex(colID)).toInt()
                val colDate = query.getString(query.getColumnIndex(colDate))
                val colTime = query.getString(query.getColumnIndex(colTime))
                val alarm = alarm(colID, colDate, colTime)

                list.add(alarm)
                query.moveToNext()
            }

        }
        database.close()
        query.close()
        return list
    }

    fun getSize(): Int {
        return readAlarms().size - 1
    }

    fun logInsert():String {
        var a : String

        a = colID.toString() + colDate.toString() + colTime.toString() + colDate.toString()
        return a
    }


    class alarm(val ID: Int, val dateTime: String, val studyTime: String) {
    }

}