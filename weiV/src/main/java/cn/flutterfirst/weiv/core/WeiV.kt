package cn.flutterfirst.weiv.core

import android.util.Log
import cn.flutterfirst.weiv.BuildConfig
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget

open class WeiV {
    var currentWidgetContext = ArrayList<Widget>()

    @JavaOnly
    constructor()

    @KotlinOnly
    constructor(block: WeiV.(weiV: WeiV) -> Unit) {
        block(this)
        if (BuildConfig.DEBUG) {
            printWidgetTree()
        }
    }

    private fun printWidgetTree() {
        iteratorWidgetTree(0, currentWidgetContext) { widget, level ->
            Log.d("weiVWidgetTree", "-------".repeat(level) + "$widget")
        }
    }

    private fun iteratorWidgetTree(
        level: Int = 0,
        widgets: ArrayList<Widget>,
        filter: (widget: Widget, level: Int) -> Unit
    ) {
        widgets.forEach {
            filter.invoke(it, level)
            if (it is ContainerRenderWidget<*>) {
                iteratorWidgetTree(level + 1, it.childWidgets!!, filter)
            }
        }
    }

    fun <WIDGET : Widget> addLeafRenderWidget(widget: WIDGET): WIDGET {
        currentWidgetContext.add(widget)
        return widget
    }

    fun <WIDGET : ContainerRenderWidget<VIEW_GROUP>, VIEW_GROUP> addContainerRenderWidget(
        widget: WIDGET,
        block: WeiV.(widget: WIDGET) -> Unit
    ) {
        currentWidgetContext.add(widget)
        val temp = currentWidgetContext
        currentWidgetContext = widget.childWidgets!!
        block(widget)
        currentWidgetContext = temp
    }
}