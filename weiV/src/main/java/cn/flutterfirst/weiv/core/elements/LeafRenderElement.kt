package cn.flutterfirst.weiv.core.elements

import android.view.View
import cn.flutterfirst.weiv.core.widgets.Widget

class LeafRenderElement(override var widget: Widget) : Element(widget) {
    lateinit var view: View

    // No need to create widgets to build child elements
    override fun build(): Widget? {
        return null
    }
}