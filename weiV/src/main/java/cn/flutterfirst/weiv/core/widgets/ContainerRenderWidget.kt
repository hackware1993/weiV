package cn.flutterfirst.weiv.core.widgets

import android.view.View
import cn.flutterfirst.weiv.core.elements.ContainerRenderElement
import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.LayoutParam

abstract class ContainerRenderWidget<V : View, W : ContainerRenderWidget<V, W>>(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    childWidgets: ArrayList<Widget<*>>,
    extra: Any? = null,
) : LeafRenderWidget<V, W>(key, layoutParam, childWidgets, extra) {

    override fun createElement(): Element {
        return ContainerRenderElement(this)
    }

    abstract fun processChildLayoutParam(parent: V, child: View, childLayoutParam: LayoutParam<*>?)
}