package cn.flutterfirst.weiv.core.widgets

import android.content.Context
import android.view.View
import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.elements.LeafRenderElement
import cn.flutterfirst.weiv.core.keys.Key

abstract class LeafRenderWidget<T : View>(override var key: Key? = null) : Widget(key) {

    override fun createElement(): Element {
        return LeafRenderElement(this)
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