package com.wegeekapps.android.wegeek.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * 基础Activity 框架实现
 * 后续会加入分享控件
 * <p/> Charming on 15/10/28.
 * /**
 * BASE基类
 *
 * @author huangxuyang
 * @created 2015/11/24 0024
 */
public abstract class BaseActivity extends Activity {
    /**
     * LOG打印标签
     */
    private static final String TAG = BaseActivity.class.getSimpleName();

    /**
     * 全局的Context {@link #mContext = this.getApplicationContext();}
     */
    protected Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this.getApplicationContext();

        BaseApplication.addActivity(this);
    }


    /**
     * 引入两个抽象方法，在setContentView的时候自动加载，用于规范 初始化数据和事件监听
     * 调用顺序1.initView用于加载数据  2.setListener用于设置事件监听
     *
     * @param layoutResID
     * @author Charming
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initView();
        setListener();
    }

    /**
     * 长时间显示Toast提示(来自String)
     *
     * @param message
     */
    protected void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast提示(来自res)
     *
     * @param resId
     */
    protected void showToast(int resId) {
        Toast.makeText(mContext, getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 短暂显示Toast提示(来自res)
     *
     * @param resId
     */
    protected void showShortToast(int resId) {
        Toast.makeText(mContext, getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自String)
     *
     * @param text
     */
    protected void showShortToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        //overridePendingTransition(android.support.v7.appcompat.R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 通过Action跳转界面
     **/
    public void startActivity(String action) {
        startActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    public void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        //overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        //overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 初始化View的一些数据
     */
    protected abstract void initView();


    /**
     * 设置各类事件监听
     */
    protected abstract void setListener();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
