package com.example.aakanksha.project_face;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivityReg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reg);
    }
    public void onClickRegister(View view)
    {
        Intent intent = new Intent(MainActivityReg.this, Acivity_Role.class);
        startActivity(intent);
    }
}
