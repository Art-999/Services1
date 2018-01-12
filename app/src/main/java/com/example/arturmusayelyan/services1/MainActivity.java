package com.example.arturmusayelyan.services1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = "Art";
    private Button startBtn, stopBtn;
    private ProgressBar progressBar;
    private TextView showProgressTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = findViewById(R.id.start_btn);
        stopBtn = findViewById(R.id.stop_btn);
        progressBar = findViewById(R.id.progress_bar);
        showProgressTv = findViewById(R.id.show_progress_tv);
    }

    public void startButtonClick(View view) {
       // startService(new Intent(this, MyService.class));
        new MyService().someTask();
    }

    public void stopButtonClick(View view) {
        stopService(new Intent(this, MyService.class));
    }
}
