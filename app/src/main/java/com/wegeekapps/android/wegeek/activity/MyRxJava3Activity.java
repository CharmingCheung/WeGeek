package com.wegeekapps.android.wegeek.activity;

import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wegeekapps.android.wegeek.R;
import com.wegeekapps.android.wegeek.base.BaseHandlerActivity;
import com.wegeekapps.android.wegeek.model.V2exhot;
import com.wegeekapps.android.wegeek.utils.HttpUtils;
import com.wegeekapps.android.wegeek.utils.Utils;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Charming on 16/2/3.
 */
public class MyRxJava3Activity extends BaseHandlerActivity {

    @Bind(R.id.tv_rxjava)
    TextView tv_rxjava;

    private String url = "http://www.v2ex.com/api/topics/hot.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rxjava);
        ButterKnife.bind(this);
    }

    @Override
    protected void handler(Message msg) {


    }

    @Override
    protected void initView() {
        observerByOkhttp(url);

    }

    @Override
    protected void setListener() {

    }

    private void obserVoid1() {
        Observable<List<V2exhot>> observable = service.getHot();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<V2exhot>>() {
                    @Override
                    public void onCompleted() {
                        // handle completed
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle error
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<V2exhot> v) {
                        tv_rxjava.setText(JSON.toJSONString(v));
                    }
                });
    }

    /**
     *
     */
    private void obserVoid2() {
        service.getHot()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v2exhots -> tv_rxjava.setText(JSON.toJSONString(v2exhots)));

    }

    private void observerByOkhttp(final String url) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String result = "";
                try {
                    result = HttpUtils.get(MyRxJava3Activity.this, url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(result);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(s -> tv_rxjava.setText(s));

    }

}
