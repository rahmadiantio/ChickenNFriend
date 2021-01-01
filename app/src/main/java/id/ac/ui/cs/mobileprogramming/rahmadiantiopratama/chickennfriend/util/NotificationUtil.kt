package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.util

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity.MainActivity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.receiver.AppConstant
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.receiver.TimerNotificationActionReceiver
import java.text.SimpleDateFormat
import java.util.*

class NotificationUtil {
    companion object{
        private const val CHANNEL_ID_TIMER = "menu_timer"
        private const val CHANNEL_NAME_TIMER = "ChickenNFriend"
        private const val TIMER_ID = 0

        fun lihatTimerBerjalan(context: Context, wakeUpTime: Long){
            val intentStop = Intent(context, TimerNotificationActionReceiver::class.java)
            intentStop.action = AppConstant.ACTION_STOP
            val pendingIntentStop = PendingIntent.getBroadcast(context, 0, intentStop, PendingIntent.FLAG_UPDATE_CURRENT)

            val simpleDataFormat = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT)

            val builderTimerJln = getBasicNotificationBuilder(context, CHANNEL_ID_TIMER, true)
            builderTimerJln.setContentTitle("Timer is Running")
                .setContentText("End at ${simpleDataFormat.format(Date(wakeUpTime))}")
                .setContentIntent(getPendingIntentWithStack(context, MainActivity::class.java))
                .setOngoing(true)
                .addAction(R.drawable.ic_stop, "Stop", pendingIntentStop)

            val managerTImerJln = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            managerTImerJln.createNotificationChannel(CHANNEL_ID_TIMER, CHANNEL_NAME_TIMER, true)
            managerTImerJln.notify(TIMER_ID, builderTimerJln.build())
        }

        fun lihatSisaTimer(context: Context){
            val intentMulai = Intent(context, TimerNotificationActionReceiver::class.java)
            intentMulai.action = AppConstant.ACTION_START
            val startPendingIntent = PendingIntent.getBroadcast(context, 0, intentMulai, PendingIntent.FLAG_UPDATE_CURRENT)

            val builderTimerSisa = getBasicNotificationBuilder(context, CHANNEL_ID_TIMER, true)
            builderTimerSisa.setContentTitle("Timer Expired")
                .setContentText("Pesanan Sudah Siap, Harap Mengambil")
                .setContentIntent(getPendingIntentWithStack(context, MainActivity::class.java))

            val managerTimerSisa = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            managerTimerSisa.createNotificationChannel(CHANNEL_ID_TIMER, CHANNEL_NAME_TIMER, true)
            managerTimerSisa.notify(TIMER_ID, builderTimerSisa.build())
        }

        fun sembunyikanNotifikasi(context: Context){
            val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nManager.cancel(TIMER_ID)
        }

        private fun <T> getPendingIntentWithStack(context: Context, javaClass: Class<T>): PendingIntent {
            val intentHasil = Intent(context, javaClass)
            intentHasil.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

            val stackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addParentStack(javaClass)
            stackBuilder.addNextIntent(intentHasil)

            return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        private fun getBasicNotificationBuilder(context: Context, channelId: String, sound: Boolean): NotificationCompat.Builder {
            val nBuilder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_timer)
                .setAutoCancel(true)
                .setDefaults(0)
            if(sound){
                val notificationSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                nBuilder.setSound(notificationSound)
            }
            return nBuilder
        }

        @TargetApi(24)
        private fun NotificationManager.createNotificationChannel(channelID: String, channelName: String, sound: Boolean){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channelImportance = if(sound) NotificationManager.IMPORTANCE_DEFAULT
                else NotificationManager.IMPORTANCE_LOW

                val nChannel = NotificationChannel(channelID, channelName, channelImportance)
                nChannel.enableLights(true)
                nChannel.lightColor = Color.BLUE
                this.createNotificationChannel(nChannel)
            }
        }
    }
}