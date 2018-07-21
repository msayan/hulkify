package com.wololo.hulkify.utils

import android.app.Notification
import android.app.NotificationChannel
//import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
//import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationCompat.VISIBILITY_PRIVATE
import com.wololo.hulkify.R
import com.wololo.hulkify.ui.main.MainActivity
import com.wololo.hulkify.utils.extensions.hasOreo
import java.lang.ref.WeakReference

const val DETECT_CHANNEL: String = "com.wololo.hulkify.utils.DETECT_CHANNEL"
const val DETECT_NOTIFICATION: Int = 0xb339

class ShakeNotificationBuilder(private val contextWeakRef: WeakReference<Context>) {
    private val context get() = contextWeakRef.get()
    private val platformNotificationManager: NotificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    fun build(): Notification {
        if (shouldCreateChannel()) {
            createChannel()
        }

        val intent = PendingIntent.getActivity(context,
                -1,
                Intent(context, MainActivity::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT)

        return NotificationCompat.Builder(context!!, DETECT_CHANNEL)
                .setSmallIcon(R.drawable.hulk)
                .setContentIntent(intent)
                .setVisibility(VISIBILITY_PRIVATE)
                .setContentTitle(context?.getString(R.string.app_name))
                .setContentText("Click to close")
                .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val notificationChannel = NotificationChannel(DETECT_CHANNEL,
                "sensor_channel",
                NotificationManager.IMPORTANCE_MIN)
                .apply {
                    description = "Hulkify app opener"
                }
        platformNotificationManager.createNotificationChannel(notificationChannel)
    }

    private fun shouldCreateChannel() =
            hasOreo() && !channelExists()

    @RequiresApi(Build.VERSION_CODES.O)
    private fun channelExists() =
            platformNotificationManager.getNotificationChannel(DETECT_CHANNEL) != null
}