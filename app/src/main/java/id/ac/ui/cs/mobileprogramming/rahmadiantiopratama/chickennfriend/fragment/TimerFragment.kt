package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity.MainActivity
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity.MainActivity.Companion.detikSekarang
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity.MainActivity.Companion.removeAlarm
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity.MainActivity.Companion.setAlarm
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.util.NotificationUtil
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.util.PrefUtil
import kotlinx.android.synthetic.main.fragment_timer.*
import java.lang.NumberFormatException

class TimerFragment : Fragment() {
    private lateinit var timer: CountDownTimer
    private var detik: Long = 0
    private var isMulai: Boolean = false
    private var sisaDetik: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_timer, container, false)
        val btnPlay = view.findViewById<Button>(R.id.btn_play)
        btnPlay.setOnClickListener(){
            if(!isMulai){
                val total = 1
                PrefUtil.setTimerLength(total, context as MainActivity)
                setTimerBaru()
                timerMulai()
                isMulai = true
            }
        }

        val btnStop = view.findViewById<Button>(R.id.btn_stop)
        btnStop.setOnClickListener(){
            timer.cancel()
            PrefUtil.setTimerLength(0, context as MainActivity)
            onTimerFinished()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        initTimer()
        removeAlarm((context as MainActivity))
        NotificationUtil.sembunyikanNotifikasi(context as MainActivity)
    }

    override fun onPause() {
        super.onPause()
        if(isMulai){
            timer.cancel()
            val waktuBangun = setAlarm((context as MainActivity), detikSekarang, sisaDetik)
            NotificationUtil.lihatTimerBerjalan(context as MainActivity, waktuBangun)
        }

        PrefUtil.setPreviousTimerLength(detik, (context as MainActivity))
        PrefUtil.setRemainingTimerLength(sisaDetik, (context as MainActivity))
        PrefUtil.setTimerState(isMulai, (context as MainActivity))
    }

    private fun initTimer(){
        isMulai = PrefUtil.getTimerState((context as MainActivity))
        if(!isMulai){
            setTimerBaru()
            sisaDetik = detik
        }
        else{
            detik = PrefUtil.getPreviousTimerLength((context as MainActivity))
            sisaDetik = PrefUtil.getRemainingTimerLength((context as MainActivity))
        }

        val setAlarm = PrefUtil.getAlarmSetTime(context as MainActivity)
        if(setAlarm > 0){
            sisaDetik -= detikSekarang - setAlarm
        }

        if(sisaDetik <= 0){
            onTimerFinished()
        }

        else if(isMulai){
            timerMulai()
        }
        updatePerhitungan()
    }

    private fun timerMulai(){
        if(sisaDetik <= 0){
            sisaDetik = detik
        }
        timer = object : CountDownTimer(sisaDetik * 1000, 1000){
            override fun onFinish() {
                onTimerFinished()
            }

            override fun onTick(millisUntilFinished: Long) {
                sisaDetik = millisUntilFinished / 1000
                updatePerhitungan()
            }
        }.start()
    }

    private fun setTimerBaru(){
        val menit = PrefUtil.getTimerLength(context as MainActivity)
        detik = (menit * 60L)
    }

    private fun updatePerhitungan(){
        val sisaMenit = sisaDetik / 60
        val stringSisaMenit = sisaMenit.toString()
        val sisaDetikDalamMenit = sisaDetik - sisaMenit * 60
        val stringSisaDetik = sisaDetikDalamMenit.toString()
        waktu.text = "$stringSisaMenit:${
        if(stringSisaDetik.length == 2) stringSisaDetik
        else "" + stringSisaDetik
        }"
    }

    private fun onTimerFinished(){
        isMulai = false
        setTimerBaru()
        PrefUtil.setRemainingTimerLength(detik, (context as MainActivity))
        sisaDetik = detik
        updatePerhitungan()
    }

    companion object{
        @JvmStatic
        fun newInstance() = TimerFragment()
    }
}