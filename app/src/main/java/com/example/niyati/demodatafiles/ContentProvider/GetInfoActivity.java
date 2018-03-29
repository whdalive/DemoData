package com.example.niyati.demodatafiles.ContentProvider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.niyati.demodatafiles.R;

public class GetInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getinfo);
        findViewById(R.id.read_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction().add(R.id.container,new Contact())
                        .addToBackStack(null)
                        .commit();

            }
        });
    }
}
