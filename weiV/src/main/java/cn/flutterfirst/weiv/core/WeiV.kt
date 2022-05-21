package cn.flutterfirst.weiv.core

import android.util.Log
import cn.flutterfirst.weiv.BuildConfig
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget

open class WeiV {
    var currentWidgetContext = ArrayList<Widget<*>>()

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
            Log.d("weiVWidgetTree", "----".repeat(level) + "$widget")
        }
    }

    private fun iteratorWidgetTree(
        level: Int = 0,
        widgets: ArrayList<Widget<*>>,
        filter: (widget: Widget<*>, level: Int) -> Unit
    ) {
        widgets.forEach {
            filter.invoke(it, level)
            if (it is ContainerRenderWidget<*, *>) {
                iteratorWidgetTree(level + 1, it.childWidgets!!, filter)
            }
        }
    }

    fun <W : Widget<*>> addLeafRenderWidget(widget: W): W {
        currentWidgetContext.add(widget)
        return widget
    }

    fun <W : Widget<*>> addContainerRenderWidget(
        widget: W,
        block: WeiV.(widget: W) -> Unit
    ): W {
        currentWidgetContext.add(widget)
        val temp = currentWidgetContext
        currentWidgetContext = widget.childWidgets!!
        block(widget)
        currentWidgetContext = temp
        return widget
    }

    fun merge(block: () -> WeiV) {
        currentWidgetContext.addAll(block().currentWidgetContext)
    }

    fun merge(weiV: WeiV) {
        currentWidgetContext.addAll(weiV.currentWidgetContext)
    }
}