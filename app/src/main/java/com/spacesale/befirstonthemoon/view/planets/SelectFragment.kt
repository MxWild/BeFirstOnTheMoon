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
