package cn.flutterfirst.weiv.core.views

import android.content.Context

abstract class WeiVView(context: Context) : WeiVRoot(context), IWeiVRootHolder {

    init {
        weiVRootHolder = this
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        initIfNeeded(build(buildCount))
    }

    fun setState(block: () -> Unit) {
        block()
        update(build(buildCount))
    }
}