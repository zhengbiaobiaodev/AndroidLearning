package com.example.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TestMain";

    private FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        //addFragment(new RightFragment(), "rightFragment");
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                /*replaceFragment(getSupportFragmentManager().findFragmentByTag("rightFragment"),
                        new AnotherRightFragment());
                break;*/
            case R.id.button2:

                /*Fragment fragment = manager.findFragmentById(R.id.right_layout);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.hide(fragment).commit();*/

                /*List<Fragment> fragments = manager.getFragments();
                for (Fragment f :
                        fragments) {
                    if (f != null) {
                        Log.d(TAG, "onClick: " + f.toString() + f.isVisible());
                    }
                }*/
                break;
            case R.id.button3:
                /*Fragment fragment1 = manager.findFragmentById(R.id.right_layout);
                if (fragment1 != null)
                    Log.d(TAG, "onClick: " + fragment1.toString() + fragment1.getId());*/
                break;
            default:
                break;
        }
    }

    /*private void addFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.right_layout, fragment, tag);
        transaction.commit();
    }*/

    /*private void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.right_layout, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }*/

    /*private void replaceFragment(Fragment from, Fragment to) {

        FragmentTransaction transaction = manager.beginTransaction();
        if (!to.isAdded()) {
            //transaction.hide(from).add(R.id.right_layout, to).commit();
            transaction.hide(from).add(R.id.right_layout, to).commit();
        } else {
            transaction.hide(from).show(to).commit();
        }
    }*/

    /*private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.right_layout, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }*/

}
