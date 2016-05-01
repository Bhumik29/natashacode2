package com.bhumika29.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void onButtonClickk(View v) {
        if (v.getId() == R.id.button4) {
            Intent i = new Intent(Main3Activity.this, Main2Activity.class);
            startActivity(i);
        }
    }
}
