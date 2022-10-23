package com.iruda.servicesexample

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class MyIntentService : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        // if true -> same as START_REDELIVER_INTENT
        // if false -> same as START_NOT_STICKY
        setIntentRedelivery(false)
        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())
    }

    override fun onHandleIntent(p0: Intent?) {
        log("onHandleIntent")
        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("Timer $i")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "IntentService: $message")
    }

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun createNotification(): Notification =
        NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Title")
            .setContentText("Text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()


    companion object {

        private const val NAME = "my_intent_service"
        private const val NOTIFICATION_CHANNEL_ID = "notification_channel_id"
        private const val NOTIFICATION_NAME = "notification_name"
        private const val NOTIFICATION_ID = 1

        private const val EXTRA_START = "start"

        fun newIntent(context: Context): Intent {
            return Intent(context, MyIntentService::class.java)
        }
    }
}