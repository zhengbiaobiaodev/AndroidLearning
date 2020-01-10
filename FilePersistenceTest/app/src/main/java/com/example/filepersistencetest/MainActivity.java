package com.example.filepersistencetest;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.edit);
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            mEditText.setText(inputText);
            mEditText.setSelection(inputText.length());
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = mEditText.getText().toString();
        save(inputText);
    }

    private void save(String text) {
        FileOutputStream fos;
        BufferedWriter bw = null;

        try {
            fos = openFileOutput("data", MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(text);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private String load() {
        FileInputStream fis;
        BufferedReader br = null;
        StringBuilder content = new StringBuilder();
        try {
            fis = openFileInput("data");
            br = new BufferedReader(new InputStreamReader(fis));
            String line = "";
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return content.toString();
    }
}
