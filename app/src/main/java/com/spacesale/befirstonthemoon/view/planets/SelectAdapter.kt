package com.spacesale.befirstonthemoon.view.planets

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SelectAdapter(fragment: SelectFragment, private val planets: Map<Int, PlanetInfo>)
    : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = planets.size

    override fun createFragment(position: Int): Fragment = PlanetFragment.newInstance(position, planets)
}