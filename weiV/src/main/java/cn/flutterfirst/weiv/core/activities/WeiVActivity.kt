package cn.flutterfirst.weiv.core.activities

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.views.WeiVRoot

abstract class WeiVActivity : Activity() {
    lateinit var weiVRoot: WeiVRoot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weiVRoot = WeiVRoot(this)
        weiVRoot.init(build())
        if (containerId() == 0) {
            setContentView(weiVRoot)
        } else {
            val weiVContainer = findViewById<ViewGroup>(containerId())
            weiVContainer.addView(
                weiVRoot,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    abstract fun build(): WeiV

    fun setState(block: () -> Unit) {
        block()
        weiVRoot.update(build())
    }

    open fun containerId(): Int {
        return 0
    }
}