package com.example.ticktackgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mode1, mode2, mode3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mode1 = findViewById(R.id.Mode1_button);
        mode2 = findViewById(R.id.Mode2_button);
        mode3 = findViewById(R.id.Mode3_button);
        mode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity(v, getResources().getString(R.string.mode1));
            }
        });
        mode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity(v, getResources().getString(R.string.mode2));
            }
        });
        mode3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity(v, getResources().getString(R.string.mode3));
            }
        });
    }
    public void GameActivity(View view, String str){
        Intent intent = new Intent(this, GameActivity.class);
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();
        intent.putExtra("mode", str);
        startActivityForResult(intent,1);
    }
}