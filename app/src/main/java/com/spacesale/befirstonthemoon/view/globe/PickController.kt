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

class PickController(private val context: Context) : BasicWorldWindowController() {

    protected var pickedObjects = ArrayList<Any>()

    /**
     * Assign a subclassed SimpleOnGestureListener to a GestureDetector to handle the "pick" events.
     */
    protected var pickGestureDetector =
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
        val PICK_REGION_SIZE = 40 // pixels

        // Forget our last picked objects
        togglePickedObjectHighlights()
        pickedObjects.clear()

        // Perform a new pick at the screen x, y
        val pickList = worldWindow.pickShapesInRect(
            event.x - PICK_REGION_SIZE / 2,
            event.y - PICK_REGION_SIZE / 2,
            PICK_REGION_SIZE.toFloat(), PICK_REGION_SIZE.toFloat()
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
    fun togglePickedObjectHighlights() {
        var message = ""
        for (pickedObject in pickedObjects) {
            if (pickedObject is Highlightable) {
                val highlightable = pickedObject
                highlightable.isHighlighted = !highlightable.isHighlighted
                if (highlightable.isHighlighted) {
                    if (!message.isEmpty()) {
                        message += ", "
                    }
                    message += (highlightable as Renderable).displayName
                }
            }
        }
        if (!message.isEmpty()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
        this.worldWindow.requestRedraw()
    }

}