package sg.edu.rp.soi.p12psc347;


import android.app.Activity;

import android.app.AlarmManager;

import android.app.PendingIntent;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;


import java.util.Calendar;


public class ADD extends AppCompatActivity {
    int piReqCode = 12;
    Button btnAdd, btnCancel;
    EditText etName, etDes, etSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_d_d);
        btnAdd = findViewById(R.id.btnok);
        etName = findViewById(R.id.etname);
        etDes = findViewById(R.id.etDes);
        etSec = findViewById(R.id.etTime);
        btnCancel = findViewById(R.id.btncnl);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int seconds = Integer.valueOf(etSec.getText().toString());
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, seconds);
                String name = etName.getText().toString();
                String desc = etDes.getText().toString();
                DBHelper dbh = new DBHelper(ADD.this);
                int id = (int) dbh.insertTask(name, desc);
                dbh.close();
                Intent iReminder = new Intent(ADD.this, MyReceiver.class);
                iReminder.putExtra("id", id);
                iReminder.putExtra("name", name);
                iReminder.putExtra("desc", desc);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(ADD.this, piReqCode, iReminder, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                setResult(RESULT_OK);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
