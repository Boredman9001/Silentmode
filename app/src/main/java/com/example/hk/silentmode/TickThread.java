package com.example.hk.silentmode;

import android.os.CountDownTimer;
import android.widget.Toast;

public class TickThread extends Thread
{
    long temp;
    TickThread(long temp)
    {
        this.temp=temp;
    }
    public void run()
    {
        new CountDownTimer(temp, 1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish()
            {

            }
        }.start();


    }

}
