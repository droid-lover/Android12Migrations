package com.sachin.android12migrations.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.sachin.android12migrations.notifications.NotificationHelper
/**
 * Created by Sachin
 */
class MyAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.also {
            Toast.makeText(it,"Coming",Toast.LENGTH_SHORT).show()
            val notificationHelper = NotificationHelper(context)
            NotificationHelper(context).makeNotification(notificationHelper.createNotificationBuilder(),1001)
        }
    }
}