package com.spacesale.befirstonthemoon.view.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import androidx.viewpager2.widget.ViewPager2
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.domain.Planet
import org.koin.android.viewmodel.ext.android.viewModel

class SelectFragment(planets: MutableMap<Int, Planet>) : Fragment() {

    private val mPlanets: MutableMap<Int, Planet> = planets

    private val selectPlanetViewModel: SelectPlanetViewModel by viewModel()

    private lateinit var selectAdapter: SelectAdapter
    private lateinit var viewPager: ViewPager2

    private lateinit var leftArrow: ImageView
    private lateinit var rightArrow: ImageView

    private lateinit var loadText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        reenterTransition = inflater.inflateTransition(R.transition.slide_left)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        leftArrow = view.findViewById(R.id.left_arrow)
        rightArrow = view.findViewById(R.id.right_arrow)
        loadText = view.findViewById(R.id.load_text)

        leftArrow.visibility = View.GONE
        rightArrow.visibility = View.GONE
        loadText.text = getString(R.string.loading_text_1)

        if (mPlanets.isEmpty()) {
            loadText.text = getString(R.string.loading_text_2)
            selectPlanetViewModel.planetLiveData.observe(viewLifecycleOwner) { planets ->
                planets.forEach {
                    mPlanets[it.id] = it
                }
                if (!mPlanets.isEmpty()) {
                    setViewPager(view)
                }
            }
            selectPlanetViewModel.loadPlanets()
        }
        else setViewPager(view)
    }

    private fun setViewPager(view: View) {
        loadText.visibility = View.GONE

        selectAdapter = SelectAdapter(this, mPlanets)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = selectAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                leftArrow.visibility =
                    if (position == 0) View.GONE else View.VISIBLE
                rightArrow.visibility =
                    if (position == selectAdapter.itemCount - 1) View.GONE else View.VISIBLE
            }
        })

        leftArrow.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem - 1
        }
        rightArrow.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem + 1
        }
    }

    companion object {
        fun newInstance(planets: MutableMap<Int, Planet>) = SelectFragment(planets)
    }
}
