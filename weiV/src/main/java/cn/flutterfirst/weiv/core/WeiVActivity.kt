package cn.flutterfirst.weiv.core

import android.app.Activity
import android.os.Bundle
import cn.flutterfirst.weiv.core.widgets.Widget

abstract class WeiVActivity : Activity() {
    private lateinit var weiVRoot: WeiVRoot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weiVRoot = WeiVRoot(this)
        weiVRoot.init(build())
        setContentView(weiVRoot)
    }

    abstract fun build(): Widget

    fun setState(block: () -> Unit) {
        block()
        weiVRoot.update(build())
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}