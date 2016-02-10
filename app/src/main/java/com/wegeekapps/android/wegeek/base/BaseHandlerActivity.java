package com.wegeekapps.android.wegeek.base;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.wegeekapps.android.wegeek.factroy.ServiceFactory;
import com.wegeekapps.android.wegeek.service_model.V2exHotService;

import java.lang.ref.WeakReference;

/**
 * 带Handler的Activity基类
 *
 * @author Charming
 *         Create on 16/1/15
 */
public abstract class BaseHandlerActivity extends BaseActivity {

    /**
     * Handler
     */
    protected static WeekHandler handler;

    protected static V2exHotService service = ServiceFactory.createRetrofitService(V2exHotService.class, V2exHotService.SERVICE_ENDPOINT);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new WeekHandler(Looper.getMainLooper(), BaseHandlerActivity.this);
        setHandler();

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    /**
     * Handler类
     */
    public static class WeekHandler extends Handler {

        WeakReference<Activity> mActivityReference;


        private InterfaceHandler handler;//回调接口，消息传递给注册者

        public WeekHandler(Looper looper, Activity activity) {
            super(looper);
            mActivityReference = new WeakReference<>(activity);
        }

        public WeekHandler(Looper looper, InterfaceHandler handler) {
            super(looper);
            this.handler = handler;
        }

        public void setHandler(InterfaceHandler handler) {
            this.handler = handler;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final Activity activity = mActivityReference.get();
            if (activity == null) {
                return;
            }
            if (handler != null) {
                handler.handleMessage(msg);//有消息，就传递
            }
        }
    }

    /**
     * 接口
     */
    public interface InterfaceHandler {
        void handleMessage(Message msg);
    }

    /**
     * 处理消息
     *
     * @param msg
     */
    protected abstract void handler(Message msg);

    /**
     * 设置Handler
     */
    private void setHandler() {
        handler.setHandler(new InterfaceHandler() {
            public void handleMessage(Message msg) {
                handler(msg);//有消息就提交给子类实现的方法
            }
        });
    }

    /**
     * 销毁时把handler清除
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}

