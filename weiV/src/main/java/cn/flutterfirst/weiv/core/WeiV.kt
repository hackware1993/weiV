package cn.flutterfirst.weiv.core

import android.util.Log
import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget

val weiVDummyWidget = WeiV {
}

class WeiVElement : Element(weiVDummyWidget) {
    override fun build(): Widget? {
        return null
    }
}

open class WeiV(block: WeiV.(weiV: WeiV) -> Unit) : Widget() {
    open var currentWidgetContext = ArrayList<Widget>()

    init {
        block(this)
        printWidgetTree()
    }

    private fun printWidgetTree() {
        iteratorWidgetTree(currentWidgetContext) {
            Log.d("weiVWidgetTree", "tree item = $it")
        };
    }

    private fun iteratorWidgetTree(widgets: ArrayList<Widget>, filter: (widget: Widget) -> Unit) {
        widgets.forEach {
            filter.invoke(it)
            if (it is ContainerRenderWidget<*>) {
                iteratorWidgetTree(it.childWidgets, filter)
            }
        }
    }

    fun addLeafRenderWidget(widget: LeafRenderWidget<*>) {
        currentWidgetContext.add(widget)
    }

    fun addContainerRenderWidget(
        widget: ContainerRenderWidget<*>,
        block: WeiV.(widget: ContainerRenderWidget<*>) -> Unit
    ) {
        currentWidgetContext.add(widget)
        val temp = currentWidgetContext
        widget.childWidgets = ArrayList<Widget>()
        currentWidgetContext = widget.childWidgets
        block(widget)
        currentWidgetContext = temp
    }

    override fun createElement(): Element {
        return WeiVElement()
    }
}