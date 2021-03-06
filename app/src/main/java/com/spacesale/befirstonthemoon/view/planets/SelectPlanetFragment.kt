package com.spacesale.befirstonthemoon.view.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.spacesale.befirstonthemoon.MainActivity
import com.spacesale.befirstonthemoon.databinding.FragmentSelectPlanetBinding
import com.spacesale.befirstonthemoon.domain.Planet
import com.spacesale.befirstonthemoon.view.details.PlanetDetailsFragment

private const val ARG_PLANET_ID = "planet_id"
private const val ARG_PLANET_NAME = "planet_name"
private const val ARG_PLANET_DRAWABLE = "planet_drawable"

class SelectPlanetFragment : Fragment() {

    private var _binding: FragmentSelectPlanetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectPlanetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.takeIf { it.containsKey(ARG_PLANET_ID) }?.apply {
            binding.selectButton.setOnClickListener {
                (activity as MainActivity)
                    .loadFragment(PlanetDetailsFragment.newInstance(getInt(ARG_PLANET_ID)))
            }

            binding.planetImage.setOnClickListener {
                (activity as MainActivity)
                    .loadFragment(PlanetDetailsFragment.newInstance(getInt(ARG_PLANET_ID)))
            }
        }
        arguments?.takeIf { it.containsKey(ARG_PLANET_NAME) }?.apply {
            binding.planetNameText.text = getString(ARG_PLANET_NAME)
        }
        arguments?.takeIf { it.containsKey(ARG_PLANET_DRAWABLE) }?.apply {
            binding.planetImage.setImageResource(getInt(ARG_PLANET_DRAWABLE))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(planet: Planet): Fragment {
            val fragment = SelectPlanetFragment()
            fragment.arguments = Bundle().apply {
                putInt(ARG_PLANET_ID, planet.id)
                putString(ARG_PLANET_NAME, planet.name)
                putInt(ARG_PLANET_DRAWABLE, planet.mainPoster)
            }
            return fragment
        }
    }
}