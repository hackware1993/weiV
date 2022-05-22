package cn.flutterfirst.weiv.core.others

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.IExtensionCreator
import cn.flutterfirst.weiv.core.widgets.ConstWidget
import cn.flutterfirst.weiv.core.widgets.StatefulWidget
import cn.flutterfirst.weiv.core.widgets.XmlViewWidget
import cn.flutterfirst.weiv.wrappers.InternalWidgetDesc
import cn.flutterfirst.weiv.wrappers.linearlayout.weiVFlex
import cn.flutterfirst.weiv.wrappers.textview.weiVText

class WeiVJavaHelper {
    var weiV: WeiV? = null
    var textViewCreator: IExtensionCreator<weiVText<TextView, *>>? = null
    var flexCreator: IExtensionCreator<weiVFlex<LinearLayout>>? = null

    fun createWeiV(build: IBuild): WeiV {
        val temp = weiV
        val weiV = WeiV()
        this.weiV = weiV
        build.build()
        this.weiV = temp
        return weiV
    }

    fun createText(): weiVText<TextView, *> {
        if (textViewCreator == null) {
            textViewCreator = ExtensionMgr.getExtension(InternalWidgetDesc.TEXT)
        }
        val weiVText = textViewCreator!!.createWidget()
        weiV!!.addLeafRenderWidget(weiVText)
        return weiVText
    }

    fun createFlex(build: IBuildWithContext<weiVFlex<LinearLayout>>): weiVFlex<LinearLayout> {
        if (flexCreator == null) {
            flexCreator = ExtensionMgr.getExtension(InternalWidgetDesc.FLEX)
        }
        val weiVFlex = flexCreator!!.createWidget()
        weiV!!.addContainerRenderWidget(weiVFlex) {
            build.build(weiVFlex)
        }
        return weiVFlex
    }

    fun createFlex(build: IBuild): weiVFlex<LinearLayout> {
        if (flexCreator == null) {
            flexCreator = ExtensionMgr.getExtension(InternalWidgetDesc.FLEX)
        }
        val weiVFlex = flexCreator!!.createWidget()
        weiV!!.addContainerRenderWidget(weiVFlex) {
            build.build()
        }
        return weiVFlex
    }

    fun createStateful(state: StatefulWidget.State): StatefulWidget {
        return weiV!!.addLeafRenderWidget(StatefulWidget(state = state))
    }

    fun createConst(buildCount: Int, build: IBuild): ConstWidget {
        return if (buildCount == 0) {
            weiV!!.addContainerRenderWidget(ConstWidget(childWidgets = ArrayList())) {
                build.build()
            }
        } else {
            weiV!!.addLeafRenderWidget(ConstWidget())
        }
    }

    fun <V : View, P> createXmlView(
        viewCreator: () -> V,
        onParamChanged: (view: V, param: P?, first: Boolean) -> Unit
    ): XmlViewWidget<V, P> {
        return weiV!!.addLeafRenderWidget(
            XmlViewWidget(
                viewCreator = viewCreator,
                onParamChanged = onParamChanged
            )
        )
    }
}