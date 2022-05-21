package cn.flutterfirst.weiv_example

import cn.flutterfirst.weiv.core.views.WeiVRoot

object SkinManager {
    @JvmStatic
    var isLight = true

    @JvmStatic
    fun changeSkin() {
        isLight = !isLight
        WeiVRoot.buildAll()
    }
}