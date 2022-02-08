package com.sachin.android12migrations

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import com.sachin.android12migrations.receiver.MyAlarmReceiver
import java.util.*

class MainActivity : AppCompatActivity() {

    private val alarmManager by lazy { getSystemService(Context.ALARM_SERVICE) as AlarmManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun startAlarm(view: View) {

        val timePicker: TimePickerDialog
        var currentTimeCalendarInstance = Calendar.getInstance()
        val hour = currentTimeCalendarInstance.get(Calendar.HOUR_OF_DAY)
        val minute = currentTimeCalendarInstance.get(Calendar.MINUTE)

        timePicker = TimePickerDialog(
            this, { _, hourOfDay, minute ->
                val selectedTime =  Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY,hourOfDay)
                    set(Calendar.MINUTE,minute)
                    set(Calendar.SECOND,0)
                }

                val intent = Intent(this, MyAlarmReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, selectedTime.timeInMillis, pendingIntent)

            }, hour, minute, false
        )

        timePicker.show()

   }


    fun stopAlarm(view: View) {


    }
}