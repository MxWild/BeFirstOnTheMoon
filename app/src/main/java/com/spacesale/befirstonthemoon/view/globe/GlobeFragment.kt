package com.spacesale.befirstonthemoon.view.globe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.databinding.FragmentGlobeBinding
import com.spacesale.befirstonthemoon.view.profile.ProfileFragment
import gov.nasa.worldwind.WorldWind
import gov.nasa.worldwind.WorldWindow
import gov.nasa.worldwind.geom.Position
import gov.nasa.worldwind.layer.BackgroundLayer
import gov.nasa.worldwind.render.ImageSource
import gov.nasa.worldwind.shape.Polygon
import gov.nasa.worldwind.shape.ShapeAttributes
import gov.nasa.worldwind.util.Logger
import gov.nasa.worldwind.util.WWUtil
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import kotlin.properties.Delegates

class GlobeFragment : Fragment() {

    private val viewModel: GlobeViewModel by viewModel()

    private lateinit var wwd: WorldWindow
    private var planetId by Delegates.notNull<Int>()
    private var _binding: FragmentGlobeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            planetId = it.getInt(PARAM_PLANET_ID)
            viewModel.loadPlanetInfo(planetId)
            viewModel.loadSectors(planetId)
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
        }

        viewModel.sectors.observe(viewLifecycleOwner) {
            //todo
        }

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
            Toast
                .makeText(
                    context,
                    String.format(getString(R.string.buy_toast), planetId.toString()),
                    Toast.LENGTH_LONG)
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

    private fun showGlobe(planetId: Int) {
        viewModel.loadPlanetInfo(planetId)
        viewModel.loadSectors(planetId)
        binding.globe.addView(createWorldWindow())
        //loadCountriesFile()
    }

    private fun createWorldWindow(): WorldWindow {
        val moon = viewModel.planet.value?.let {
            ImageSource.fromResource(it.texture)
        }
//        wwd.setBackgroundResource(R.drawable.background_stars)
        wwd.layers.addLayer(BackgroundLayer(moon, null))
        return wwd
    }

    private fun loadCountriesFile() {
        // Define the normal shape attributes
        val commonAttrs = ShapeAttributes()
        commonAttrs.interiorColor[1.0f, 1.0f, 0.0f] = 0.5f
        commonAttrs.outlineColor[0.0f, 0.0f, 0.0f] = 1.0f
        commonAttrs.outlineWidth = 3f

        // Define the shape attributes used for highlighted countries
        val highlightAttrs = ShapeAttributes()
        highlightAttrs.interiorColor[1.0f, 1.0f, 1.0f] = 0.5f
        highlightAttrs.outlineColor[1.0f, 1.0f, 1.0f] = 1.0f
        highlightAttrs.outlineWidth = 5f

        // Load the countries
        var reader: BufferedReader? = null
        try {
            val `in` = resources.openRawResource(R.raw.world_boundaries)
            reader = BufferedReader(InputStreamReader(`in`))

            // Process the header in the first line of the CSV file ...
            var line = reader.readLine()
            val headers = Arrays.asList(*line.split(",").toTypedArray())
            val GEOMETRY = headers.indexOf("WKT")
            val NAME = headers.indexOf("COUNTRY_NA")

            // ... and process the remaining lines in the CSV
            val WKT_START = "\"POLYGON ("
            val WKT_END = ")\""
            while (reader.readLine().also { line = it } != null) {
                // Extract the "well known text" feature and the attributes
                // e.g.: "POLYGON ((x.xxx y.yyy,x.xxx y.yyy), (x.xxx y.yyy,x.xxx y.yyy))",text,more text,...
                val featureBegin = line.indexOf(WKT_START) + WKT_START.length
                val featureEnd = line.indexOf(WKT_END, featureBegin) + WKT_END.length
                val feature = line.substring(featureBegin, featureEnd)
                val attributes = line.substring(featureEnd + 1)
                val fields = attributes.split(",").toTypedArray()
                val polygon = Polygon()
                polygon.altitudeMode = WorldWind.CLAMP_TO_GROUND
                polygon.pathType = WorldWind.LINEAR
                polygon.isFollowTerrain =
                    true // essential for preventing long segments from intercepting ellipsoid.
                polygon.displayName = fields[1]
                polygon.attributes = ShapeAttributes(commonAttrs)
                //polygon.getAttributes().setInteriorColor(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat(), 0.3f));
                polygon.highlightAttributes = highlightAttrs

                // Process all the polygons within this feature by creating "boundaries" for each.
                // Individual polygons are bounded by "(" and ")"
                var polyStart = feature.indexOf("(")
                while (polyStart >= 0) {
                    val polyEnd = feature.indexOf(")", polyStart)
                    val poly = feature.substring(polyStart + 1, polyEnd)

                    // Buildup the Polygon boundaries. Coordinate tuples are separated by ",".
                    val positions: MutableList<Position> = ArrayList()
                    val tuples = poly.split(",").toTypedArray()
                    for (i in tuples.indices) {
                        // The XY tuple components a separated by a space
                        val xy = tuples[i].split(" ").toTypedArray()
                        positions.add(Position.fromDegrees(xy[1].toDouble(), xy[0].toDouble(), 0.0))
                    }
                    polygon.addBoundary(positions)

                    // Locate the next polygon in the feature
                    polyStart = feature.indexOf("(", polyEnd)
                }

                // Add the Polygon object to the RenderableLayer on the UI Thread (see onProgressUpdate).
                //publishProgress(polygon)
                //this.numCountriesCreated++
            }
        } catch (e: IOException) {
            Logger.log(Logger.ERROR, "Exception attempting to read/parse world_highways file.")
        } finally {
            WWUtil.closeSilently(reader)
        }
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