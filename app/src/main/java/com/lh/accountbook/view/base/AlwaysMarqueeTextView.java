package com.lh.accountbook.view.base;


import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * Created by LuHao on 2017/7/6.
 * 跑马灯textview
 */

public class AlwaysMarqueeTextView extends AppCompatTextView {

    public AlwaysMarqueeTextView(Context context) {
        super(context);
        initView();
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        //跑马灯缺少以下两种任何一个属性都不行
        setEllipsize(TextUtils.TruncateAt.MARQUEE);//超出文本的省略号显示方式，这里选择没有省略号
        setSingleLine(true);//因为文字只能显示一行，在一行内实现跑马灯。所以设置属性单行模式
    }

    //跑马灯属性只有在获得焦点的情况下才有动画。
    //所以，这里我们让这个textview自动获得焦点
    @Override
    public boolean isFocused() {
        return true;
    }

}
