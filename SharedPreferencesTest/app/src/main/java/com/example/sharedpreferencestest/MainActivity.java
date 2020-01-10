package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save = findViewById(R.id.save_data);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * SharedPreferences 的三种获取方式
                 * Context类中的getSharedPreferences("文件名", 读写模式(目前只有MODE_PRIVATE))
                 * Activity类中的getPreferences(读写模式(目前只有MODE_PRIVATE)) 将当前类名作为文件名
                 * PreferenceManager类中的getDefaultSharedPreferences(Context参数) 将当前包名作为文件名
                 */
                SharedPreferences.Editor editor = getSharedPreferences("data",
                        MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 23);
                editor.putBoolean("married", false);
                editor.apply();
            }
        });

        Button load = findViewById(R.id.restore_data);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences ss = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
                String name = sp.getString("name", "");
                int age = sp.getInt("age", 0);
                boolean married = sp.getBoolean("married", false);
                Log.d(TAG, "onClick: name is " + name);
                Log.d(TAG, "onClick: age is " + age);
                Log.d(TAG, "onClick: married is " + married);
            }
        });
    }
}
