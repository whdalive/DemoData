package com.example.niyati.demodatafiles.ContentProvider;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
                startFragment(1);
            }
        });
        findViewById(R.id.read_provider).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(2);
            }
        });
    }
    private void startFragment(int type){
        Bundle args = new Bundle();
        args.putInt("key",type);
        Reader reader = new Reader();
        reader.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,reader)
                .commit();

    }
}
