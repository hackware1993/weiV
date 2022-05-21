package cn.flutterfirst.weiv.core.extension

import android.widget.LinearLayout
import android.widget.TextView
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam
import cn.flutterfirst.weiv.core.widgets.Widget
import cn.flutterfirst.weiv.wrappers.InternalWidgetDesc
import cn.flutterfirst.weiv.wrappers.linearlayout.weiVFlex
import cn.flutterfirst.weiv.wrappers.textview.weiVText

object ExtensionMgr {
    var extensionMap: HashMap<String, IExtensionCreator<*>> = HashMap()
    var globalWidgetCreateObserver: IGlobalWidgetCreateObserver? = null
    var globalWidgetAttachObserver: IGlobalWidgetAttachObserver? = null

    init {
        registerExtension(InternalWidgetDesc.TEXT, IExtensionCreator {
            val widget = if (it.isNotEmpty()) {
                @KotlinOnly weiVText(
                    it[0] as Key?,
                    it[1] as LayoutParam<*>?,
                    it[2] as String,
                    it[3] as Float,
                    it[4] as Int,
                    it[5]
                )
            } else {
                @JavaOnly weiVText<TextView>()
            }
            return@IExtensionCreator dispatchWidgetCreate(InternalWidgetDesc.TEXT, widget)
        })

        registerExtension(InternalWidgetDesc.FLEX, IExtensionCreator {
            val widget = if (it.isNotEmpty()) {
                @KotlinOnly weiVFlex(
                    it[0] as Key?,
                    it[1] as LayoutParam<*>?,
                    it[2] as ArrayList<Widget<*>>,
                    it[3] as Int,
                    it[4]
                )
            } else {
                @JavaOnly weiVFlex<LinearLayout>()
            }
            return@IExtensionCreator dispatchWidgetCreate(InternalWidgetDesc.FLEX, widget)
        })
    }

    @JvmStatic
    fun dispatchWidgetCreate(type: String, widget: Widget<*>): Widget<*> {
        if (globalWidgetCreateObserver == null) {
            return widget
        }
        return globalWidgetCreateObserver!!.onWidgetCreate(type, widget)
    }

    @JvmStatic
    fun dispatchWidgetAttach(widget: Widget<*>): Widget<*> {
        if (globalWidgetAttachObserver == null) {
            return widget
        }
        return globalWidgetAttachObserver!!.onWidgetAttach(widget)
    }

    @JvmStatic
    fun registerExtension(extensionName: String, creator: IExtensionCreator<*>) {
        extensionMap[extensionName] = creator
    }

    @JvmStatic
    fun <W : Widget<W>> getExtension(extensionName: String): IExtensionCreator<W>? {
        return extensionMap[extensionName] as IExtensionCreator<W>?
    }

    @JvmStatic
    fun createWidget(extensionName: String): Widget<*>? {
        return extensionMap[extensionName]?.createWidget()
    }
}