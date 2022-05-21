package cn.flutterfirst.weiv.core.elements

import android.content.Context
import android.view.View
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget

open class LeafRenderElement<V : View, W : LeafRenderWidget<V, W>>(
    widget: LeafRenderWidget<V, W>,
) : Element(widget) {
    lateinit var view: V

    override fun mount(context: Context) {
        super.mount(context)
        view = (widget as LeafRenderWidget<V, W>).createViewInstance(context)
    }

    override fun update(newWidget: Widget<*>) {
        super.update(newWidget)
        (widget as LeafRenderWidget<V, W>).updateView(view)
    }
}