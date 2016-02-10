package com.wegeekapps.android.wegeek.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具杂类
 * Created by Charming on 15/12/18.
 */
public class Utils {

    private static Toast mToast;

    /**
     * 显示提示
     *
     * @param context
     * @param msg
     * @return
     */
    static public void showToast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    /**
     * 显示提示
     *
     * @param context
     * @param resId   字符串ID
     * @return
     */
    static public void showToast(Context context, int resId) {
        showToast(context, context.getResources().getString(resId));
    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public static void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1]\\d{10}";// "[1]"代表第1位为数字1，"\\d{10}"代表后面是可以是0～9的数字，有10位。
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    public static String toSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }

    /**
     * 判断是否Url
     *
     * @param scanStr
     * @return
     */
    public static boolean isUrl(String scanStr) {
        Pattern pattern = Pattern.compile("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
        Matcher matcher = pattern.matcher(scanStr);
        return matcher.find();
    }

    /**
     * 分割List
     *
     * @param list     待分割的list
     * @param pageSize 每段list的大小
     * @return List<<List<T>>
     */
    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size();//list的大小
        int page = (listSize + (pageSize - 1)) / pageSize;//页数

        List<List<T>> listArray = new ArrayList<List<T>>();//创建list数组 ,用来保存分割后的list
        for (int i = 0; i < page; i++) {//按照数组大小遍历
            List<T> subList = new ArrayList<T>();//数组每一位放入一个分割后的list
            for (int j = 0; j < listSize; j++) {//遍历待分割的list
                int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize;//当前记录的页码(第几页)
                if (pageIndex == (i + 1)) {//当前记录的页码等于要放入的页码时
                    subList.add(list.get(j));//放入list中的元素到分割后的list(subList)
                }

                if ((j + 1) == ((j + 1) * pageSize)) {//当放满一页时退出当前循环
                    break;
                }
            }
            listArray.add(subList);//将分割后的list放入对应的数组的位中
        }
        return listArray;
    }


}
