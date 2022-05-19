package cn.flutterfirst.weiv.core.widgets

import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.elements.State
import cn.flutterfirst.weiv.core.elements.StatefulElement
import cn.flutterfirst.weiv.core.keys.Key

abstract class StatefulWidget(key: Key? = null) : Widget(key) {
    abstract fun createState(): State<*>
    final override fun createElement(): Element = StatefulElement(this)
}

