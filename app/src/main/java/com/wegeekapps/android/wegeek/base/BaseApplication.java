package com.wegeekapps.android.wegeek.base;

/**
 * Created by Charming on 16/2/2.
 */

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.wegeekapps.android.wegeek.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

/**
 * 全局变量、公用资源类
 *
 * @author huangxuyang
 * @created 2015/11/24 0024
 */
public class BaseApplication extends Application {

    /**
     * 当前用户TOKEN
     */
    public String token = "";

    /**
     *
     */
    private static BaseApplication mInstance = null;

    /**
     * 声明一个SharedPreferences
     */
    public SharedPreferences sp = null;

    /**
     * 用于缓存全局变量
     */
    public Map<String, Object> cache;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Fresco.initialize(this);
        cache = new HashMap<String, Object>();
        activitys = new ArrayList<BaseActivity>();
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }


    /**
     * Activity集合
     */
    private static ArrayList<BaseActivity> activitys = new ArrayList<BaseActivity>();

    /**
     * 添加Activity到ArrayList<Activity>管理集合
     *
     * @param activity
     */
    public static void addActivity(BaseActivity activity) {
        String className = activity.getClass().getName();
        for (Activity at : activitys) {
            if (className.equals(at.getClass().getName())) {
                activitys.remove(at);
                break;
            }
        }
        activitys.add(activity);
    }

    /**
     * 销毁所有Activity
     */
    public static void destroyActivitys() {
        for (Activity activity : activitys) {
            activity.finish();
        }
    }

    /**
     * 销毁指定Activity
     *
     * @param activityName
     */
    public static void destroyActivityByActivityName(Activity activityName) {
        for (Activity activity : activitys) {
            if (activity != null && activity.equals(activityName)) {
                activity.finish();
            }
        }
    }

}
