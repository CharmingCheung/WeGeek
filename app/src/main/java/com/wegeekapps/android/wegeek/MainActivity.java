package com.wegeekapps.android.wegeek;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.wegeekapps.android.wegeek.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @Bind(R.id.fab)
    public FloatingActionButton bt;

    private ListView q;


     class MyAdapter extends BaseAdapter{

         @Override
         public int getCount() {
             return 0;
         }

         @Override
         public Object getItem(int position) {
             return null;
         }

         @Override
         public long getItemId(int position) {
             return 0;
         }

         @Override
         public View getView(int position, View convertView, ViewGroup parent) {
             return null;
         }
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected void findView() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected void setOnClick() {
    }

    @Override
    public void onClick(View v) {

    }
}
