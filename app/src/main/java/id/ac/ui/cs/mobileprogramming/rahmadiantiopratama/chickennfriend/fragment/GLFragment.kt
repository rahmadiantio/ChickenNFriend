package id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.rahmadiantiopratama.chickennfriend.R
import r21nomi.com.glrippleview.GLRippleView

class GLFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gl, container, false)
        val glObject = view.findViewById<GLRippleView>(R.id.glView)
        glObject.run{
            addBackgroundImages(listOf(
                BitmapFactory.decodeResource(resources, R.drawable.splash_screen_2),
                BitmapFactory.decodeResource(resources, R.drawable.splash_screen)
            ))
            setRippleOffset(0.01f)
            setFadeInterval(2500)
            startCrossFadeAnimation()
        }
        return view
    }

    companion object{
        @JvmStatic
        fun newInstance() = GLFragment()
    }
}