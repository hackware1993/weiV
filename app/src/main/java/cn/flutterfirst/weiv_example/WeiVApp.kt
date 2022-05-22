package cn.flutterfirst.weiv_example

import android.app.Application
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.IExtensionCreator
import cn.flutterfirst.weiv_example.ext.weiVButton

class WeiVApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ExtensionMgr.registerExtension("Button", IExtensionCreator {
            val widget = weiVButton()
            return@IExtensionCreator ExtensionMgr.dispatchWidgetCreate(
                "Button",
                widget
            )
        })
    }
}