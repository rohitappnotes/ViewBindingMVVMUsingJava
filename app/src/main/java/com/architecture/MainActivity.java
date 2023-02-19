package com.architecture;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        handler.postDelayed(runnable, 2000);
    }
}