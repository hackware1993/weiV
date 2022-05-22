package cn.flutterfirst.weiv.wrappers.textview

import android.content.Context
import android.graphics.Color
import android.widget.TextView
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.IExtensionCreator
import cn.flutterfirst.weiv.core.extension.ISerializableWidget
import cn.flutterfirst.weiv.core.extension.IWeiVExtension
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.JavaOnly
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.wrappers.InternalWidgetDesc
import org.json.JSONObject

open class weiVText<V : TextView, W : weiVText<V, W>>(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    open var text: String = "",
    open var textSize: Float = TextConst.defaultTextSize,
    open var textColor: Int = TextConst.defaultTextColor,
    extra: Any? = null
) :
    LeafRenderWidget<V, weiVText<V, W>>(key, layoutParam, extra = extra), IWeiVExtension,
    ISerializableWidget<weiVText<V, W>> {

    override fun createView(context: Context): V = TextView(context) as V

    override fun doParameter(view: V, first: Boolean): V {
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
    open fun wText(text: String): W {
        this.text = text
        return this as W
    }

    @JavaOnly
    open fun wTextSize(textSize: Float): W {
        this.textSize = textSize
        return this as W
    }

    @JavaOnly
    open fun wTextColor(textColor: Int): W {
        this.textColor = textColor
        return this as W
    }

    override fun fromJson(jsonObj: JSONObject, param: Map<String, Any?>): weiVText<V, W> {
        text = (param["text"] as String?) ?: ""
        val textSizeObj = param["textSize"]
        textSize = when (textSizeObj) {
            is Int -> {
                textSizeObj.toFloat()
            }
            is Float -> {
                textSizeObj
            }
            is Double -> {
                textSizeObj.toFloat()
            }
            else -> {
                TextConst.defaultTextSize
            }
        }
        val colorStr = param["textColor"] as String?
        textColor = if (colorStr != null && colorStr.startsWith("#")) {
            Color.parseColor(colorStr)
        } else {
            TextConst.defaultTextColor
        }
        return this
    }

    override fun toString(): String {
        return "weiVText(text='$text', textSize=$textSize, textColor=$textColor)"
    }
}

var creator: IExtensionCreator<weiVText<*, *>>? = null

@KotlinOnly
fun WeiV.Text(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    text: String = "",
    textSize: Float = TextConst.defaultTextSize,
    textColor: Int = TextConst.defaultTextColor,
    extra: Any? = null
): weiVText<*, *> {
    if (creator == null) {
        creator = ExtensionMgr.getExtension(InternalWidgetDesc.TEXT)
    }

    return addLeafRenderWidget(
        creator!!.createWidget(key, layoutParam, text, textSize, textColor, extra)
    )
}