package com.example.aakanksha.project_face;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Manager_info extends AppCompatActivity {

    Button settingsbutton;
    Button statsbutton;
    Button setupbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_info);
        settingsbutton = findViewById(R.id.buttonsettings);
        statsbutton=findViewById(R.id.buttonstats);

        settingsbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Frequency.class);
                startActivity(i);

            }
        });

        statsbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Plot.class);
                startActivity(i);


            }
        });


    }

}