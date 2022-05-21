package cn.flutterfirst.weiv.core.widgets

import android.content.Context
import android.view.View
import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.elements.LeafRenderElement
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.LayoutParam

abstract class LeafRenderWidget<V : View, W : LeafRenderWidget<V, W>>(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    childWidgets: ArrayList<Widget<*>>? = null,
    extra: Any? = null
) :
    Widget<W>(key, layoutParam, childWidgets, extra) {

    override fun createElement(): Element {
        return LeafRenderElement(this)
    }

    fun createViewInstance(context: Context): V {
        val view = createView(context)
        if (autoDoParameter()) {
            return doParameter(view, true)
        }
        return view
    }

    abstract fun createView(context: Context): V

    abstract fun doParameter(view: V, first: Boolean): V

    open fun updateView(view: V) {
        if (autoDoParameter()) {
            doParameter(view, false)
        }
    }

    open fun autoDoParameter(): Boolean {
        return true
    }
}