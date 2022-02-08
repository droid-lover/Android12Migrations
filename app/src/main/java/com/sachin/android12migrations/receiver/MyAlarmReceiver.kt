package com.sachin.android12migrations.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sachin.android12migrations.notifications.NotificationHelper

class MyAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.also {
            val notificationHelper = NotificationHelper(context)
            NotificationHelper(context).makeNotification(notificationHelper.createNotificationBuilder(),1001)
        }
    }
}