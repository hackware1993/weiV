package cn.flutterfirst.weiv.wrappers.linearlayout

import cn.flutterfirst.weiv.core.others.LayoutParam

class FlexLayoutParam @JvmOverloads constructor(
    width: Int = WRAP_CONTENT,
    height: Int = WRAP_CONTENT
) : LayoutParam<FlexLayoutParam>(width, height)