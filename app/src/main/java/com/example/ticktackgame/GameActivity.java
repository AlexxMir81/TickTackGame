package com.example.ticktackgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    private int step = 1;
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView textView = findViewById(R.id.Mode_text);
        String str = getIntent().getStringExtra("mode");
        textView.setText(str);

        gridLayout = findViewById(R.id.Grid_content);//new GridLayout(this);

        for (int i=0; i<9; i++){
            Button button = new Button(this);
            button.setText("");
            button.setTextSize(20);
            gridLayout.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(button.getText()=="") {
                       if (step % 2 == 0) {
                           button.setText("X");
                           step++;
                       } else {
                           button.setText("O");
                           step++;
                       }
                   }
                    View childView = gridLayout.getChildAt(0 * gridLayout.getColumnCount() + 0);
                    Button button1 = (Button) childView;
                    Toast.makeText(GameActivity.this,button1.getText().toString(),Toast.LENGTH_LONG).show();
                }
            });
        }
        
        gridLayout.setBackgroundColor(Color.BLACK);
    }
}