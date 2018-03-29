package com.example.niyati.demodatafiles.File;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.niyati.demodatafiles.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Niyati on 2018/3/28.
 */

public class FileActivity extends AppCompatActivity implements View.OnClickListener{

    //两个文本框，一个输入框，一个输出框
    private EditText write;
    private EditText read;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        initViews();
    }
    private void initViews(){
        findViewById(R.id.submit).setOnClickListener(this);
        findViewById(R.id.read).setOnClickListener(this);
        write = findViewById(R.id.text_write);
        read = findViewById(R.id.text_read);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                saveText();
                break;
            case R.id.read:
                String content = getText();
                read.setText(content);
                break;
        }
    }
    private void saveText(){
        String data = (write.getText().toString().equals("")? "null":write.getText().toString());
        String filename = "myFile";
        FileOutputStream out = null;
        try {
            out = openFileOutput(filename, Context.MODE_PRIVATE);
            out.write(data.getBytes());
            out.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getText(){
        String filename = "myFile";
        StringBuilder content = new StringBuilder();
        FileInputStream in = null;
        BufferedReader reader = null;
        try {
            in = openFileInput(filename);
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine())!=null){
                content.append(line);
                content.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (reader != null ){
                try{
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
