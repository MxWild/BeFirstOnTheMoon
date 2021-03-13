package com.spacesale.befirstonthemoon.view.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.spacesale.befirstonthemoon.R

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