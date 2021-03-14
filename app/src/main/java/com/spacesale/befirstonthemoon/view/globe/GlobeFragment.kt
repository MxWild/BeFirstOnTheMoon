package com.spacesale.befirstonthemoon.view.globe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.databinding.FragmentGlobeBinding
import com.spacesale.befirstonthemoon.domain.Planet
import com.spacesale.befirstonthemoon.view.profile.ProfileFragment
import gov.nasa.worldwind.WorldWind
import gov.nasa.worldwind.WorldWindow
import gov.nasa.worldwind.geom.Position
import gov.nasa.worldwind.layer.BackgroundLayer
import gov.nasa.worldwind.layer.RenderableLayer
import gov.nasa.worldwind.render.ImageSource
import gov.nasa.worldwind.shape.Polygon
import gov.nasa.worldwind.shape.ShapeAttributes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class GlobeFragment : Fragment() {

    private val viewModel: GlobeViewModel by viewModel()
    var layer = RenderableLayer()

    private lateinit var wwd: WorldWindow
    private var planetId by Delegates.notNull<Int>()
    private var planet: Planet? = null
    private var _binding: FragmentGlobeBinding? = null
    private val binding get() = _binding!!

    private var polygons: List<Polygon> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            planetId = it.getInt(PARAM_PLANET_ID)
            viewModel.loadPlanetInfo(planetId)
            //viewModel.loadSectors(planetId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGlobeBinding.inflate(inflater, container, false)
        wwd = WorldWindow(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.planet.observe(viewLifecycleOwner) {
            showGlobe(planetId)
            planet = it
        }

        viewModel.sectors.observe(viewLifecycleOwner) { sectors ->
            lifecycleScope.launch(Dispatchers.IO) {
                polygons = PolygonConverter().converterDbToPolygons(sectors.map { it.WKT })
                showPolygons()
            }
        }

        viewModel.loadSectors(planetId)

        binding.buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.buttonProfile.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment.instance(1/*todo*/))
                .addToBackStack(null)
                .commit()
        }

        binding.buttonBuy.setOnClickListener {
            //TODO добавить покупку выбранного участка по кнопке
            viewModel.buySector(planetId,1)
            Toast
                .makeText(
                    context,
                    String.format(getString(R.string.buy_toast), planet?.name),
                    Toast.LENGTH_LONG
                )
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

    private fun showPolygons() {
        //TODO обновление данных с полигонами из обсервера
        layer.addAllRenderables(polygons)
    }

    private fun showGlobe(planetId: Int) {
        viewModel.loadPlanetInfo(planetId)
        //viewModel.loadSectors(planetId)
        binding.globe.addView(createWorldWindow())
    }

    private fun createWorldWindow(): WorldWindow {
        val moon = viewModel.planet.value?.let {
            ImageSource.fromResource(it.texture)
        }
//        wwd.setBackgroundResource(R.drawable.background_stars)
        wwd.layers.addLayer(BackgroundLayer(moon, null))
        wwd.layers.addLayer(layer)
        wwd.worldWindowController = PickController(requireContext())
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