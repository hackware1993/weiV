package cn.flutterfirst.weiv.core.views

import android.content.Context

abstract class WeiVView(context: Context) : WeiVRoot(context), IWeiVRootHolder {

    init {
        weiVRootHolder = this
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        init(build(0))
    }

    fun setState(block: () -> Unit) {
        block()
        update(build(buildCount))
    }
}