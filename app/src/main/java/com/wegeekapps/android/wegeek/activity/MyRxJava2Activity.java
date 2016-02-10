package com.wegeekapps.android.wegeek.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.wegeekapps.android.wegeek.R;
import com.wegeekapps.android.wegeek.base.BaseHandlerActivity;

import butterknife.Bind;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Charming on 16/2/3.
 */
public class MyRxJava2Activity extends BaseHandlerActivity {

    @Bind(R.id.tv_rxjava)
    TextView tvRxJava;

    Observable<String> myObservable;

    Action1<String> onNextAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rxjava);
    }

    @Override
    protected void handler(Message msg) {
        switch (msg.what) {
            case 123:
                myObservable.subscribe(onNextAction);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initView() {
        myObservable = Observable.just("Hello, world!");
        onNextAction = s -> {
            tvRxJava.setText(s);
            System.out.println(s);
        };

        handler.sendEmptyMessageDelayed(123, 2000);
    }

    @Override
    protected void setListener() {

    }
}
