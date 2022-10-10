package org.hyperskill.projectname

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    companion object {
        const val NOTIFICATION_ID = 393939
        const val NOTIFICATION_CHANNEL_ID = "org.hyperskill"
        const val NOTIFICATION_CHANNEL_NAME = "hyperskill"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        val notificationSendButton = findViewById<Button>(R.id.notificationSendButton)

        notificationSendButton.setOnClickListener(::onNotificationSendClick)
    }

    private fun onNotificationSendClick(v: View) {
        println("onNotificationSendClick")
        postNotification()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun postNotification() {
        val notificationBuilder =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            else
                NotificationCompat.Builder(applicationContext)

        val notification = notificationBuilder
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("some title")
            .setContentText("some text")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .build()

        notification.flags = notification.flags.or(Notification.FLAG_INSISTENT);

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}