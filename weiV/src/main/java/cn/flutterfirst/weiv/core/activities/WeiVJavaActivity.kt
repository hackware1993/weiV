package cn.flutterfirst.weiv.core.activities

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.others.*
import cn.flutterfirst.weiv.core.widgets.StatefulWidget
import cn.flutterfirst.weiv.core.widgets.XmlViewWidget
import cn.flutterfirst.weiv.wrappers.linearlayout.weiVFlex
import cn.flutterfirst.weiv.wrappers.textview.weiVText

abstract class WeiVJavaActivity : WeiVActivity() {
    val weiVJavaHelper = WeiVJavaHelper()

    fun WeiV(build: Build): WeiV {
        return weiVJavaHelper.createWeiV(build)
    }

    open fun Text(): weiVText<TextView> {
        return weiVJavaHelper.createText()
    }

    open fun Flex(build: BuildWithContext<weiVFlex<LinearLayout>>): weiVFlex<LinearLayout> {
        return weiVJavaHelper.createFlex(build)
    }

    open fun Flex(build: Build): weiVFlex<LinearLayout> {
        return weiVJavaHelper.createFlex(build)
    }

    open fun Stateful(state: StatefulWidget.State): StatefulWidget {
        return weiVJavaHelper.createStateful(state = state)
    }

    open fun <VIEW : View, PARAM> XmlView(
        viewCreator: BuildValue<VIEW>,
        onParamChanged: ParamChangedCallback<VIEW, PARAM>
    ): XmlViewWidget<VIEW, PARAM> {
        return weiVJavaHelper.createXmlView({
            viewCreator.build()
        }, onParamChanged = { view, param, first ->
            onParamChanged.onParamChanged(view, param, first)
        })
    }

    fun setState(build: Build) {
        build.build()
        weiVRoot.update(build())
    }
}