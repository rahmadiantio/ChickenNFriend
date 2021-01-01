package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity.MainActivity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.util.NotificationUtil
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.util.PrefUtil

class TimerNotificationActionReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            AppConstant.ACTION_START -> {
                val detikSekarang = 60L
                val waktuBangun = MainActivity.setAlarm(context, 0, detikSekarang)
                PrefUtil.setTimerState(true, context)
                PrefUtil.setAlarmSetTime(detikSekarang, context)
                NotificationUtil.lihatTimerBerjalan(context, waktuBangun)
            }

            AppConstant.ACTION_STOP -> {
                MainActivity.removeAlarm(context)
                PrefUtil.setTimerState(false, context)
                NotificationUtil.sembunyikanNotifikasi(context)
            }
        }
    }
}