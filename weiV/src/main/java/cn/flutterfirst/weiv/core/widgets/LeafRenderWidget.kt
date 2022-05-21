package cn.flutterfirst.weiv.core.widgets

import android.content.Context
import android.view.View
import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.elements.LeafRenderElement
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.LayoutParam

abstract class LeafRenderWidget<VIEW : View, WIDGET : LeafRenderWidget<VIEW, WIDGET>>(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    childWidgets: ArrayList<Widget<*>>? = null,
    extra: Any? = null
) :
    Widget<WIDGET>(key, layoutParam, childWidgets, extra) {

    override fun createElement(): Element {
        return LeafRenderElement(this)
    }

    fun createViewInstance(context: Context): VIEW {
        val view = createView(context)
        if (autoDoParameter()) {
            return doParameter(view, true)
        }
        return view
    }

    abstract fun createView(context: Context): VIEW

    abstract fun doParameter(view: VIEW, first: Boolean): VIEW

    open fun updateView(view: VIEW) {
        if (autoDoParameter()) {
            doParameter(view, false)
        }
    }

    open fun autoDoParameter(): Boolean {
        return true
    }
}