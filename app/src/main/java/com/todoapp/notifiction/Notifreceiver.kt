package com.todoapp.notifiction

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import com.todoapp.R
import com.todoapp.fragment.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class Notifreceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val currentId = intent.extras!!.getInt("id")
        val notifManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        runBlocking {
             val currentTodo = context.dataStore.data.first().todoList.find {
                it.hashCode() == currentId
            }
            createNotification(context ,currentId.toString(),currentTodo!!.title,currentTodo.description,notifManager)
        }

    }

    private fun createNotification(
        notificationManager: NotificationManager,
        titel:String,
        desc:String,
        context: Context?,
        id: String) {
        val notification = NotificationCompat.Builder(context!!, id)
            .setSmallIcon(R.drawable.baseline_access_alarms_24)
            .setContentTitle(titel)
            .setContentText(desc).setPriority(NotificationCompat.PRIORITY_HIGH).build()
        notificationManager.notify(id.toInt(),notification)

    }
}