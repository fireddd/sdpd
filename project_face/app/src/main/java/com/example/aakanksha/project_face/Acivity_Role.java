package com.example.aakanksha.project_face;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Acivity_Role extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acivity__role);
    }

    public void onClickCashier(View view) {
        Intent intent = new Intent(Acivity_Role.this, cashier_customer_info.class);
        startActivity(intent);
    }

    public void onClickManager(View view) {
        Intent intent = new Intent(Acivity_Role.this, Manager_info.class);
        startActivity(intent);
    }
}