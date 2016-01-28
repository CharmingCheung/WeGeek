package com.wegeekapps.android.wegeek.base;

import android.app.Activity;
import android.view.View;

/**
 * 基础Activity 框架实现
 * 后续会加入分享控件
 * <p/>
 * Created by Charming on 15/10/28.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener {


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        findView();
        initView();
        setOnClick();
    }

    /**
     * 获取布局控件
     */
    protected abstract void findView();

    /**
     * 初始化View的一些数据
     */
    protected abstract void initView();

    /**
     * 设置点击监听
     */
    protected abstract void setOnClick();
}
