package cn.flutterfirst.weiv.core

import android.util.Log
import cn.flutterfirst.weiv.BuildConfig
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.widgets.ContainerRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget

open class WeiV {
    var currentWidgetContext = ArrayList<Widget<*>>()

    companion object {
        var globalWidgetContext: ArrayList<Widget<*>>? = null
    }

    @JavaOnly
    constructor()

    @KotlinOnly
    constructor(block: WeiV.(weiV: WeiV) -> Unit) {
        block(this)
        if (BuildConfig.DEBUG) {
            printWidgetTree()
        }
    }

    fun printWidgetTree() {
        iteratorWidgetTree(0, currentWidgetContext) { widget, level ->
            Log.d("weiVWidgetTree", "----".repeat(level) + "$widget")
        }
    }

    fun iteratorWidgetTree(
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
        if (globalWidgetContext != null) {
            globalWidgetContext?.add(widget)
        } else {
            currentWidgetContext.add(widget)
        }
        return widget
    }

    fun <W : Widget<*>> addContainerRenderWidget(
        widget: W,
        block: WeiV.(widget: W) -> Unit
    ): W {
        if (globalWidgetContext != null) {
            globalWidgetContext?.add(widget)
            val temp = globalWidgetContext
            globalWidgetContext = widget.childWidgets!!
            block(widget)
            globalWidgetContext = temp
        } else {
            currentWidgetContext.add(widget)
            val temp = currentWidgetContext
            currentWidgetContext = widget.childWidgets!!
            block(widget)
            currentWidgetContext = temp
        }
        return widget
    }

    fun merge(block: () -> WeiV) {
        if (globalWidgetContext != null) {
            globalWidgetContext?.addAll(block().currentWidgetContext)
        } else {
            currentWidgetContext.addAll(block().currentWidgetContext)
        }
    }

    fun merge(weiV: WeiV) {
        if (globalWidgetContext != null) {
            globalWidgetContext?.addAll(weiV.currentWidgetContext)
        } else {
            currentWidgetContext.addAll(weiV.currentWidgetContext)
        }
    }
}