package com.spacesale.befirstonthemoon.view.planets

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

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