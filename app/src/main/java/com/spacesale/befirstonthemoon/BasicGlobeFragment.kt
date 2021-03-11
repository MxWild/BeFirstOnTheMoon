package com.spacesale.befirstonthemoon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import gov.nasa.worldwind.WorldWindow
import gov.nasa.worldwind.layer.BackgroundLayer
import gov.nasa.worldwind.layer.BlueMarbleLandsatLayer
import gov.nasa.worldwind.render.ImageSource

class BasicGlobeFragment : Fragment() {

    private lateinit var wwd: WorldWindow

    fun createWorldWindow(): WorldWindow {
        val moon = ImageSource.fromResource(R.drawable.moon)
        this.wwd = WorldWindow(context)
        this.wwd.layers.addLayer(BackgroundLayer(moon, null))
//        this.wwd.layers.addLayer(BlueMarbleLandsatLayer())

        return wwd
    }

    fun getWorldWindow(): WorldWindow {
        return this.wwd
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView  = inflater.inflate(R.layout.fragment_globe, container, false)
        val globeLayout : FrameLayout = rootView.findViewById (R.id.globe)

        globeLayout.addView(this.createWorldWindow())

        return rootView
    }

    override fun onResume() {
        super.onResume()
        this.wwd.onResume()
    }

    override fun onPause() {
        super.onPause()
        this.wwd.onPause()
    }

    companion object {
        fun newInstance() = BasicGlobeFragment()
    }
}