package cn.flutterfirst.weiv_example.examples

import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.activities.WeiVActivity
import cn.flutterfirst.weiv.core.others.LayoutParam.Companion.matchParent
import cn.flutterfirst.weiv.wrappers.linearlayout.Flex
import cn.flutterfirst.weiv.wrappers.linearlayout.applyFlexLayoutParams
import cn.flutterfirst.weiv.wrappers.recyclerview.ListView
import cn.flutterfirst.weiv.wrappers.textview.Text
import cn.flutterfirst.weiv_example.SkinManager
import cn.flutterfirst.weiv_example.ext.Button

class ListViewExampleKotlinActivity : WeiVActivity() {
    private var itemCount = 30

    override fun build(buildCount: Int) = WeiV {
        Flex {
            ListView(itemCount = itemCount) {
                if (it == 0) {
                    Flex {
                        Button(text = "Add itemCount", onClick = {
                            setState {
                                itemCount++
                            }
                        })

                        Button(text = "Sub itemCount", onClick = {
                            setState {
                                if (itemCount > 1) {
                                    itemCount--
                                }
                            }
                        })

                        Button(text = "Change app skin", onClick = {
                            SkinManager.changeSkin()
                        }).applyFlexLayoutParams {
                            width = 0
                            weight = 1f
                        }
                    }
                } else {
                    if (it % 2 == 0) {
                        Button(text = "$it")
                    } else {
                        Text(text = "$it")
                    }
                }
            }.applyFlexLayoutParams {
                width = matchParent
                height = matchParent
            }
        }
    }
}