package com.example.hk.silentmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
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

public class InputActivity extends AppCompatActivity {
    TimeZone timeZone = TimeZone.getTimeZone("Asia/Jerusalem");
    Calendar c= Calendar.getInstance(timeZone);
    int hourX=c.get(Calendar.HOUR_OF_DAY);
    int minuteX=c.get(Calendar.MINUTE);
    long startMill=0;
    long finishMill=0;
    Button btn3;
    Button btn4;
    Button btn5;
    boolean flag;
    boolean flag2;

    TimePickerDialog timePicker;
    Intent i2;
    Calendar c2 = Calendar.getInstance();
    /*@Override
    protected void onActivityResult(int aRequestCode, int aResultCode, Intent aData){
        ;    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        c= Calendar.getInstance(timeZone);
        flag=false;
        flag2=false;
        hourX=c.get(Calendar.HOUR_OF_DAY);
        minuteX=c.get(Calendar.MINUTE);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        btn5=(Button)findViewById(R.id.btn5);
        i2=new Intent(this,MainActivity.class);
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                //Toast.makeText(getApplicationContext(), hourOfDay + " " + minute, Toast.LENGTH_SHORT).show();
                if (!flag) {
                    startMill = ((hourOfDay - hourX) * 3600000) + ((minute - minuteX) * 60000);
                    Toast.makeText(getApplicationContext(), startMill+ ",", Toast.LENGTH_SHORT).show();
                    flag2=true;


                }
                else
                {
                    finishMill=((hourOfDay - hourX) * 3600000) + ((minute - minuteX) * 60000);
                    Toast.makeText(getApplicationContext(), finishMill+ ",", Toast.LENGTH_SHORT).show();

                }



            }
        };
        timePicker = new TimePickerDialog(this,listener,hourX,minuteX,true);
        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                timePicker.updateTime(hourX,minuteX);
                timePicker.show();

            }
        });
        btn4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (flag2)
                {
                    flag=true;
                }
                timePicker.updateTime(hourX,minuteX);
                timePicker.show();
            }
        });
        btn5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                i2.putExtra("startAt",startMill);
                i2.putExtra("finishAt",(finishMill-startMill));
                setResult(RESULT_OK,i2);
                finish();
            }
        });
    }
}
