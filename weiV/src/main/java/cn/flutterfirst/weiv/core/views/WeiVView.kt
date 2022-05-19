package cn.flutterfirst.weiv.core.views

import android.content.Context
import cn.flutterfirst.weiv.core.WeiV

abstract class WeiVView(context: Context) : WeiVRoot(context) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        init(build())
    }

    fun setState(block: () -> Unit) {
        block()
        update(build())
    }

    abstract fun build(): WeiV
}