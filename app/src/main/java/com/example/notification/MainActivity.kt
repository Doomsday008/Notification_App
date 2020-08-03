package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Will work only for devices having Android Version 8 or more.
        val nm = getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = (NotificationChannel("First",
                "default", NotificationManager.IMPORTANCE_HIGH))
            channel.apply { enableVibration(true)
            enableLights(true)}
            nm.createNotificationChannel(channel)

        }
        button.setOnClickListener {
            val builder =
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                Notification.Builder(this, "First")
            }
            else{
                Notification.Builder(this)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setDefaults(Notification.DEFAULT_VIBRATE and Notification.DEFAULT_LIGHTS)
            }
            val simpleNotification = builder
                .setContentTitle("Simple Title")
                .setContentText("This is sample text for demo notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()
            nm.notify(1,simpleNotification)
        }
        button2.setOnClickListener {
            val i = Intent();
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https:\\www.google.com")
            val pi = PendingIntent.getActivity(this,123, i, PendingIntent.FLAG_UPDATE_CURRENT)

            val autoCancelNotification = NotificationCompat.Builder(this, "First")
                .setContentTitle("Chrome Notification")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setContentText("Click to Open Google.com")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(2,autoCancelNotification)
        }
        button3.setOnClickListener {
            val i = Intent();
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https:\\www.google.com")
            val pi = PendingIntent.getActivity(this,123, i, PendingIntent.FLAG_UPDATE_CURRENT)

            val autoCancelNotification = NotificationCompat.Builder(this, "First")
                .setContentTitle("Chrome Notification")
                .addAction(R.drawable.ic_launcher_foreground, "Click Me",pi)
                .setAutoCancel(true)
                .setContentText("Click to Open Google.com")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(2,autoCancelNotification)
        }
        //HeadsUp Notification (it has high priority)


    }
}