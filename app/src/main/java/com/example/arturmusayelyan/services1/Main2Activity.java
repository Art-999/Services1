package com.example.arturmusayelyan.services1;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    //PendingIntent Example Service. Обратная связь с помощью PendingIntent

    private TextView showServiceResultTv;
    public final int TASK1_REQUESTCODE = 1;

    public final static String TASK_RESULTCODE = "result";
    public final static String PENDING_INTENT = "pendingIntent";
    public final static String EXECUTION_TIME = "time";
    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        showServiceResultTv = findViewById(R.id.service_result_tv);
    }

    public void startButtonOnClick(View view) {
        PendingIntent pendingIntent = createPendingResult(TASK1_REQUESTCODE, null, 0);
        Intent intent = new Intent(this, MyService2.class);
        intent.putExtra(PENDING_INTENT, pendingIntent);
        intent.putExtra(EXECUTION_TIME, 2);
        startService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==STATUS_START){
            switch (requestCode){
                case TASK1_REQUESTCODE:
                    showServiceResultTv.setText("Task 1 start");
                    break;
            }
        }
        if(resultCode==STATUS_FINISH){
            int result=data.getIntExtra(TASK_RESULTCODE,0);
            switch (requestCode){
                case TASK1_REQUESTCODE:
                    showServiceResultTv.setText("Task 1 finish, result= "+result);
                    break;
            }
        }
    }
}
