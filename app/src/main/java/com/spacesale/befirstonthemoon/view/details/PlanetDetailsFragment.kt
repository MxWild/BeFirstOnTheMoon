package com.spacesale.befirstonthemoon.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.databinding.FragmentPlanetDetailsBinding
import com.spacesale.befirstonthemoon.domain.Planet


class PlanetDetailsFragment : Fragment() {

    private var planetId: Int? = null


    private var _binding: FragmentPlanetDetailsBinding? = null

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
    ): View? {
        _binding = FragmentPlanetDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val planet = Planet(
            1,
            "Марс",
            R.drawable.mars_select,
            R.drawable.ic_marsdetails,
            R.drawable.mars,
            "Марс — четвёртая по удалённости от Солнца и седьмая по размеру планета Солнечной системы; масса планеты составляет 10,7 % массы Земли. Названа в честь Марса — древнеримского бога войны, соответствующего древнегреческому Аресу. Иногда Марс называют «красной планетой» из-за красноватого оттенка поверхности, придаваемого ей минералом маггемидом— γ-оксидом железа.",
            "95,32 % углекислый газ\n2,7 % азот\n1,6 % аргон\n0,145 % кислород\n0,08 % угарный газ\n0,021 % водяной пар\n0,01 % окись азота\n0,00025 % неон",
            "Масса - 6,39Е23 кг\nРасстояние от Земли - от 55,76 до 401 млн км\nРадиус - 3389,5 км\nТемпература - от −153 °C до +35 °C"
        )


        binding.planetAvatar.load(planet.detailPoster) {
            crossfade(true)
        }

        binding.titleDetails.text = planet.name
        binding.descriptionDetails.text = planet.description
        binding.atmosphereDetails.text = planet.atmosphere
        binding.characteristicsDescription.text = planet.characteristics
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