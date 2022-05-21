package cn.flutterfirst.weiv.core.activities

import android.app.Activity
import android.view.ViewGroup
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.views.WeiVRoot

abstract class WeiVActivity : Activity() {
    lateinit var weiVRoot: WeiVRoot
    var firstResume = true

    override fun onResume() {
        if (firstResume) {
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
            firstResume = false
        }
        super.onResume()
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