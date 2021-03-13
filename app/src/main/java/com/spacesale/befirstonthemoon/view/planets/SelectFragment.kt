package com.spacesale.befirstonthemoon.view.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.spacesale.befirstonthemoon.R

data class PlanetInfo(val name: String, val image: Int)

private val mPlanets = arrayOf(
    PlanetInfo("Меркурий", R.drawable.mercury_select),
    PlanetInfo("Луна", R.drawable.moon_select),
    PlanetInfo("Марс", R.drawable.mars_select))

class SelectFragment : Fragment() {
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
        selectAdapter = SelectAdapter(this, mPlanets)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = selectAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0) setArrowVisibility(true, false)
                else setArrowVisibility(true, true)
                if (position == selectAdapter.itemCount - 1) setArrowVisibility(false, false)
                else setArrowVisibility(false, true)
            }

            fun setArrowVisibility(isLeft: Boolean, visible: Boolean) {
                val arrow: ImageView = view.findViewById(if (isLeft) R.id.left_arrow else R.id.right_arrow)
                arrow.visibility = if (visible) View.VISIBLE else View.GONE
            }
        })

        leftArrow = view.findViewById(R.id.left_arrow)
        leftArrow.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem - 1
        }
        rightArrow = view.findViewById(R.id.right_arrow)
        rightArrow.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem + 1
        }
    }

    companion object {
        fun newInstance() = SelectFragment()
    }
}
