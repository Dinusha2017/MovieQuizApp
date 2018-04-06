package com.ctse.sliit.moviequizapp.controllers;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ctse.sliit.moviequizapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);


        Button buttonPlay = findViewById(R.id.buttonPlay);

        // Capture button clicks
        buttonPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                /*
				 * Intent is just like glue which helps to navigate one activity
				 * to another.
				 */

                Intent intent = new Intent(MainActivity.this, QuizLevels.class);
                startActivity(intent);//startActivity allow you to move
            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerIask(), 2000, 3000);
    }


    public class MyTimerIask extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    }else if (viewPager.getCurrentItem()== 1){
                        viewPager.setCurrentItem(2);
                    }else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
