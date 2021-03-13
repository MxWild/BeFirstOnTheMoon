package com.spacesale.befirstonthemoon.view.planets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.domain.Planet
import org.koin.android.viewmodel.ext.android.viewModel

private val mPlanets: MutableMap<Int, Planet> = mutableMapOf()

class SelectFragment : Fragment() {
    private val selectPlanetViewModel: SelectPlanetViewModel by viewModel()

    private lateinit var selectAdapter: SelectAdapter
    private lateinit var viewPager: ViewPager2

    private lateinit var leftArrow: ImageView
    private lateinit var rightArrow: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        selectPlanetViewModel.planetLiveData.observe(viewLifecycleOwner) { planets ->
            Log.e("QWERTY_it", planets.toString())
            planets.forEach {
                mPlanets[it.id] = it
            }
        }
        selectPlanetViewModel.loadPlanets()

        selectAdapter = SelectAdapter(this, mPlanets)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = selectAdapter

        leftArrow = view.findViewById(R.id.left_arrow)
        rightArrow = view.findViewById(R.id.right_arrow)

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
        fun newInstance() = SelectFragment()
    }
}
