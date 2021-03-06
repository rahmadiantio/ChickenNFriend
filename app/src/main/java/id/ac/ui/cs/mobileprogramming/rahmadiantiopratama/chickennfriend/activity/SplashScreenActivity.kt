package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.activity

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash_screen.*
import r21nomi.com.glrippleview.GLRippleView
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R

class SplashScreenActivity : AppCompatActivity() {
    lateinit var handler : Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}