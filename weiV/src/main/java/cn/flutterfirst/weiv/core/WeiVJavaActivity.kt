package cn.flutterfirst.weiv.core

import cn.flutterfirst.weiv.core.others.Build
import cn.flutterfirst.weiv.core.others.BuildWithContext
import cn.flutterfirst.weiv.wrappers.linearlayout.weiVFlex
import cn.flutterfirst.weiv.wrappers.textview.weiVText

abstract class WeiVJavaActivity : WeiVActivity() {
    var weiV: WeiV? = null

    fun WeiV(build: Build): WeiV {
        val temp = weiV
        val weiV = WeiV()
        this.weiV = weiV
        build.build()
        this.weiV = temp
        return weiV
    }

    open fun Text(): weiVText? {
        val weiVText = weiVText()
        weiV!!.addLeafRenderWidget(weiVText)
        return weiVText
    }

    open fun Flex(build: BuildWithContext<weiVFlex>): weiVFlex? {
        val weiVFlex = weiVFlex()
        weiV!!.addContainerRenderWidget(weiVFlex) {
            build.build(weiVFlex)
        }
        return weiVFlex
    }

    open fun Flex(build: Build): weiVFlex? {
        val weiVFlex = weiVFlex()
        weiV!!.addContainerRenderWidget(weiVFlex) {
            build.build()
        }
        return weiVFlex
    }

    fun setState(build: Build) {
        build.build()
        weiVRoot.update(build())
    }
}