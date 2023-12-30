package com.todoapp.notifiction

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

class Notif(var context: Context) {
    @RequiresApi(Build.VERSION_CODES.O)
    private fun crateChannel(name: String, description: String, id: String) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id, name, importance)
        channel.description = description
        val notificationManager=context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}