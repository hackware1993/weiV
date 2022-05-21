package cn.flutterfirst.weiv_example.embed

import android.content.Context
import android.util.AttributeSet
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.views.WeiVView
import cn.flutterfirst.weiv.wrappers.linearlayout.Flex
import cn.flutterfirst.weiv.wrappers.linearlayout.FlexDirection
import cn.flutterfirst.weiv.wrappers.textview.Text
import cn.flutterfirst.weiv_example.ext.Button

class WeiVCounterKotlinView(context: Context, attrs: AttributeSet?) : WeiVView(context) {
    private var count = 0
    private val maxCount = 10
    private val minCount = 0

    override fun build(buildCount: Int) = WeiV {
        Flex {
            it.orientation = FlexDirection.VERTICAL

            Button(text = "Add count", enable = count < maxCount, onClick = {
                setState {
                    count++
                }
            })

            Button(text = "Sub count", enable = count > minCount, onClick = {
                setState {
                    count--
                }
            })

            Text(text = "count = $count")
        }
    }
}