package com.spacesale.befirstonthemoon.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.databinding.FragmentPlanetDetailsBinding
import com.spacesale.befirstonthemoon.view.globe.GlobeFragment
import com.spacesale.befirstonthemoon.view.profile.ProfileFragment
import org.koin.android.viewmodel.ext.android.viewModel


class PlanetDetailsFragment : Fragment() {

    private var planetId: Int?=null

    private var _binding: FragmentPlanetDetailsBinding? = null

    private val binding get() = _binding!!

    private val planetDetailsViewModel: PlanetDetailsViewModel by viewModel()


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
        _binding = FragmentPlanetDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        planetDetailsViewModel.planetLiveData.observe(this.viewLifecycleOwner) { selectedPlanet ->

            binding.planetAvatar.load(selectedPlanet.detailPoster) {
                crossfade(true)
            }

            binding.titleDetails.text = selectedPlanet.name
            binding.descriptionDetails.text = selectedPlanet.description
            binding.atmosphereDetails.text = selectedPlanet.atmosphere
            binding.characteristicsDescription.text = selectedPlanet.characteristics
        }

        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.profileButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment.instance(1/*todo*/))
                .addToBackStack(null)
                .commit()
        }

        binding.buyPartOfPlanet.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GlobeFragment.newInstance(planetId!!))
                .addToBackStack(null)
                .commit()
        }

        planetDetailsViewModel.showPlanet(1)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val PARAM_PLANET_ID = "planetId"

        fun newInstance(
            planetId: Int
        ): PlanetDetailsFragment {
            val fragment = PlanetDetailsFragment()
            val args = Bundle()
            args.putInt(PARAM_PLANET_ID, planetId)
            fragment.arguments = args
            return fragment
        }
    }
}