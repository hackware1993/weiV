import android.content.Context
import android.widget.LinearLayout
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.widgets.RenderWidget

class weiVFlex(override var key: Key? = null, var orientation: Int? = null) :
    RenderWidget<LinearLayout>(key) {

    override fun createView(context: Context): LinearLayout = LinearLayout(context)

    override fun doParameter(view: LinearLayout, first: Boolean): LinearLayout {
        if (view.orientation != orientation) {
            if (orientation == null) {
                orientation = LinearLayout.HORIZONTAL
            }
            view.orientation = orientation!!
        }
        return view
    }
}