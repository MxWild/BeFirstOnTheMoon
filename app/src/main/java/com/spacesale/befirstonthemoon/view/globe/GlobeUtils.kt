package com.spacesale.befirstonthemoon.view.globe

import gov.nasa.worldwind.WorldWind
import gov.nasa.worldwind.geom.Position
import gov.nasa.worldwind.shape.Polygon
import gov.nasa.worldwind.shape.ShapeAttributes

class GlobeUtils {

    private fun mainColor(): ShapeAttributes {
        val commonAttrs = ShapeAttributes()
        commonAttrs.interiorColor[1.0f, 1.0f, 0.0f] = 0.2f
        commonAttrs.outlineColor[0.0f, 0.0f, 0.0f] = 1.0f
        commonAttrs.outlineWidth = 3f
        return commonAttrs
    }

    fun selectColor(): ShapeAttributes {
        val highlightAttrs = ShapeAttributes()
        highlightAttrs.interiorColor[0.0f, 1.0f, 0.0f] = 0.2f
        highlightAttrs.outlineColor[1.0f, 1.0f, 1.0f] = 1.0f
        highlightAttrs.outlineWidth = 5f
        return highlightAttrs
    }

    fun getAllPolygons(): List<Polygon> {

        val allPolygons: MutableList<Polygon> = emptyList<Polygon>().toMutableList()

        val positions1 = listOf<Position>(
            Position.fromDegrees(40.0, -105.0, 0.0),
            Position.fromDegrees(45.0, -110.0, 0.0),
            Position.fromDegrees(50.0, -100.0, 0.0),
            Position.fromDegrees(45.0, -90.0, 0.0),
            Position.fromDegrees(40.0, -95.0, 0.0)
        )

        val positions2 = listOf<Position>(
            Position.fromDegrees(40.0, -135.0, 0.0),
            Position.fromDegrees(45.0, -140.0, 0.0),
            Position.fromDegrees(50.0, -130.0, 0.0),
            Position.fromDegrees(45.0, -130.0, 0.0),
            Position.fromDegrees(40.0, -125.0, 0.0)
        )

        val positions3 = listOf<Position>(
            Position.fromDegrees(20.0, -135.0, 0.0),
            Position.fromDegrees(25.0, -140.0, 0.0),
            Position.fromDegrees(30.0, -130.0, 0.0),
            Position.fromDegrees(25.0, -120.0, 0.0),
            Position.fromDegrees(20.0, -125.0, 0.0)
        )

        val positions4 = listOf<Position>(
            Position.fromDegrees(0.0, -135.0, 0.0),
            Position.fromDegrees(5.0, -140.0, 0.0),
            Position.fromDegrees(10.0, -130.0, 0.0),
            Position.fromDegrees(5.0, -120.0, 0.0),
            Position.fromDegrees(0.0, -125.0, 0.0)
        )

        val positions5 = listOf<Position>(
            Position.fromDegrees(0.0, -105.0, 0.0),
            Position.fromDegrees(5.0, -110.0, 0.0),
            Position.fromDegrees(10.0, -110.0, 0.0),
            Position.fromDegrees(5.0, -90.0, 0.0),
            Position.fromDegrees(0.0, -95.0, 0.0)
        )

        val positions6 = listOf<Position>(
            Position.fromDegrees(59.9, -105.34, 0.0),
            Position.fromDegrees(64.0, -110.57, 0.0),
            Position.fromDegrees(69.0, -110.61, 0.0),
            Position.fromDegrees(64.7, -90.0, 0.0),
            Position.fromDegrees(59.7, -95.0, 0.0),
        )

        val positions7 = listOf<Position>(
            Position.fromDegrees(159.9, -105.34, 0.0),
            Position.fromDegrees(164.0, -110.57, 0.0),
            Position.fromDegrees(169.0, -110.61, 0.0),
            Position.fromDegrees(164.7, -90.0, 0.0),
            Position.fromDegrees(159.7, -95.0, 0.0),
        )

        val poly1 = getPolygonAttr(positions1, "Sector 1 10_000_000")
        val poly2 = getPolygonAttr(positions2, "Sector 2 18_000_000")
        val poly3 = getPolygonAttr(positions3, "Sector 3 9_999_009")
        val poly4 = getPolygonAttr(positions4, "Sector 4 25_000_025")
        val poly5 = getPolygonAttr(positions5, "Sector 5 17_500_000")
        val poly6 = getPolygonAttr(positions6, "Sector 6 14_450_000")
        val poly7 = getPolygonAttr(positions7, "Sector 7 13_200_100")

        allPolygons.add(poly1)
        allPolygons.add(poly2)
        allPolygons.add(poly3)
        allPolygons.add(poly4)
        allPolygons.add(poly5)
        allPolygons.add(poly6)
        allPolygons.add(poly7)

        return allPolygons
    }

    private fun getPolygonAttr(positions: List<Position>, sectorName: String): Polygon {
        val poly = Polygon(positions)
        poly.altitudeMode = WorldWind.CLAMP_TO_GROUND
        poly.isFollowTerrain = true
        poly.pathType = WorldWind.LINEAR
        poly.attributes = ShapeAttributes(mainColor())
        poly.highlightAttributes = selectColor()
        poly.displayName = sectorName
        return poly
    }
}