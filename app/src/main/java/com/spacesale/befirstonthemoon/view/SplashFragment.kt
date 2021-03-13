package com.spacesale.befirstonthemoon.view

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.spacesale.befirstonthemoon.MainActivity
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.view.planets.SelectFragment

class SplashFragment : Fragment() {

    private lateinit var catImage: ImageView
    private lateinit var appName: TextView
    private lateinit var cometLeft: ImageView
    private lateinit var cometRight: ImageView
    private lateinit var sun: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appName = view.findViewById(R.id.app_name)
        catImage = view.findViewById(R.id.cat_image)
        cometLeft = view.findViewById(R.id.comet_left)
        cometRight = view.findViewById(R.id.comet_right)
        sun = view.findViewById(R.id.sun)
        startAnimation()

        object : CountDownTimer(5000, 1000) {
            override fun onFinish() {
                (activity as MainActivity).loadFragment(SelectFragment.newInstance())
            }
            override fun onTick(millisUntilFinished: Long) {}
        }.start()
    }

    fun startAnimation() {
        val metrics = getMetrics()
        val density = getDensity(metrics)
        val height = getScreenHeight(metrics)
        val width = getScreenWidth(metrics)

        val valueAnimator0 = ValueAnimator.ofFloat(-1000f,
            (height / density / 1.25).toFloat()
        )
        valueAnimator0.addUpdateListener {
            val value = it.animatedValue as Float
            catImage.translationY = value
        }

        valueAnimator0.interpolator = AccelerateInterpolator(2.5f)
        valueAnimator0.duration = 1000

        valueAnimator0.start()

        val valueAnimator1 = ValueAnimator.ofFloat(-500f,
            (width / density / 1.1).toFloat()
        )
        valueAnimator1.addUpdateListener {
            val value = it.animatedValue as Float
            catImage.translationX = value
        }

        valueAnimator1.interpolator = AccelerateInterpolator(2.5f)
        valueAnimator1.duration = 1000

        valueAnimator1.start()

        val valueAnimator2 = ValueAnimator.ofFloat(0.25f, 1f)
        valueAnimator2.addUpdateListener {
            val value = it.animatedValue as Float
            catImage.scaleX = value
            catImage.scaleY = value
        }

        valueAnimator2.interpolator = LinearInterpolator()
        valueAnimator2.duration = 1000

        valueAnimator2.start()

        val valueAnimator3 = ValueAnimator.ofFloat(0f, 10f)
        valueAnimator3.addUpdateListener {
            val value = it.animatedValue as Float
            catImage.rotation = value
        }

        valueAnimator3.interpolator = AccelerateInterpolator(2.5f)
        valueAnimator3.duration = 1000

        valueAnimator3.start()

        val valueAnimator4 = ValueAnimator.ofFloat(-1000f, 50f)
        valueAnimator4.addUpdateListener {
            val value = it.animatedValue as Float
            appName.translationX = value
        }

        valueAnimator4.interpolator = AccelerateInterpolator(2.5f)
        valueAnimator4.duration = 1000

        valueAnimator4.start()

        val valueAnimator5 = ValueAnimator.ofFloat(-200f, 1100f)
        valueAnimator5.addUpdateListener {
            val value = it.animatedValue as Float
            cometLeft.translationX = value
        }

        valueAnimator5.interpolator = AccelerateInterpolator(2.5f)
        valueAnimator5.duration = 2500

        valueAnimator5.start()

        val valueAnimator6 = ValueAnimator.ofFloat(-200f,
            (height / density / 2.5).toFloat())
        valueAnimator6.addUpdateListener {
            val value = it.animatedValue as Float
            cometLeft.translationY = value
        }

        valueAnimator6.interpolator = AccelerateInterpolator(2.5f)
        valueAnimator6.duration = 2500

        valueAnimator6.start()

        val valueAnimator7 = ValueAnimator.ofFloat(1100f, -200f)
        valueAnimator7.addUpdateListener {
            val value = it.animatedValue as Float
            cometRight.translationX = value
        }

        valueAnimator7.interpolator = AccelerateInterpolator(2.5f)
        valueAnimator7.duration = 2500

        valueAnimator7.start()

        val valueAnimator8 = ValueAnimator.ofFloat(-200f,
            (height / density / 2.5).toFloat())
        valueAnimator8.addUpdateListener {
            val value = it.animatedValue as Float
            cometRight.translationY = value
        }

        valueAnimator8.interpolator = AccelerateInterpolator(2.5f)
        valueAnimator8.duration = 2500

        valueAnimator8.start()

        val valueAnimator9 = ValueAnimator.ofFloat(0f, 360f)
        valueAnimator9.addUpdateListener {
            val value = it.animatedValue as Float
            sun.rotation = value
        }

        valueAnimator9.interpolator = AccelerateInterpolator(1.5f)
        valueAnimator9.duration = 2000
        valueAnimator9.repeatCount = ValueAnimator.INFINITE;

        valueAnimator9.start()
    }

    private fun getScreenWidth(displayMetrics: DisplayMetrics): Float = displayMetrics.widthPixels.toFloat()

    private fun getScreenHeight(displayMetrics: DisplayMetrics): Float = displayMetrics.heightPixels.toFloat()

    private fun getDensity(displayMetrics: DisplayMetrics) = displayMetrics.density

    private fun getMetrics(): DisplayMetrics = requireContext().resources.displayMetrics

    companion object {
        fun newInstance() = SplashFragment()
    }
}