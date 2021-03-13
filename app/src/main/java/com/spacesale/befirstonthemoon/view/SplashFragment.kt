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
import com.spacesale.befirstonthemoon.domain.Planet
import com.spacesale.befirstonthemoon.view.planets.SelectFragment
import com.spacesale.befirstonthemoon.view.planets.SelectPlanetViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private val mPlanets: MutableMap<Int, Planet> = mutableMapOf()

    private val selectPlanetViewModel: SelectPlanetViewModel by viewModel()

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

        selectPlanetViewModel.planetLiveData.observe(viewLifecycleOwner) { planets ->
            planets.forEach {
                mPlanets[it.id] = it
            }
        }
        selectPlanetViewModel.loadPlanets()

        appName = view.findViewById(R.id.app_name)
        catImage = view.findViewById(R.id.cat_image)
        cometLeft = view.findViewById(R.id.comet_left)
        cometRight = view.findViewById(R.id.comet_right)
        sun = view.findViewById(R.id.sun)
        startAnimation()

        object : CountDownTimer(5000, 1000) {
            override fun onFinish() {
                (activity as MainActivity).loadFragment(SelectFragment.newInstance(mPlanets))
            }
            override fun onTick(millisUntilFinished: Long) {}
        }.start()
    }

    fun startAnimation() {
        val metrics = getMetrics()
        val density = getDensity(metrics)
        val height = getScreenHeight(metrics)
        val width = getScreenWidth(metrics)

        val catYAnimator = ValueAnimator.ofFloat(-1000f,
            (height / density / 1.25).toFloat()
        )
        catYAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            catImage.translationY = value
        }
        catYAnimator.interpolator = AccelerateInterpolator(2.5f)
        catYAnimator.duration = 1000
        catYAnimator.start()

        val catXAnimator = ValueAnimator.ofFloat(-500f,
            (width / density / 1.1).toFloat()
        )
        catXAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            catImage.translationX = value
        }
        catXAnimator.interpolator = AccelerateInterpolator(2.5f)
        catXAnimator.duration = 1000
        catXAnimator.start()

        val catScaleAnimator = ValueAnimator.ofFloat(0.25f, 1f)
        catScaleAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            catImage.scaleX = value
            catImage.scaleY = value
        }
        catScaleAnimator.interpolator = LinearInterpolator()
        catScaleAnimator.duration = 1000
        catScaleAnimator.start()

        val catRotationAnimator = ValueAnimator.ofFloat(0f, 10f)
        catRotationAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            catImage.rotation = value
        }
        catRotationAnimator.interpolator = LinearInterpolator()
        catRotationAnimator.duration = 1000
        catRotationAnimator.start()

        val textAnimator = ValueAnimator.ofFloat(-1000f, 50f)
        textAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            appName.translationX = value
        }
        textAnimator.interpolator = AccelerateInterpolator(2.5f)
        textAnimator.duration = 1000
        textAnimator.start()

        val cometLeftXAnimator = ValueAnimator.ofFloat(-200f, 1100f)
        cometLeftXAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            cometLeft.translationX = value
        }
        cometLeftXAnimator.interpolator = AccelerateInterpolator(2.5f)
        cometLeftXAnimator.duration = 3000
        cometLeftXAnimator.start()

        val cometLeftYAnimator = ValueAnimator.ofFloat(-200f,
            (height / density / 2.5).toFloat())
        cometLeftYAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            cometLeft.translationY = value
        }
        cometLeftYAnimator.interpolator = AccelerateInterpolator(2.5f)
        cometLeftYAnimator.duration = 3000
        cometLeftYAnimator.start()

        val cometRightXAnimator = ValueAnimator.ofFloat(1100f, -200f)
        cometRightXAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            cometRight.translationX = value
        }
        cometRightXAnimator.interpolator = AccelerateInterpolator(2.5f)
        cometRightXAnimator.duration = 3000
        cometRightXAnimator.start()

        val cometRightYAnimator = ValueAnimator.ofFloat(-200f,
            (height / density / 2.5).toFloat())
        cometRightYAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            cometRight.translationY = value
        }
        cometRightYAnimator.interpolator = AccelerateInterpolator(2.5f)
        cometRightYAnimator.duration = 3000
        cometRightYAnimator.start()

        val sunRotationAnimator = ValueAnimator.ofFloat(0f, 360f)
        sunRotationAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            sun.rotation = value
        }
        sunRotationAnimator.interpolator = AccelerateInterpolator(1.5f)
        sunRotationAnimator.duration = 2000
        sunRotationAnimator.repeatCount = ValueAnimator.INFINITE;
        sunRotationAnimator.start()
    }

    private fun getScreenWidth(displayMetrics: DisplayMetrics): Float =
        displayMetrics.widthPixels.toFloat()

    private fun getScreenHeight(displayMetrics: DisplayMetrics): Float =
        displayMetrics.heightPixels.toFloat()

    private fun getDensity(displayMetrics: DisplayMetrics) = displayMetrics.density

    private fun getMetrics(): DisplayMetrics = requireContext().resources.displayMetrics

    companion object {
        fun newInstance() = SplashFragment()
    }
}