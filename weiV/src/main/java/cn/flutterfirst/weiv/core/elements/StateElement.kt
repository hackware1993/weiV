package cn.flutterfirst.weiv.core.elements

import cn.flutterfirst.weiv.core.StatefulWidget
import cn.flutterfirst.weiv.core.widgets.Widget

abstract class State<T : Widget> {
    lateinit var widget: T
    lateinit var element: StateElement

    open fun initState() {
    }

    abstract fun build(): Widget

    open fun dispose() {
    }

    fun setState(block: () -> Unit) {
        block()
        element.update(build())
    }
}

class StateElement(override var widget: Widget) : Element(widget) {
    lateinit var state: State<Widget>

    override fun mount() {
        super.mount()
        state = (widget as StatefulWidget<Widget>).createState()
        state.widget = widget
        state.element = this
        state.initState()
    }

    override fun build(): Widget {
        TODO("Not yet implemented")
    }
}