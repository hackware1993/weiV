package cn.flutterfirst.weiv.core.elements

import android.view.View
import cn.flutterfirst.weiv.core.widgets.Widget

class ContainerRenderElement(override var widget: Widget) : Element(widget) {
    var views: List<View> = ArrayList()

    override fun build(): Widget? {
        return null
    }
}