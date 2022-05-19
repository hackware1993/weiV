package cn.flutterfirst.weiv_example

import android.content.Intent
import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.WeiVActivity
import cn.flutterfirst.weiv.wrappers.linearlayout.Flex
import cn.flutterfirst.weiv.wrappers.linearlayout.FlexDirection
import cn.flutterfirst.weiv.wrappers.textview.Text
import cn.flutterfirst.weiv_example.ext.Button

class WeiVCounter : WeiVActivity() {
    private var count = 0
    private val maxCount = 10
    private val minCount = 0

    override fun build() = WeiV {
        Flex {
            it.orientation = FlexDirection.VERTICAL

            Button(text = "Add count", onClick = {
                setState {
                    if (count < maxCount) {
                        count++
                    }
                }
            })

            Button(text = "Sub count", onClick = {
                setState {
                    if (count > minCount) {
                        count--
                    }
                }
            })

            Text(text = "count = $count")

            Button(text = "Open weiV Java", onClick = {
                startActivity(Intent(this@WeiVCounter, WeiVJavaCounter::class.java))
            })
        }
    }
}