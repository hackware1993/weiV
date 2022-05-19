package cn.flutterfirst.weiv.core

import android.widget.LinearLayout
import android.widget.TextView
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.IExtensionCreator
import cn.flutterfirst.weiv.core.others.Build
import cn.flutterfirst.weiv.core.others.BuildWithContext
import cn.flutterfirst.weiv.wrappers.InternalWidgetDesc
import cn.flutterfirst.weiv.wrappers.linearlayout.weiVFlex
import cn.flutterfirst.weiv.wrappers.textview.weiVText

abstract class WeiVJavaActivity : WeiVActivity() {
    var weiV: WeiV? = null
    var textViewCreator: IExtensionCreator<weiVText<TextView>>? = null
    var flexCreator: IExtensionCreator<weiVFlex<LinearLayout>>? = null

    fun WeiV(build: Build): WeiV {
        val temp = weiV
        val weiV = WeiV()
        this.weiV = weiV
        build.build()
        this.weiV = temp
        return weiV
    }

    open fun Text(): weiVText<TextView> {
        if (textViewCreator == null) {
            textViewCreator = ExtensionMgr.getExtension(InternalWidgetDesc.TEXT)
        }
        val weiVText = textViewCreator!!.createWidget()
        weiV!!.addLeafRenderWidget(weiVText)
        return weiVText
    }

    open fun Flex(build: BuildWithContext<weiVFlex<LinearLayout>>): weiVFlex<LinearLayout> {
        if (flexCreator == null) {
            flexCreator = ExtensionMgr.getExtension(InternalWidgetDesc.FLEX)
        }
        val weiVFlex = flexCreator!!.createWidget()
        weiV!!.addContainerRenderWidget(weiVFlex) {
            build.build(weiVFlex)
        }
        return weiVFlex
    }

    open fun Flex(build: Build): weiVFlex<LinearLayout> {
        if (flexCreator == null) {
            flexCreator = ExtensionMgr.getExtension(InternalWidgetDesc.FLEX)
        }
        val weiVFlex = flexCreator!!.createWidget()
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