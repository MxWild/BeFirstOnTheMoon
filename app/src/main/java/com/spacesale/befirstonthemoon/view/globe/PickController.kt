package com.spacesale.befirstonthemoon.view.globe

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.widget.Toast
import gov.nasa.worldwind.BasicWorldWindowController
import gov.nasa.worldwind.render.Renderable
import gov.nasa.worldwind.shape.Highlightable
import java.util.*

open class PickController(private val context: Context) : BasicWorldWindowController() {

    private var pickedObjects = ArrayList<Any>()

    /**
     * Assign a subclassed SimpleOnGestureListener to a GestureDetector to handle the "pick" events.
     */
    private var pickGestureDetector =
        GestureDetector(context, object : SimpleOnGestureListener() {
            override fun onSingleTapUp(event: MotionEvent): Boolean {
                pick(event) // Pick the object(s) at the tap location
                return true
            }
        })

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var consumed = super.onTouchEvent(event)
        if (!consumed) {
            consumed = pickGestureDetector.onTouchEvent(event)
        }
        return consumed
    }

    /**
     * Performs a pick at the tap location.
     */
    fun pick(event: MotionEvent) {
        val pickRegionSize = 40 // pixels

        // Forget our last picked objects
        togglePickedObjectHighlights()
        pickedObjects.clear()

        // Perform a new pick at the screen x, y
        val pickList = worldWindow.pickShapesInRect(
            event.x - pickRegionSize / 2,
            event.y - pickRegionSize / 2,
            pickRegionSize.toFloat(), pickRegionSize.toFloat()
        )

        // pickShapesInRect can return multiple objects, i.e., they're may be more that one 'top object'
        // So we iterate through the list instead of calling pickList.topPickedObject which returns the
        // arbitrary 'first' top object.
        for (i in 0 until pickList.count()) {
            if (pickList.pickedObjectAt(i).isOnTop) {
                pickedObjects.add(pickList.pickedObjectAt(i).userObject)
            }
        }
        togglePickedObjectHighlights()
    }

    /**
     * Toggles the highlighted state of a picked object.
     */
    private fun togglePickedObjectHighlights() {
        var message = ""
        for (pickedObject in pickedObjects) {
            if (pickedObject is Highlightable) {
                pickedObject.isHighlighted = !pickedObject.isHighlighted
                if (pickedObject.isHighlighted) {
                    if (message.isNotEmpty()) {
                        message += ", "
                    }
                    message += (pickedObject as Renderable).displayName
                }
            }
        }
        if (message.isNotEmpty()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            polyId = message.split(" ")[1].toInt()
        }
        this.worldWindow.requestRedraw()
    }

}