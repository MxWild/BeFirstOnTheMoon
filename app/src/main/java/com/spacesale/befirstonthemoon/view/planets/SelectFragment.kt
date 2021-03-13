package com.spacesale.befirstonthemoon.view.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.view.planets.SelectAdapter

const val ARG_PLANET_ID = "planet_id"
const val ARG_PLANET_NAME = "planet_name"
const val ARG_PLANET_DRAWABLE = "planet_drawable"

val mPlanets = arrayOf(PlanetInfo("Меркурий", R.drawable.mercury_select),
    PlanetInfo("Луна", R.drawable.moon_select),
    PlanetInfo("Марс", R.drawable.mars_select))

data class PlanetInfo(val name: String, val image: Int)

class SelectFragment : Fragment() {
    private lateinit var selectAdapter: SelectAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        selectAdapter = SelectAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = selectAdapter
    }

    companion object {
        fun newInstance() = SelectFragment()
    }
}
