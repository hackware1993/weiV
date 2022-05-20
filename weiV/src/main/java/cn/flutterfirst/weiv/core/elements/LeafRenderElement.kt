package cn.flutterfirst.weiv.core.elements

import android.content.Context
import android.view.View
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget

open class LeafRenderElement<VIEW : View, WIDGET : LeafRenderWidget<VIEW, WIDGET>>(
    widget: LeafRenderWidget<VIEW, WIDGET>,
) : Element(widget) {
    lateinit var view: VIEW

    override fun mount(context: Context) {
        super.mount(context)
        view = (widget as LeafRenderWidget<VIEW, WIDGET>).createViewInstance(context)
    }

    override fun update(newWidget: Widget<*>) {
        super.update(newWidget)
        (newWidget as LeafRenderWidget<VIEW, WIDGET>).updateView(view)
    }
}