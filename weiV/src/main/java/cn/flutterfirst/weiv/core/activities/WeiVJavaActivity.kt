package cn.flutterfirst.weiv.core.activities

import android.view.View
import android.widget.LinearLayout
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.*
import cn.flutterfirst.weiv.core.widgets.ConstWidget
import cn.flutterfirst.weiv.core.widgets.StatefulWidget
import cn.flutterfirst.weiv.core.widgets.XmlViewWidget
import cn.flutterfirst.weiv.wrappers.linearlayout.weiVFlex
import cn.flutterfirst.weiv.wrappers.textview.weiVText

abstract class WeiVJavaActivity : WeiVActivity() {
    val weiVJavaHelper = WeiVJavaHelper()

    fun WeiV(build: IBuild): WeiV {
        return weiVJavaHelper.createWeiV(build)
    }

    open fun Text(): weiVText<*, *> {
        return weiVJavaHelper.createText()
    }

    open fun Flex(build: IBuildWithContext<weiVFlex<LinearLayout>>): weiVFlex<LinearLayout> {
        return weiVJavaHelper.createFlex(build)
    }

    open fun Flex(build: IBuild): weiVFlex<LinearLayout> {
        return weiVJavaHelper.createFlex(build)
    }

    open fun Stateful(state: StatefulWidget.State): StatefulWidget {
        return weiVJavaHelper.createStateful(state = state)
    }

    open fun Const(key: Key, buildCount: Int, build: IBuild): ConstWidget {
        return weiVJavaHelper.createConst(key, buildCount, build)
    }

    open fun <V : View, P> XmlView(
        viewCreator: IBuildValue<V>,
        onParamChanged: IParamChangedCallback<V, P>,
    ): XmlViewWidget<V, P> {
        return weiVJavaHelper.createXmlView({
            viewCreator.build()
        }, onParamChanged = { view, param, first ->
            onParamChanged.onParamChanged(view, param, first)
        })
    }

    fun setState(build: IBuild) {
        build.build()
        weiVRoot.update(build(weiVRoot.buildCount))
    }
}