package com.wegeekapps.android.wegeek.utils;

import android.app.Activity;
import android.content.Context;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * 项目网络请求框架使用OkHttp，有关使用和学习请访问
 * http://square.github.io/okhttp/
 * <p>
 * Created by Charming on 15/12/16.
 */
public class HttpUtils {

    /**
     * 声明全局变量 OkHttp官方文档鼓励OkHttpClient类使用全局声明
     */
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();

    /**
     * 超时设定（单位：秒）
     */
    private static final int CONNECT_TIMEOUT = 30;

    static {
        mOkHttpClient.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
    }

    /**
     * 默认编码.
     */
    private static final String DEFAULT_ENCODE = "UTF-8";

    /**
     * 默认请求头的Content Type.
     */
    private static final String DEFAULT_CONTENT_TYPE = "application/json";


    /**
     * GET方法
     *
     * @param url
     * @return
     * @throws IOException
     * @author Charming
     */
    public static String get(Context context, String url) throws IOException {
        if (!NetUtils.isConnected(context)) {
            return "";
        }
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * GET方法 检查Login状态
     *
     * @param url
     * @return
     * @throws IOException
     * @author Charming
     */
    public static String get(Activity activity, String url) throws IOException {
        if (!NetUtils.isConnected(activity)) {
            return "";
        }
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = mOkHttpClient.newCall(request).execute();
        ///检查Login

        return response.body().string();
    }


    /**
     * POST方法 传入Json字符串
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     * @TODO 尚未知道请求头格式，若有不同后续再修改
     * @author Charming
     */
    @Deprecated
    public static String post(String url, String json) throws IOException {

        //设置请求头
        MediaType mediaType = MediaType.parse(DEFAULT_CONTENT_TYPE);

        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * POST方法 传入Json字符串
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     * @TODO 尚未知道请求头格式，若有不同后续再修改
     * @author Charming
     */
    @Deprecated
    public static String delete(String url, String json) throws IOException {
        //设置请求头
        MediaType mediaType = MediaType.parse(DEFAULT_CONTENT_TYPE);


        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .delete(body)
                .build();
        Response response = mOkHttpClient.newCall(request).execute();

        return response.body().string();
    }


    /**
     * GET请求，使用Map请求键值对
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String getByMap(Context context, String url, Map<String, String> params) throws IOException {
        if (null != params) {
            url = url + "?" + getRequestData(params);
        }
        return get(context, url);
    }

    /**
     * GET请求，使用Map请求键值对
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String getByMap(Activity activity, String url, Map<String, String> params) throws IOException {
        if (null != params) {
            url = url + "?" + getRequestData(params);
        }
        return get(activity, url);
    }

    /**
     * 将请求参数的Map转换为字符串. <br/>
     *
     * @param params 请求参数.
     * @return 转换后的字符串.
     * @throws UnsupportedEncodingException
     * @author Charming
     * @since JDK 1.6
     */
    private static String getRequestData(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuffer buffer = new StringBuffer();

        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                buffer.append(entry.getKey());
                buffer.append("=");
                if (entry.getValue() == "") {
                    @SuppressWarnings("unused")
                    String nullstring = null;
                    buffer.append("");
                } else {
                    buffer.append(URLEncoder.encode(entry.getValue(), DEFAULT_ENCODE));
                }

                buffer.append("&");
            }
            buffer.deleteCharAt(buffer.length() - 1);
        }

        return buffer.toString();
    }


    /**
     * post方法，传Key-Value的FormData
     *
     * @param url
     * @param form
     * @return
     * @throws IOException
     */
    public static String postFormData(Context context, String url, Map<String, Object> form) throws IOException {
        if (!NetUtils.isConnected(context)) {
            return "";
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();
        Iterator contentIterator = form.entrySet().iterator();

        while (contentIterator.hasNext()) {
            Entry entry = (Entry) contentIterator.next();
            entry.getKey();
            entry.getValue();

            //判断是否有传数组
            if (isIntArray(entry.getValue())) {
                int[] intArray = (int[]) entry.getValue();
                for (int i : intArray) {
                    builder.add((String) entry.getKey(), String.valueOf(i));
                }

            } else {
                builder.add((String) entry.getKey(), String.valueOf(entry.getValue()));
            }


        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();

    }


    public static boolean isIntArray(Object object) throws IOException {
        return object instanceof int[] ? true : false;
    }


    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    /**
     * 图片上传
     *
     * @param url
     * @param token
     * @param sourceImageFile
     * @return
     * @throws IOException
     */
    public static String uploadImage(Context context, String url, String token, String sourceImageFile) throws IOException {
        if (!NetUtils.isConnected(context)) {
            return "";
        }
        File sourceFile = new File(sourceImageFile);
//            Log.d(TAG, "File...::::" + sourceFile + " : " + sourceFile.exists());
        RequestBody requestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addFormDataPart("token", token)
                .addFormDataPart("avatarFile", "profile.png", RequestBody.create(MEDIA_TYPE_PNG, sourceFile))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        Response response = mOkHttpClient.newCall(request).execute();
        return (response.body().string());

    }

}
