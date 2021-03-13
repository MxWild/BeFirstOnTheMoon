package com.spacesale.befirstonthemoon.view.globe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.databinding.FragmentGlobeBinding
import com.spacesale.befirstonthemoon.view.profile.ProfileFragment
import gov.nasa.worldwind.WorldWindow
import gov.nasa.worldwind.layer.BackgroundLayer
import gov.nasa.worldwind.render.ImageSource

class GlobeFragment : Fragment() {

    private val viewModel: GlobeViewModel by viewModels()

    private lateinit var wwd: WorldWindow
    private var planetId: Int? = null
    private var _binding: FragmentGlobeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            planetId = it.getInt(PARAM_PLANET_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGlobeBinding.inflate(inflater, container, false)
        planetId?.let {
            viewModel.loadPlanetInfo(it)
        }
        binding.globe.addView(createWorldWindow())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.buttonProfile.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment.instance(ArrayList()))
                .addToBackStack(null)
                .commit()
        }

        binding.buttonBuy.setOnClickListener {
            //TODO добавить покупку выбранного участка по кнопке
            Toast
                .makeText(context, "Поздравляю, вы купили участок на марте", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        this.wwd.onResume()
    }

    override fun onPause() {
        super.onPause()
        this.wwd.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createWorldWindow(): WorldWindow {
        val moon = viewModel.planet.value?.let {
            ImageSource.fromResource(it.texture)
        }
        wwd = WorldWindow(context)
//        wwd.setBackgroundResource(R.drawable.background_stars)
        wwd.layers.addLayer(BackgroundLayer(moon, null))
        return wwd
    }

    companion object {

        private const val PARAM_PLANET_ID = "planetId"

        fun newInstance(planetId: Int): GlobeFragment {
            val fragment = GlobeFragment()
            val args = Bundle()
            args.putInt(PARAM_PLANET_ID, planetId)
            fragment.arguments = args
            return fragment
        }
    }
}