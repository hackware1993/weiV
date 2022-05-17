package cn.flutterfirst.weiv.core.widgets

import android.content.Context
import android.view.View
import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.elements.RenderElement
import cn.flutterfirst.weiv.core.keys.Key

abstract class RenderWidget<T : View>(key: Key? = null) : Widget(key) {
    open var childWidgets = ArrayList<Widget>()

    override fun createElement(): Element {
        return RenderElement(this)
    }

    fun createViewInstance(context: Context): T {
        val view = createView(context)
        if (autoDoParameter()) {
            return doParameter(view, true)
        }
        return view;
    }

    abstract fun createView(context: Context): T

    abstract fun doParameter(view: T, first: Boolean): T

    open fun updateView(view: T) {
        if (autoDoParameter()) {
            doParameter(view, false)
        }
    }

    open fun autoDoParameter(): Boolean {
        return true
    }
}