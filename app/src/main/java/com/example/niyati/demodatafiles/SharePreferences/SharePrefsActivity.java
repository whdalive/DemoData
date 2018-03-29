package com.example.niyati.demodatafiles.SharePreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.niyati.demodatafiles.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Niyati on 2018/3/28.
 */

public class SharePrefsActivity extends AppCompatActivity implements View.OnClickListener{

    //两个文本框，一个输入框，一个输出框
    private EditText write;
    private EditText read;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefs);
        initViews();
    }
    private void initViews(){
        findViewById(R.id.prefs_submit).setOnClickListener(this);
        findViewById(R.id.prefs_read).setOnClickListener(this);
        write = findViewById(R.id.prefs_edt_write);
        read = findViewById(R.id.prefs_edt_read);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prefs_submit:
                saveText();
                break;
            case R.id.prefs_read:
                String content = getText();
                read.setText(content);
                break;
        }
    }
    private void saveText(){
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        SharedPreferences.Editor ed =
        editor.putString("name","whdalive");
        editor.putString("info", write.getText().toString().equals("")?"null":write.getText().toString());
        editor.apply();
    }
    private String getText(){
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        String name = preferences.getString("name","");
        String info = preferences.getString("info","");
        Log.d("111111111111111", "getText: "+info);
        return new StringBuilder().append(name).append(info).toString();
    }
}
