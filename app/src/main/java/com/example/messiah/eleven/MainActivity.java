package com.example.messiah.eleven;

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

    // Starts the battle between the AI and the User
    public void battleAi(View view) {
        Intent openAi = new Intent(MainActivity.this, AiActivity.class);
        startActivity(openAi);
    }
}
