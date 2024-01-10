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
                       } else {
                           button.setText("O");
                       }
                   }
                    step++;
                    if(CheckWinner()){
                        if (step % 2 == 0){
                            Toast.makeText(GameActivity.this, "Победитель: O", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(GameActivity.this, "Победитель: X", Toast.LENGTH_LONG).show();
                        }
                        ClearGrid();
                    };
                }
            });
        }
        
        gridLayout.setBackgroundColor(Color.BLACK);
    }

    public void ClearGrid(){
        for (int i = 0; i < gridLayout.getChildCount(); i++){
            View childView = gridLayout.getChildAt(i);
            Button button = (Button) childView;
            button.setText("");
        }

    }
    public boolean CheckWinner(){
        int rowCount = gridLayout.getRowCount();
        int colCount = gridLayout.getColumnCount();
        String[][] grid = new String[rowCount][colCount];
        for (int i = 0; i < rowCount; i++){
            for (int j = 0; j < colCount; j++){
                View childView = gridLayout.getChildAt(j * gridLayout.getColumnCount() + i);
                Button button = (Button) childView;
                grid[i][j] = button.getText().toString();
            }
        }

        for (int i = 0; i < rowCount; i++){
            if(!grid[i][0].equals("") && grid[i][0].equals(grid[i][1]) && grid[i][0].equals(grid[i][2])){
                return true;
            }
        }
        for (int i = 0; i < colCount; i++){
            if(!grid[0][i].equals("") && grid[0][i].equals(grid[1][i]) && grid[0][i].equals(grid[2][i])){
                return true;
            }
        }
        for (int i = 0; i < rowCount; i++) {
            if (!grid[0][0].equals("") && grid[0][0].equals(grid[1][1]) && grid[0][0].equals(grid[2][2])) {
                return true;
            }
        }
        for (int i = 0; i < rowCount; i++) {
            if (!grid[0][2].equals("") && grid[0][2].equals(grid[1][1]) && grid[0][2].equals(grid[2][0])) {
                return true;
            }
        }
        return false;
    }
}