package com.wololo.hulkify.utils.service

import android.app.Service
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.Intent
import android.hardware.SensorManager
import android.os.IBinder
import com.wololo.hulkify.utils.ShakeDetector
import com.wololo.hulkify.utils.ShakeDetector.Companion.SENSITIVITY_LIGHT
import com.wololo.hulkify.utils.UnlockActivity
import java.lang.ref.WeakReference

class SensorService : Service() {
    private val devicePolicyManager by lazy {
        getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
    }

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private lateinit var shakeDetector: ShakeDetector

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        val notification = NotificationBuilder(WeakReference(this)).build()
        startForeground(DETECT_NOTIFICATION, notification)
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        shakeDetector = ShakeDetector(object : ShakeDetector.Listener {
            override fun hearShake() {
                startActivity(Intent(this@SensorService, UnlockActivity::class.java))
            }
        })

        shakeDetector.setSensitivity(SENSITIVITY_LIGHT)

        shakeDetector.start(sensorManager)

        return START_NOT_STICKY
    }


    override fun onDestroy() {
        shakeDetector.stop()
        stopForeground(true)
        super.onDestroy()
    }
}