package com.spacesale.befirstonthemoon.view.planets

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.spacesale.befirstonthemoon.domain.Planet

class SelectAdapter(fragment: SelectFragment, private val planets: Map<Int, Planet>)
    : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = planets.size

    override fun createFragment(position: Int): Fragment = PlanetFragment.newInstance(position, planets)
}