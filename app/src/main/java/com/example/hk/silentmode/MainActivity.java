package com.example.hk.silentmode;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.AudioManager;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.TimePicker;
import android.provider.MediaStore.Audio;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.TimePickerDialog;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static android.app.TimePickerDialog.*;


public class MainActivity extends AppCompatActivity {
    public class TickThread extends Thread
    {
        long startAtP;
        long finishAtP;
        TickThread(long temp1, long temp2)
        {
            this.startAtP=temp1;
            this.finishAtP=temp2;
        }
        public void run1()
        {
            new CountDownTimer(/*startAtP*/10000, 1000)
            {

                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish()
                {
                    Toast.makeText(MainActivity.this,"a",Toast.LENGTH_SHORT).show();

                }
            }.start();


        }

    }
    ToggleButton tglSil;
    TextView time;
    Button btn1;
    Button btn2;
    long sub = 0;
    Intent i;
    Intent i2;
    Bundle bundle;
    long startAt=0;
    long finishAt=0;
    AudioManager am;
    TimeZone timeZone = TimeZone.getTimeZone("Asia/Jerusalem");
    Calendar c= Calendar.getInstance(timeZone);
    int hourX=c.get(Calendar.HOUR_OF_DAY);
    int minuteX=c.get(Calendar.MINUTE);
    Calendar c2 = Calendar.getInstance();
    TimePickerDialog timePicker;
    public void timerCountingDown(long millis)
    {
        new CountDownTimer(millis,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                Toast toast = null;
                Toast.makeText(MainActivity.this, "Time's Up!", Toast.LENGTH_SHORT).show();
                tglSil.setChecked(false);
                Calendar c= Calendar.getInstance(timeZone);
                int hourX=c.get(Calendar.HOUR_OF_DAY);
                int minuteX=c.get(Calendar.MINUTE);
                time.setText(String.format("%d:%d", hourX, minuteX));
                timePicker.updateTime(hourX,minuteX);


            }
        }.start();
    };
    TickThread ticker1;
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                startAt=data.getLongExtra("startAt",0);
                finishAt=data.getLongExtra("finishAt",0);
                ticker1=new TickThread(startAt,finishAt);
                ticker1.run1();
                Toast.makeText(getApplicationContext(), startAt+ ",", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), finishAt+ "", Toast.LENGTH_SHORT).show();

            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimePickerDialog.OnTimeSetListener listener = new OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                //Toast.makeText(getApplicationContext(), hourOfDay + " " + minute, Toast.LENGTH_SHORT).show();
                long mills=((hourOfDay-hourX)*3600000)+((minute-minuteX)*60000);
                Toast.makeText(getApplicationContext(), mills+ ","+minuteX, Toast.LENGTH_SHORT).show();
                timerCountingDown(mills);


            }
        };
        timePicker = new TimePickerDialog(this,listener,12,12,true);
        i= new Intent(this,InputActivity.class);
        time = (TextView) findViewById(R.id.time);
        tglSil=(ToggleButton)findViewById(R.id.tglSil);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);

        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);




        time.setText(String.format("%d:%d", hourX, minuteX));
        tglSil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   am.setRingerMode(0);
                } else {
                   am.setRingerMode(2);
                }
            }
        });


        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(30000,1000)
                {

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        Toast toast = null;
                        Toast.makeText(MainActivity.this, "Time's Up!", Toast.LENGTH_SHORT).show();
                        tglSil.setChecked(false);


                    }
                }.start();

                Toast toast = null;
                //Toast.makeText(MainActivity.this, "Time's Up!", Toast.LENGTH_SHORT).show();

            }
        });
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivityForResult(i,1);






            }
        });




    }

}

