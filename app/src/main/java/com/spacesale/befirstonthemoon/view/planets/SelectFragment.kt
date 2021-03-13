package com.spacesale.befirstonthemoon.view.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.domain.Planet

val tempPlanets = arrayOf(
    Planet(0, "Меркурий", R.drawable.mercury_select,
        R.drawable.ic_marsdetails, R.drawable.mars, "", "", ""),
    Planet(1, "Луна", R.drawable.moon_select,
        R.drawable.ic_marsdetails, R.drawable.mars, "", "", ""),
    Planet(2, "Марс", R.drawable.mars_select,
        R.drawable.ic_marsdetails, R.drawable.mars, "", "", ""))

data class PlanetInfo(val name: String, val image: Int)
private val mPlanets: MutableMap<Int, PlanetInfo> = mutableMapOf()

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
        tempPlanets.forEach {
            mPlanets[it.id] = PlanetInfo(it.name, it.mainPoster)
        }

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
