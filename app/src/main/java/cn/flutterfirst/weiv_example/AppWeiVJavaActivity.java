package cn.flutterfirst.weiv_example;

import cn.flutterfirst.weiv.core.WeiVJavaActivity;
import cn.flutterfirst.weiv.core.others.JavaOnly;
import cn.flutterfirst.weiv.wrappers.textview.TextConst;
import cn.flutterfirst.weiv_example.ext.weiVButton;

abstract class AppWeiVJavaActivity extends WeiVJavaActivity {
    @JavaOnly
    public weiVButton Button() {
        weiVButton weiVButton = new weiVButton(null, "", TextConst.getDefaultTextSize(), TextConst.getDefaultTextColor(), null);
        getWeiV().addLeafRenderWidget(weiVButton);
        return weiVButton;
    }
}
