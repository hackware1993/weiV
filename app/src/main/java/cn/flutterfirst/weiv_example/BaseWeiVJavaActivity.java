package cn.flutterfirst.weiv_example;

import cn.flutterfirst.weiv.core.WeiV;
import cn.flutterfirst.weiv.core.activities.WeiVJavaActivity;
import cn.flutterfirst.weiv.core.others.JavaOnly;
import cn.flutterfirst.weiv_example.ext.weiVButton;

abstract class BaseWeiVJavaActivity extends WeiVJavaActivity {
    @JavaOnly
    public weiVButton Button() {
        return getWeiVJavaHelper().getWeiV().addLeafRenderWidget(new weiVButton());
    }

    public void merge(WeiV weiV) {
        getWeiVJavaHelper().getWeiV().merge(weiV);
    }
}
