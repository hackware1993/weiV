package cn.flutterfirst.weiv.core.views

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.others.Build
import cn.flutterfirst.weiv.core.others.BuildWithContext
import cn.flutterfirst.weiv.core.others.WeiVJavaHelper
import cn.flutterfirst.weiv.core.widgets.StatefulWidget
import cn.flutterfirst.weiv.wrappers.linearlayout.weiVFlex
import cn.flutterfirst.weiv.wrappers.textview.weiVText

abstract class WeiVJavaView(context: Context) : WeiVView(context) {
    val weiVJavaHelper = WeiVJavaHelper()

    fun WeiV(build: Build): WeiV {
        return weiVJavaHelper.createWeiV(build)
    }

    open fun Text(): weiVText<TextView> {
        return weiVJavaHelper.createText()
    }

    open fun Flex(build: BuildWithContext<weiVFlex<LinearLayout>>): weiVFlex<LinearLayout> {
        return weiVJavaHelper.createFlex(build)
    }

    open fun Flex(build: Build): weiVFlex<LinearLayout> {
        return weiVJavaHelper.createFlex(build)
    }

    open fun Stateful(): StatefulWidget {
        return weiVJavaHelper.createStateful()
    }

    fun setState(build: Build) {
        build.build()
        update(build())
    }
}