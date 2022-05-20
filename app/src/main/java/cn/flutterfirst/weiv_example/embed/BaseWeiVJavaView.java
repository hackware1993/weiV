package cn.flutterfirst.weiv_example.embed;

import android.content.Context;
import android.util.AttributeSet;

import cn.flutterfirst.weiv.core.others.JavaOnly;
import cn.flutterfirst.weiv.core.views.WeiVJavaView;
import cn.flutterfirst.weiv_example.ext.weiVButton;

abstract class BaseWeiVJavaView extends WeiVJavaView {

    public BaseWeiVJavaView(Context context, AttributeSet attrs) {
        super(context);
    }

    @JavaOnly
    public weiVButton Button() {
        return getWeiVJavaHelper().getWeiV().addLeafRenderWidget(new weiVButton());
    }
}
