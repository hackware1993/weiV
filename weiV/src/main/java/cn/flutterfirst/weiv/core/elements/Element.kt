package cn.flutterfirst.weiv.core.elements

import android.content.Context
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.widgets.Widget

abstract class Element(var widget: Widget<*>) {
    var parent: Element? = null

    init {
        widget = ExtensionMgr.dispatchWidgetAttach(widget)
    }

    fun assignParent(element: Element) {
        parent = element
    }

    // The context of the current activity
    lateinit var context: Context

    open fun mount(context: Context) {
        this.context = context
    }

    open fun update(newWidget: Widget<*>) {
        widget = ExtensionMgr.dispatchWidgetAttach(newWidget)
    }

    open fun unmount() {
    }
}