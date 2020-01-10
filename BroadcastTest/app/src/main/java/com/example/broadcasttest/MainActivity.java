package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private IntentFilter mIntentFilter;
    private NetworkChangeReceiver mNetworkChangeReceiver;
    private MyBroadcastReceiver mMyBroadcastReceiver;
    private LocalReceiver mLocalReceiver;

    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(MainActivity.this); //获取实例

        //发出去的标准广播
        //自定义广播，可以携带一些数据给广播接收器
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
                Intent intent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
                //intent.setPackage("com.example.broadcasttest2");
                //intent.setPackage("com.example.broadcasttest");
                //sendOrderedBroadcast(intent, null);
                //Log.d(TAG, "onClick: ");
                mLocalBroadcastManager.sendBroadcast(intent);//发送本地广播
            }
        });

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        mNetworkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(mNetworkChangeReceiver, mIntentFilter);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("com.example.broadcasttest.MY_BROADCAST");
        mIntentFilter.setPriority(100);
        mMyBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(mMyBroadcastReceiver, mIntentFilter);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        mLocalReceiver = new LocalReceiver();
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, mIntentFilter);
    }

    private <T> void registerMyReceiver(IntentFilter intentFilter,
                                    String action, T receiver, Integer priority) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetworkChangeReceiver);
        unregisterReceiver(mMyBroadcastReceiver);
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO: This method is called when the BroadcastReceiver is receiving
            // an Intent broadcast.
            Toast.makeText(context, "received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
            abortBroadcast();
        }
    }

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}
