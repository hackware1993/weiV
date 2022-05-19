package cn.flutterfirst.weiv.wrappers.textview

import android.content.Context
import android.widget.TextView
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.IExtensionCreator
import cn.flutterfirst.weiv.core.extension.IWeiVExtension
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.wrappers.InternalWidgetDesc

open class weiVText<T : TextView>(
    key: Key? = null,
    open var text: String = "",
    open var textSize: Float = TextConst.defaultTextSize,
    open var textColor: Int = TextConst.defaultTextColor
) :
    LeafRenderWidget<T>(key), IWeiVExtension {

    override fun createView(context: Context): T = TextView(context) as T

    override fun doParameter(view: T, first: Boolean): T {
        if (view.text != text) {
            view.text = text
        }
        if (view.currentTextColor != textColor) {
            view.setTextColor(textColor)
        }
        if (view.textSize != textSize) {
            view.textSize = textSize
        }
        return view
    }

    @JavaOnly
    open fun wKey(key: Key? = null): weiVText<T> {
        this.key = key
        return this
    }

    @JavaOnly
    open fun wText(text: String = ""): weiVText<T> {
        this.text = text
        return this
    }

    @JavaOnly
    open fun wTextSize(textSize: Float = TextConst.defaultTextSize): weiVText<T> {
        this.textSize = textSize
        return this
    }

    @JavaOnly
    open fun wTextColor(textColor: Int = TextConst.defaultTextColor): weiVText<T> {
        this.textColor = textColor
        return this
    }

    override fun toString(): String {
        return "weiVText($text)"
    }
}

var creator: IExtensionCreator<LeafRenderWidget<*>>? = null

@KotlinOnly
fun WeiV.Text(
    key: Key? = null,
    text: String = "",
    textSize: Float = TextConst.defaultTextSize,
    textColor: Int = TextConst.defaultTextColor
) {
    if (creator == null) {
        creator = ExtensionMgr.getExtension(InternalWidgetDesc.TEXT)
    }

    addLeafRenderWidget(
        creator!!.createWidget(key, text, textSize, textColor)
    )
}