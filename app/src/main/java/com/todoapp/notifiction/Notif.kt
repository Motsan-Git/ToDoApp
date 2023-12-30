package com.todoapp.notifiction

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Build.VERSION_CODES.S
import androidx.annotation.RequiresApi
import com.todoapp.model.ToDo
import java.util.Calendar

@RequiresApi(VERSION_CODES.S)
class Notif(
    var name: String,
    var description: String,
    var context: Context,
    var id: String,
    var todo: ToDo
) {
    init {
        crateChannel(name,description)
        startNotification()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun crateChannel(name: String, description: String) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id, name, importance)
        channel.description = description
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
    private fun startNotification() {
        val calender = Calendar.getInstance()
        calender.set(Calendar.HOUR_OF_DAY, (todo.time).split(":")[0].toInt())
        calender.set(Calendar.MINUTE, (todo.time).split(":")[1].toInt())
        calender.set(Calendar.DAY_OF_MONTH, (todo.date).split("/")[2].toInt())
        calender.set(Calendar.MONTH, (todo.date).split("/")[1].toInt())
        calender.set(Calendar.YEAR, (todo.date).split("/")[0].toInt())

        val notifIntent = Intent(context, Notifreceiver::class.java)
        notifIntent.putExtra("id", id)
        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager

        if (alarmManager.canScheduleExactAlarms()) {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP, calender.timeInMillis, PendingIntent.getBroadcast(
                    context, id.toInt(), notifIntent, PendingIntent.FLAG_IMMUTABLE
                )
            )
        }
    }
}