package com.sachin.android12migrations.views

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sachin.android12migrations.R
import com.sachin.android12migrations.receiver.MyAlarmReceiver
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
/**
 * Created by Sachin
 */
class MainActivity : AppCompatActivity() {

    private val alarmManager by lazy { getSystemService(Context.ALARM_SERVICE) as AlarmManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun startAlarm(view: View) {

        val timePicker: TimePickerDialog
        val currentTimeCalendarInstance = Calendar.getInstance()
        val hour = currentTimeCalendarInstance.get(Calendar.HOUR_OF_DAY)
        val minute = currentTimeCalendarInstance.get(Calendar.MINUTE)

        timePicker = TimePickerDialog(
            this, { _, hourOfDay, minute ->
                val selectedTime =  Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY,hourOfDay)
                    set(Calendar.MINUTE,minute)
                    set(Calendar.SECOND,0)
                }
                setAlarmState(getString(R.string.alarm_state)+ "  $hourOfDay $minute")
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, selectedTime.timeInMillis, getAlarmManagerPendingIntent())

            }, hour, minute, false
        )

        timePicker.show()

   }

    private fun setAlarmState(value: String?) {
        tvCurrentAlarmState.apply {
            setTextColor(if(value!=null) getColor(R.color.purple_500) else getColor(R.color.purple_200))
            text = value ?: "No Alarm is set as of now"
        }
    }


    fun stopAlarm(view: View) {
        alarmManager.cancel(getAlarmManagerPendingIntent())
        setAlarmState(null)
    }

    private fun getAlarmManagerPendingIntent(): PendingIntent? {
        val intent = Intent(this, MyAlarmReceiver::class.java)
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }
}