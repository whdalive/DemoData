package com.example.niyati.demodatafiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.niyati.demodatafiles.ContentProvider.GetInfoActivity;
import com.example.niyati.demodatafiles.File.FileActivity;
import com.example.niyati.demodatafiles.SQLite.SQLiteActivity;
import com.example.niyati.demodatafiles.SharePreferences.SharePrefsActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.use_sqllite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SQLiteActivity.class));
            }
        });
        findViewById(R.id.use_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FileActivity.class));
            }
        });
        findViewById(R.id.use_prefs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SharePrefsActivity.class));
            }
        });
        findViewById(R.id.use_provider).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GetInfoActivity.class));
            }
        });
    }
}
