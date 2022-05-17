package cn.flutterfirst.weiv.core

import android.util.Log
import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.widgets.RenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget
import weiVFlex
import weiVText

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
            if (it is RenderWidget<*>) {
                iteratorWidgetTree(it.childWidgets, filter)
            }
        }
    }

    override fun createElement(): Element {
        return WeiVElement()
    }

    fun Text(
        key: Key? = null,
        text: String? = null,
        textSize: Float? = null,
        textColor: Int? = null
    ) {
        currentWidgetContext.add(
            weiVText(
                key = key,
                text = text,
                textSize = textSize,
                textColor = textColor
            )
        )
    }

    fun Flex(
        key: Key? = null,
        orientation: Int? = null,
        block: WeiV.(widget: weiVFlex) -> Unit
    ) {
        val flex = weiVFlex(key = key, orientation = orientation)
        currentWidgetContext.add(flex)
        val temp = currentWidgetContext
        flex.childWidgets = ArrayList<Widget>()
        currentWidgetContext = flex.childWidgets
        block(flex)
        currentWidgetContext = temp
    }
}