package com.example.k.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAlert();
            }
        });

    }

    public void startAlert() {
        EditText text = (EditText)findViewById(R.id.time);
        //int i = Integer.parseInt(text.getText().toString());

        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        /*PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 231324243, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i*1000),pendingIntent);
*/
        //Toast.makeText(this, "Alarm set in" + i + "seconds",Toast.LENGTH_LONG).show();
    }
}
