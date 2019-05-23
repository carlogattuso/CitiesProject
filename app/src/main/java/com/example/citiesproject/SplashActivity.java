package com.example.citiesproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timer= new Thread()
        {
            public void run()
            {
                try
                {
                    //Display for 3 seconds
                    sleep(4000);
                }
                catch (InterruptedException e)
                {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                finally
                {
                    final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
                    boolean registered = sharedPref.getBoolean("registered", false);
                    Class dest;
                    if(!registered) dest = LoginActivity.class;
                    else dest = MainActivity.class;
                    Intent intent = new Intent(SplashActivity.this,dest);
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        finish();

    }
}
