package cn.flutterfirst.weiv.core.activities

import android.app.Activity
import android.view.ViewGroup
import cn.flutterfirst.weiv.core.views.IWeiVRootHolder
import cn.flutterfirst.weiv.core.views.WeiVRoot

abstract class WeiVActivity : Activity(), IWeiVRootHolder {
    lateinit var weiVRoot: WeiVRoot
    var firstResume = true

    override fun onResume() {
        if (firstResume) {
            weiVRoot = WeiVRoot(this)
            weiVRoot.weiVRootHolder = this
            weiVRoot.init(build(0))
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

    fun setState(block: () -> Unit) {
        block()
        weiVRoot.update(build(weiVRoot.buildCount))
    }

    open fun containerId(): Int {
        return 0
    }
}