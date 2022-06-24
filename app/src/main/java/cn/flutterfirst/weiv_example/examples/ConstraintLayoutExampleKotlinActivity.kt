package cn.flutterfirst.weiv_example.examples

import cn.flutterfirst.weiv.core.WeiV
import cn.flutterfirst.weiv.core.activities.WeiVActivity
import cn.flutterfirst.weiv.wrappers.constraintlayout.CL
import cn.flutterfirst.weiv.wrappers.constraintlayout.ConstraintLayout
import cn.flutterfirst.weiv.wrappers.constraintlayout.applyConstraint
import cn.flutterfirst.weiv_example.ext.Button

class ConstraintLayoutExampleKotlinActivity : WeiVActivity() {
    override fun build(buildCount: Int) = WeiV {
        ConstraintLayout {
            Button(text = "测试").applyConstraint {
                width = 100
                height = 100
                centerTo = CL.parent
            }
        }
    }
}