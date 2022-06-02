package cn.flutterfirst.weiv.wrappers.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.extension.ExtensionMgr
import cn.flutterfirst.weiv.core.extension.IExtensionCreator
import cn.flutterfirst.weiv.core.extension.ISerializableWidget
import cn.flutterfirst.weiv.core.extension.IWeiVExtension
import cn.flutterfirst.weiv.core.keys.Key
import cn.flutterfirst.weiv.core.others.KotlinOnly
import cn.flutterfirst.weiv.core.others.LayoutParam
import cn.flutterfirst.weiv.core.views.WeiVItemView
import cn.flutterfirst.weiv.core.widgets.LeafRenderWidget
import cn.flutterfirst.weiv.core.widgets.Widget
import cn.flutterfirst.weiv.wrappers.InternalWidgetDesc
import org.json.JSONObject

open class weiVRecyclerView<V : RecyclerView>(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    open var itemCount: Int = 0,
    open var orientation: Int = RecyclerViewDirection.VERTICAL,
    open var block: ((index: Int) -> Unit)? = null,
    extra: Any? = null,
) :
    LeafRenderWidget<V, weiVRecyclerView<V>>(key, layoutParam, extra = extra),
    IWeiVExtension,
    ISerializableWidget<weiVRecyclerView<V>> {

    override fun createView(context: Context): V = RecyclerView(context) as V

    @SuppressLint("WrongConstant")
    override fun doParameter(view: V, first: Boolean): V {
        view.layoutManager = LinearLayoutManager(view.context, orientation, false)
        view.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int,
            ): RecyclerView.ViewHolder {
                val weiVItemView = WeiVItemView(parent.context)
                return object : RecyclerView.ViewHolder(weiVItemView) {
                }
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val itemWidgets = ArrayList<Widget<*>>()
                val temp = WeiV.globalWidgetContext
                WeiV.globalWidgetContext = itemWidgets
                block!!(position)
                WeiV.globalWidgetContext = temp
                (holder.itemView as WeiVItemView).inflate(itemWidgets[0] as LeafRenderWidget<*, *>)
            }

            override fun getItemCount(): Int {
                return this@weiVRecyclerView.itemCount
            }
        }
        return view
    }

    override fun fromJson(jsonObj: JSONObject, param: Map<String, Any?>): weiVRecyclerView<V> {
        return this
    }
}

var creator: IExtensionCreator<weiVRecyclerView<*>>? = null

@KotlinOnly
fun WeiV.RecyclerView(
    key: Key? = null,
    layoutParam: LayoutParam<*>? = null,
    extra: Any? = null,
    itemCount: Int,
    orientation: Int = RecyclerViewDirection.VERTICAL,
    block: (index: Int) -> Unit,
): weiVRecyclerView<*> {
    if (creator == null) {
        creator = ExtensionMgr.getExtension(InternalWidgetDesc.RECYCLER_VIEW)
    }

    return addLeafRenderWidget(
        creator!!.createWidget(
            key,
            layoutParam,
            itemCount,
            orientation,
            block,
            extra
        )
    )
}