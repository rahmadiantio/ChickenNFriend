package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.util.NotificationUtil
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.util.PrefUtil

class TimerExpiredReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.lihatSisaTimer(context)
        PrefUtil.setTimerState(false, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}