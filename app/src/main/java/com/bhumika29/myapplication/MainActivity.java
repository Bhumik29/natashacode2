package com.bhumika29.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick (View v) {
        if(v.getId()== R.id.button3){
            Intent i= new Intent(MainActivity.this, Main2Activity.class);
            startActivity(i);
        }
        else if (v.getId()==R.id.button2){
            Intent i= new Intent(MainActivity.this, Main3Activity.class);
            startActivity(i);

        }
    }
}
