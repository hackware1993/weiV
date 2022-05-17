package cn.flutterfirst.weiv.core.widgets

import cn.flutterfirst.weiv.core.elements.Element
import cn.flutterfirst.weiv.core.keys.Key

abstract class Widget(open var key: Key? = null) {
    abstract fun createElement(): Element
}