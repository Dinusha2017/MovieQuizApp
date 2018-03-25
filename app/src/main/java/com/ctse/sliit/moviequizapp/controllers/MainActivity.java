package com.ctse.sliit.moviequizapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ctse.sliit.moviequizapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

}
