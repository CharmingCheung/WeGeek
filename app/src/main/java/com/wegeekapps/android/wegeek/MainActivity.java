package com.wegeekapps.android.wegeek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.wegeekapps.android.wegeek.activity.MyRxJava2Activity;
import com.wegeekapps.android.wegeek.activity.MyRxJava3Activity;
import com.wegeekapps.android.wegeek.activity.MyRxJavaActivity;
import com.wegeekapps.android.wegeek.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this, MyRxJava3Activity.class));
        finish();
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void setListener() {
    }
}
