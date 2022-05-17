package cn.flutterfirst.weiv.core

import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.elements.State
import cn.flutterfirst.weiv.core.elements.StateElement
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.widgets.Widget

abstract class StatefulWidget<T : Widget>(key: Key? = null) : Widget(key) {
    abstract fun createState(): State<T>
    final override fun createElement(): Element = StateElement(this)
}

class MyWidget : StatefulWidget<MyWidget>() {
    override fun createState(): State<MyWidget> = MyWidgetState()
}

class MyWidgetState : State<MyWidget>() {
    override fun build(): Widget {
        TODO("Not yet implemented")
    }
}

