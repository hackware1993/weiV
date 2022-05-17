import android.content.Context
import android.widget.TextView
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget

class weiVText(
    override var key: Key? = null,
    var text: String? = null,
    var textSize: Float? = null,
    var textColor: Int? = null
) :
    LeafRenderWidget<TextView>(key) {

    override fun createView(context: Context): TextView = TextView(context)

    override fun doParameter(view: TextView, first: Boolean): TextView {
        if (view.text != text) {
            view.text = text
        }
        if (view.currentTextColor != textColor) {
            view.setTextColor(textColor!!)
        }
        if (view.textSize != textSize) {
            view.textSize = textSize!!
        }
        return view
    }
}

fun WeiV.Text(
    key: Key? = null,
    text: String? = null,
    textSize: Float? = null,
    textColor: Int? = null
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