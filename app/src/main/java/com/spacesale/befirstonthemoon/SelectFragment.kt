package com.spacesale.befirstonthemoon

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

private const val ARG_PLANET_ID = "planet_id"
private const val ARG_PLANET_NAME = "planet_name"
private const val ARG_PLANET_DRAWABLE = "planet_drawable"

private val mPlanets = arrayOf(PlanetInfo("Меркурий", R.drawable.mercury_select),
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

class SelectAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = mPlanets.size

    override fun createFragment(position: Int): Fragment {
        val fragment = PlanetFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_PLANET_ID, position)
                putString(ARG_PLANET_NAME, mPlanets[position].name)
                putInt(ARG_PLANET_DRAWABLE, mPlanets[position].image)
        }
        return fragment
    }
}

class PlanetFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_select_planet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_PLANET_ID) }?.apply {
            val button: Button = view.findViewById(R.id.select_button)
            button.text = getInt(ARG_PLANET_ID).toString()
        }
        arguments?.takeIf { it.containsKey(ARG_PLANET_NAME) }?.apply {
            val text: TextView = view.findViewById(R.id.planet_name_text)
            text.text = getString(ARG_PLANET_NAME)
        }
        arguments?.takeIf { it.containsKey(ARG_PLANET_DRAWABLE) }?.apply {
            val image: ImageView = view.findViewById(R.id.planet_image)
            image.setImageResource(getInt(ARG_PLANET_DRAWABLE))
        }
    }
}
