package com.wegeekapps.android.wegeek.activity;

import android.os.Bundle;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import com.wegeekapps.android.wegeek.R;
import com.wegeekapps.android.wegeek.base.BaseHandlerActivity;
import com.wegeekapps.android.wegeek.utils.Utils;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Charming on 16/2/2.
 */
public class MyRxJavaActivity extends BaseHandlerActivity {

    @Bind(R.id.tv_rxjava)
    TextView tvRxJava;

    @Bind(R.id.bt_go)
    Button btGo;

    private Subscriber<String> mySubscriber;
    private Observable<String> myObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rxjava);
        handler.sendEmptyMessageDelayed(123, 2000);
    }

    public void initRxJava() {
        myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );
    }

    @Override
    protected void handler(Message msg) {
        if (msg.what == 123) {
            myObservable.subscribe(mySubscriber);
        }
    }

    @Override
    protected void initView() {
        initRxJava();
        initSubcriber();
    }

    @Override
    protected void setListener() {
    }


    public void initSubcriber() {
        mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                Utils.showToast(MyRxJavaActivity.this, s);
                System.out.println(s);
                tvRxJava.setText(s);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
        };
    }
}
