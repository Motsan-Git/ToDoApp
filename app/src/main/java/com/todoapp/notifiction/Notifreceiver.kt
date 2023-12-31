package com.todoapp.notifiction

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import com.todoapp.R
import com.todoapp.fragment.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class Notifreceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val currentId = intent.extras!!.getString("id")

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        fun createNotification(
            context: Context?,
            id: String,
            titel: String,
            desc: String,
            notificationManager: NotificationManager,
        ) {
            val notification = NotificationCompat.Builder(context!!, id)
                .setSmallIcon(R.drawable.baseline_access_alarms_24)
                .setContentTitle(titel)
                .setContentText(desc).setPriority(NotificationCompat.PRIORITY_HIGH).build()
            notificationManager.notify(id.toInt(), notification)
        }
        runBlocking {
            val currentTodo = context.dataStore.data.first().todoList.find {
                it.hashCode() == currentId!!.toInt()
            }

            createNotification(
                context,
                currentTodo.toString(),
                currentTodo!!.title,
                currentTodo.description,
                notificationManager
            )
        }
    }
}


