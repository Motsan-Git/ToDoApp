package com.todoapp.notifiction

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.navigation.NavDeepLinkBuilder
import com.todoapp.R
import com.todoapp.fragment.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import android.app.NotificationManager as NotificationManager

class Notifreceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val pendingIntent = NavDeepLinkBuilder(context).setGraph(R.navigation.nav_graph)
            .setDestination(R.id.currentToDos).createPendingIntent()
        val currentId = intent.extras!!.getString("id")


        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        fun createNotification(
            context: Context?,
            id: String,
            titel: String,
            description: String,
            notificationManager: NotificationManager,
            pendingIntent: PendingIntent
        ) {
            val notification = NotificationCompat.Builder(context!!, currentId.toString())
                .setSmallIcon(R.drawable.baseline_access_alarms_24)
                .setContentTitle(titel)
                .setContentText(description).setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent).build()
            notificationManager.notify(id.toInt(), notification)

        }

        runBlocking {

            val currentTodo = context.dataStore.data.first().todoList.find {
                it.hashCode() == currentId?.toInt()
            }

            createNotification(
                context,
                currentId.toString(),
                currentTodo!!.title,
                currentTodo.description,
                notificationManager,
                pendingIntent
            )
        }
    }
}


