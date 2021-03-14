package com.spacesale.befirstonthemoon.view.globe

import gov.nasa.worldwind.WorldWind
import gov.nasa.worldwind.geom.Position
import gov.nasa.worldwind.shape.Polygon
import gov.nasa.worldwind.shape.ShapeAttributes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PolygonConverter {

    val scope = CoroutineScope(Job() + Dispatchers.IO)

    fun converterDbToPolygons(countryCoordinates: List<String>): List<Polygon> {

        val polygons: MutableList<Polygon> = emptyList<Polygon>().toMutableList()
        scope.launch {
            countryCoordinates.forEach { stringFromTable ->
                val converterStringToPolygons = converterStringToPolygons(stringFromTable)
                polygons.add(converterStringToPolygons)
            }
        }

        return polygons
    }

    private fun converterStringToPolygons(oneCountry: String): Polygon {

        val positions: MutableList<Position> = emptyList<Position>().toMutableList()

        oneCountry.replace(WKT_START, "")
            .replace(WKT_END,"")
            .split(",").forEach {coordinates ->
                val split = coordinates.split(" ")
                positions.add(Position.fromDegrees(split[0].toDouble(), split[1].toDouble(), 0.0))
            }
        val poly = Polygon(positions)

        // Define the normal shape attributes
        val commonAttrs = ShapeAttributes()
        commonAttrs.interiorColor[1.0f, 1.0f, 0.0f] = 0.05f
        commonAttrs.outlineColor[0.0f, 0.0f, 0.0f] = 1.0f
        commonAttrs.outlineWidth = 3f

        // Define the shape attributes used for highlighted countries
        val highlightAttrs = ShapeAttributes()
        highlightAttrs.interiorColor[1.0f, 1.0f, 1.0f] = 0.2f
        highlightAttrs.outlineColor[1.0f, 1.0f, 1.0f] = 1.0f
        highlightAttrs.outlineWidth = 5f

        poly.altitudeMode = WorldWind.CLAMP_TO_GROUND
        poly.isFollowTerrain = true
        poly.pathType = WorldWind.LINEAR
        poly.attributes = ShapeAttributes(commonAttrs)
        poly.highlightAttributes = highlightAttrs

        return poly
    }

    companion object {
        const val WKT_START = "POLYGON (("
        const val WKT_END = "))"
    }
}