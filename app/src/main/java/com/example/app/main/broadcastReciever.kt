package com.example.app.main

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.app.R

class BC: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        var nChannel : NotificationChannel
        var nBuilder : NotificationCompat.Builder
        var nManager : NotificationManager

        Log.d("Shit", "Notification 1")


        val cIntent = Intent(context, LauncherActivity::class.java)
        val pIntent = PendingIntent.getActivity(context, 0, cIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        var desc = intent?.getStringExtra("Reminder")
        if (context != null) {
            nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (context != null) {
                nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){



                nChannel = NotificationChannel(
                    "package com.example.app",
                    "Notification",
                    NotificationManager.IMPORTANCE_HIGH)
                    nChannel.enableVibration(true)
                    nChannel.lightColor = Color.RED

                    nManager.createNotificationChannel(nChannel)

                if(context != null) {
                    nBuilder = NotificationCompat.Builder(context, "package com.example.app")
                        .setContentTitle("StudySmart A")
                        .setContentText(desc)
                        .setLargeIcon(BitmapFactory.decodeResource(context?.resources, R.drawable.ic_launcher_foreground))
                        .setSmallIcon(R.drawable.clock_icon)
                        .setContentIntent(pIntent)



                } else {
                    nBuilder = NotificationCompat.Builder(context)
                        .setContentTitle("StudySmart B")
                        .setContentText(desc)
                        .setLargeIcon(BitmapFactory.decodeResource(context?.resources, R.drawable.ic_launcher_foreground))
                        .setSmallIcon(R.drawable.clock_icon)
                        .setContentIntent(pIntent)

                    Log.d("Test", "If statement 3")

                }

                nManager.notify(0, nBuilder.build())
            }else {
//
                nBuilder = NotificationCompat.Builder(context, "package com.example.app")
                    .setSmallIcon(R.drawable.clock_icon)
                    .setContentTitle("StudySmart C")
                    .setContentText(desc)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)

                var compat : NotificationManagerCompat = NotificationManagerCompat.from(context)

                compat.notify(200, nBuilder.build())



            }

        }
    }

}