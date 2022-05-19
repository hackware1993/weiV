package cn.flutterfirst.weiv.wrappers.textview

import android.content.Context
import android.widget.TextView
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget

class weiVText(
    key: Key? = null,
    var text: String = "",
    var textSize: Float = TextConst.defaultTextSize,
    var textColor: Int = TextConst.defaultTextColor
) :
    LeafRenderWidget<TextView>(key) {

    override fun createView(context: Context): TextView = TextView(context)

    override fun doParameter(view: TextView, first: Boolean): TextView {
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
    fun wKey(key: Key? = null): weiVText {
        this.key = key
        return this
    }

    @JavaOnly
    fun wText(text: String = ""): weiVText {
        this.text = text
        return this
    }

    @JavaOnly
    fun wTextSize(textSize: Float = TextConst.defaultTextSize): weiVText {
        this.textSize = textSize
        return this
    }

    @JavaOnly
    fun wTextColor(textColor: Int = TextConst.defaultTextColor): weiVText {
        this.textColor = textColor
        return this
    }

    override fun toString(): String {
        return "weiVText($text)"
    }
}

@KotlinOnly
fun WeiV.Text(
    key: Key? = null,
    text: String = "",
    textSize: Float = TextConst.defaultTextSize,
    textColor: Int = TextConst.defaultTextColor
) {
    addLeafRenderWidget(
        weiVText(
            key = key,
            text = text,
            textSize = textSize,
            textColor = textColor
        )
    )
}