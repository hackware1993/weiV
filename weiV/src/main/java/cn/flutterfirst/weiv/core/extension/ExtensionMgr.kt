package cn.flutterfirst.weiv.core.extension

import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam
import cn.flutterfirst.weiv.core.widgets.Widget
import cn.flutterfirst.weiv.wrappers.InternalWidgetDesc
import cn.flutterfirst.weiv.wrappers.constraintlayout.weiVConstraintLayout
import cn.flutterfirst.weiv.wrappers.linearlayout.weiVFlex
import cn.flutterfirst.weiv.wrappers.recyclerview.weiVListView
import cn.flutterfirst.weiv.wrappers.textview.weiVText

object ExtensionMgr {
    var extensionMap: HashMap<String, IExtensionCreator<*>> = HashMap()
    var globalWidgetCreateObserver: IGlobalWidgetCreateObserver? = null
    var globalWidgetAttachObserver: IGlobalWidgetAttachObserver? = null

    init {
        registerExtension(InternalWidgetDesc.TEXT, IExtensionCreator {
            val widget = if (it.isNotEmpty()) {
                @KotlinOnly weiVText<TextView, weiVText<*, *>>(
                    it[0] as Key?,
                    it[1] as LayoutParam<*>?,
                    it[2] as String,
                    it[3] as Float,
                    it[4] as Int,
                    it[5]
                )
            } else {
                @JavaOnly weiVText<TextView, weiVText<*, *>>()
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
                    it[4] as Int,
                    it[5]
                )
            } else {
                @JavaOnly weiVFlex<LinearLayout>()
            }
            return@IExtensionCreator dispatchWidgetCreate(InternalWidgetDesc.FLEX, widget)
        })

        registerExtension(InternalWidgetDesc.CONSTRAINT_LAYOUT, IExtensionCreator {
            val widget = if (it.isNotEmpty()) {
                @KotlinOnly weiVConstraintLayout(
                    it[0] as Key?,
                    it[1] as LayoutParam<*>?,
                    it[2] as ArrayList<Widget<*>>,
                    it[3]
                )
            } else {
                @JavaOnly weiVConstraintLayout()
            }
            return@IExtensionCreator dispatchWidgetCreate(
                InternalWidgetDesc.CONSTRAINT_LAYOUT,
                widget
            )
        })

        registerExtension(InternalWidgetDesc.LIST_VIEW, IExtensionCreator {
            val widget = if (it.isNotEmpty()) {
                @KotlinOnly weiVListView(
                    it[0] as Key?,
                    it[1] as LayoutParam<*>?,
                    it[2] as Int,
                    it[3] as Int,
                    it[4] as (index: Int) -> Unit,
                    it[5]
                )
            } else {
                @JavaOnly weiVListView<RecyclerView>()
            }
            return@IExtensionCreator dispatchWidgetCreate(
                InternalWidgetDesc.LIST_VIEW,
                widget
            )
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