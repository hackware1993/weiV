package cn.flutterfirst.weiv.core.extension

import android.widget.LinearLayout
import android.widget.TextView
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget
import cn.flutterfirst.weiv.wrappers.InternalWidgetDesc
import cn.flutterfirst.weiv.wrappers.linearlayout.weiVFlex
import cn.flutterfirst.weiv.wrappers.textview.weiVText

object ExtensionMgr {
    private val extensionMap: HashMap<String, IExtensionCreator<*>> = HashMap()

    init {
        registerExtension(InternalWidgetDesc.TEXT, IExtensionCreator {
            if (it.isNotEmpty()) {
                return@IExtensionCreator @KotlinOnly weiVText<TextView>(
                    it[0] as Key?,
                    it[1] as String,
                    it[2] as Float,
                    it[3] as Int
                )
            } else {
                return@IExtensionCreator @JavaOnly weiVText<TextView>()
            }
        })

        registerExtension(InternalWidgetDesc.FLEX, IExtensionCreator {
            if (it.isNotEmpty()) {
                return@IExtensionCreator @KotlinOnly weiVFlex<LinearLayout>(
                    it[0] as Key?,
                    it[1] as ArrayList<Widget>,
                    it[2] as Int
                )
            } else {
                return@IExtensionCreator @JavaOnly weiVFlex<LinearLayout>()
            }
        })
    }

    @JvmStatic
    fun registerExtension(extensionName: String, creator: IExtensionCreator<*>) {
        extensionMap[extensionName] = creator
    }

    @JvmStatic
    fun <T : LeafRenderWidget<*>> getExtension(extensionName: String): IExtensionCreator<T> {
        return extensionMap[extensionName] as IExtensionCreator<T>
    }
}