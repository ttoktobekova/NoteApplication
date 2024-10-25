package com.example.noteapp.ui.massaging

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.noteapp.R
import com.example.noteapp.ui.activity.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingServiceFirebase : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        message.notification?.let {
            val title = it.title ?: ""
            val description = it.body ?: ""
            show(title, description)
        }
    }

    @SuppressLint("MissingPermission")
    private fun show(title: String, description: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pending = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
        val notificationLayout = getNotificationLayout(title, description)
        val builder = NotificationCompat.Builder(this, ID_CHANNEL)
            .setSmallIcon(R.drawable.img_messaging)
            .setContentIntent(pending)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
        createNotification()
        with(NotificationManagerCompat.from(this)) {
            notify(ID_NOTIFICATION, builder.build())
        }
    }

    @SuppressLint("RemoteViewLayout")
    private fun getNotificationLayout(title: String, description: String): RemoteViews? {
        val remoteView = RemoteViews(packageName, R.layout.item_message)
        remoteView.setTextViewText(R.id.tv_title, title)
        remoteView.setTextViewText(R.id.tv_messaging, description)
        remoteView.setImageViewResource(R.id.img_logo_message, R.drawable.img_messaging)
        return remoteView
    }

    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel =
                NotificationChannel(ID_CHANNEL, ID_NAME, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)

        }
    }

    companion object {
        const val ID_CHANNEL = "channel_id"
        const val ID_NAME = "channel_Name"
        const val ID_NOTIFICATION = 0
    }
}