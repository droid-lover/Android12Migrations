package com.sachin.android12migrations.notifications

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.sachin.android12migrations.MainActivity
import com.sachin.android12migrations.R

class NotificationHelper constructor(context: Context) : ContextWrapper(context) {

    val notificationManager: NotificationManager =
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "Android12_migrations_notification_id"
        const val NOTIFICATION_CHANNEL_NAME = "Android12_migrations_Default_Notifications_channel"
    }

    init {
        createNotificationChannel()
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        notificationManager.createNotificationChannel(NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            enableVibration(true)
        })
    }


    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    fun deleteChannel(channelId: String) = apply {
        notificationManager.deleteNotificationChannel(channelId)
    }

    fun createNotificationBuilder(): NotificationCompat.Builder {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        return NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .apply {
                setContentTitle("Android12 migration alarm")
                setSmallIcon(R.mipmap.ic_launcher)
                setContentIntent(pendingIntent)
                setAutoCancel(false)
            }
    }

    fun makeNotification(builder: NotificationCompat.Builder, notificationId: Int) = apply {
        notificationManager.notify(notificationId, builder.build())
    }

    fun cancelNotification(notificationId: Int) = apply {
        notificationManager.cancel(notificationId)
    }

    fun clearAllNotifications() {
        notificationManager.cancelAll()
    }

}