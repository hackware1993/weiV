package cn.flutterfirst.weiv.core.elements

import android.content.Context
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.widgets.StatefulWidget
import cn.flutterfirst.weiv.core.widgets.Widget

abstract class State<WIDGET : StatefulWidget> {
    lateinit var widget: WIDGET
    lateinit var element: StatefulElement

    open fun initState() {
    }

    open fun didUpdateWidget(newWidget: WIDGET) {
        widget = newWidget
    }

    abstract fun build(): WeiV

    open fun dispose() {
    }

    fun setState(block: () -> Unit) {
        block()
        val weiV = element.state.build()
        assert(weiV.currentWidgetContext.size == 1)
        element.child.update(weiV.currentWidgetContext[0])
    }
}

class StatefulElement(statefulWidget: StatefulWidget) : Element(statefulWidget) {
    lateinit var state: State<StatefulWidget>
    lateinit var child: Element

    override fun mount(context: Context) {
        super.mount(context)
        state = (widget as StatefulWidget).createState() as State<StatefulWidget>
        state.widget = (widget as StatefulWidget)
        state.element = this
        state.initState()
        val weiV = state.build()
        assert(weiV.currentWidgetContext.size == 1)
        child = weiV.currentWidgetContext[0].createElement()
        child.assignParent(this)
        child.mount(context)
    }

    override fun update(newWidget: Widget) {
        super.update(newWidget)
        state.didUpdateWidget(newWidget as StatefulWidget)
        val weiV = state.build()
        assert(weiV.currentWidgetContext.size == 1)
        child.update(weiV.currentWidgetContext[0])
    }

    override fun unmount() {
        super.unmount()
        child.unmount()
        state.dispose()
    }
}